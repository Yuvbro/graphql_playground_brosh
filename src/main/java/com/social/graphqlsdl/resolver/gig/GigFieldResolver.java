package com.social.graphqlsdl.resolver.gig;
import com.social.graphqlsdl.Service.ReviewService;
import com.social.graphqlsdl.Service.SellerService;
import com.social.graphqlsdl.dto.ReviewDto;
import com.social.graphqlsdl.dto.SellerDto;
import com.social.graphqlsdl.dto.GigDto;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GigFieldResolver implements GraphQLResolver<GigDto> {

    private final SellerService sellerService;
    private final ReviewService reviewService;


    public GigFieldResolver(SellerService sellerService, ReviewService reviewService) {
        this.sellerService = sellerService;
        this.reviewService = reviewService;
    }

    public SellerDto getSeller(GigDto gigDto) {
        return sellerService.getSellerById(gigDto.getSellerId());
    }

    public List<ReviewDto> reviews(GigDto gigDto, Integer first) {
        return reviewService.getFirstReviewsByGigId(gigDto.getId(), first);
    }
}
