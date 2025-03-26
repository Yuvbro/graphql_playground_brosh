package com.social.graphqlsdl.resolver.review;

import com.social.graphqlsdl.Service.ReviewService;
import com.social.graphqlsdl.dto.ReviewDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReviewMutationResolver implements GraphQLMutationResolver {
    private final ReviewService reviewService;

    public ReviewMutationResolver(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public UUID createReview(ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }
}
