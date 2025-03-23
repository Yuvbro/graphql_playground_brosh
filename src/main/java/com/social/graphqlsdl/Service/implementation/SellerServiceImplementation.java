package com.social.graphqlsdl.Service.implementation;

import com.social.graphqlsdl.Service.SellerService;
import com.social.graphqlsdl.dto.SellerDto;
import com.social.graphqlsdl.model.Seller;
import com.social.graphqlsdl.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SellerServiceImplementation implements SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImplementation(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<SellerDto> getSellers() {
        List<Seller> allSellers = sellerRepository.findAll();
        return allSellers.stream().map(
                seller -> {
                    return SellerDto.builder()
                            .id(seller.getId())
                            .name(seller.getName())
                            .email(seller.getEmail())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public SellerDto getSellerById(UUID sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));
        // If the Optional contains a Seller instance, orElseThrow() will return the Seller object - otherwise needs to use .get(),
        // and we can then proceed to create the SellerDto using the information from the Seller instance.

        return SellerDto.builder()
                .id(seller.getId())
                .name(seller.getName())
                .email(seller.getEmail())
                .build();
    }

    @Override
    public UUID createSeller(SellerDto sellerDto) {
        Seller seller = Seller.builder()
                .name(sellerDto.getName())
                .email(sellerDto.getEmail())
                .build();
        Seller newSeller = sellerRepository.saveAndFlush(seller);

        return newSeller.getId();
    }
}
