package com.social.graphqlsdl.Service;

import com.social.graphqlsdl.dto.GigDto;
import com.social.graphqlsdl.dto.SellerDto;

import java.util.List;
import java.util.UUID;

public interface SellerService {
    List<SellerDto> getSellers();

    SellerDto getSellerById(UUID sellerId);

    UUID createSeller(SellerDto sellerDto);
}
