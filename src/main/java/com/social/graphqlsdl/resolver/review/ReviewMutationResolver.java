package com.social.graphqlsdl.resolver.review;

import com.social.graphqlsdl.Service.ReviewService;
import com.social.graphqlsdl.dto.ReviewDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReviewMutationResolver implements GraphQLMutationResolver {
    private final ReviewService reviewService;
    private final ReviewPublisher reviewPublisher;

    public ReviewMutationResolver(ReviewService reviewService, ReviewPublisher reviewPublisher) {
        this.reviewService = reviewService;
        this.reviewPublisher = reviewPublisher;
    }

    public UUID createReview(ReviewDto reviewDto) {
        UUID reviewUid =  reviewService.createReview(reviewDto);
        reviewDto.setId(reviewUid);
        reviewPublisher.publish(reviewDto);

        return reviewUid;
    }
}
