package com.social.graphqlsdl.Service.implementation;

import com.social.graphqlsdl.Service.GigService;
import com.social.graphqlsdl.dto.GigDto;
import com.social.graphqlsdl.model.Gig;
import com.social.graphqlsdl.model.Seller;
import com.social.graphqlsdl.repository.GigRepository;
import com.social.graphqlsdl.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GigServiceImplementation implements GigService {

    private final GigRepository gigRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public GigServiceImplementation(GigRepository gigRepository, SellerRepository sellerRepository) {
        this.gigRepository = gigRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<GigDto> getAllGigsBySellerId(UUID sellerId) {
        List<Gig> allGigs = gigRepository.findAllBySellerId(sellerId); // provided by Spring Data JPA,
        return allGigs.stream().map(
                gig -> {
                    return GigDto.builder()
                            .id(gig.getId())
                            .sellerId(sellerId)
                            .title(gig.getTitle())
                            .description(gig.getDescription())
                            .category(gig.getCategory())
                            .build();
                }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<GigDto> getRecentGigs(int count, int cursor) {
        PageRequest pageable = PageRequest.of(cursor, count);
        Page<Gig> lastGigs = gigRepository.findAll(pageable);

        return lastGigs.stream().map(
                gig -> {
                    return GigDto.builder()
                            .id(gig.getId())
                            .sellerId(gig.getSeller().getId())
                            .title(gig.getTitle())
                            .description(gig.getDescription())
                            .category(gig.getCategory())
                            .build();
                }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public UUID createGig(GigDto gigDto) {
        Seller gigSeller = sellerRepository.findById(gigDto.getSellerId()).orElseThrow(
                () -> new RuntimeException("Seller not found with ID: " + gigDto.getSellerId()));

        Gig gig = Gig.builder()
                .title(gigDto.getTitle())
                .description(gigDto.getDescription())
                .category(gigDto.getCategory())
                .seller(gigSeller)
                .build();
        Gig newSGig = gigRepository.saveAndFlush(gig);

        return newSGig.getId();
    }

    @Override
    public Integer getGigCountBySellerId(UUID sellerId) {
        List<Gig> allGigs = gigRepository.findAllBySellerId(sellerId);
        return allGigs.size();
    }

    @Override
    public GigDto getGigById(UUID gigId) {
        Gig gig = gigRepository.findById(gigId)
                .orElseThrow(() -> new RuntimeException("Gig not found with ID: " + gigId));

        return GigDto.builder()
                .id(gig.getId())
                .sellerId(gig.getSeller().getId())
                .title(gig.getTitle())
                .description(gig.getDescription())
                .category(gig.getCategory())
                .build();
    }
}
