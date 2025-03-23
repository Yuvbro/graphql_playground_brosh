package com.social.graphqlsdl.resolver.seller;

import com.social.graphqlsdl.Service.GigService;
import com.social.graphqlsdl.Service.ReviewService;
import com.social.graphqlsdl.Service.SellerService;
import com.social.graphqlsdl.dto.ReviewDto;
import com.social.graphqlsdl.dto.SellerDto;
import com.social.graphqlsdl.dto.GigDto;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class SellerFieldResolver implements GraphQLResolver<SellerDto> {

    private final GigService gigService;
    private final ReviewService reviewService;

    public SellerFieldResolver(GigService gigService, SellerService sellerService, ReviewService reviewService) {
        this.gigService = gigService;
        this.reviewService = reviewService;
    }


    public List<GigDto> gigs(SellerDto sellerDto) {
        return gigService.getAllGigsBySellerId(sellerDto.getId());
    }

    public Integer gigCount(SellerDto sellerdto) {
        return gigService.getGigCountBySellerId(sellerdto.getId());
    }

    public List<ReviewDto> reviews(SellerDto sellerdto, Integer first) {
        return reviewService.getFirstReviewsBySellerId(sellerdto.getId(), first);
    }
}
