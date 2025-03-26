package com.social.graphqlsdl.resolver.review;

import com.social.graphqlsdl.dto.ReviewDto;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReviewSubscription  implements GraphQLSubscriptionResolver {

    private final ReviewPublisher reviewPublisher;

    public ReviewSubscription(ReviewPublisher reviewPublisher) {
        this.reviewPublisher = reviewPublisher;
    }

    public Publisher<ReviewDto> recentReview() {
        return reviewPublisher.getRecentReview();
    }

    public Publisher<ReviewDto> recentReviewBySellerId(UUID sellerId) {
        return reviewPublisher.getRecentReviewsBySellerId(sellerId);
    }
}
