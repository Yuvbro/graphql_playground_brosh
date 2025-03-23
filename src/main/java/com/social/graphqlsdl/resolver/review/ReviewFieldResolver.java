package com.social.graphqlsdl.resolver.review;

import com.social.graphqlsdl.Service.GigService;
import com.social.graphqlsdl.Service.SellerService;
import com.social.graphqlsdl.dto.GigDto;
import com.social.graphqlsdl.dto.ReviewDto;
import com.social.graphqlsdl.dto.SellerDto;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class ReviewFieldResolver implements GraphQLResolver<ReviewDto> {

    private final GigService gigService;
    private final SellerService sellerService;

    public ReviewFieldResolver(GigService gigService, SellerService sellerService) {
        this.gigService = gigService;
        this.sellerService = sellerService;
    }

    public GigDto getGig(ReviewDto reviewDto) {
        return gigService.getGigById(reviewDto.getGigId());
    }

    public SellerDto getSeller(ReviewDto reviewDto) {
        return sellerService.getSellerById(reviewDto.getSellerId());
    }

}
