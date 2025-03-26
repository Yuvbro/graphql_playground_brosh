package com.social.graphqlsdl.resolver.review;

import com.social.graphqlsdl.Service.GigService;
import com.social.graphqlsdl.Service.ReviewService;
import com.social.graphqlsdl.dto.GigDto;
import com.social.graphqlsdl.dto.ReviewDto;
import graphql.GraphQLException;
import graphql.GraphqlErrorException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import graphql.kickstart.execution.error.GraphQLErrorHandler;

import java.util.List;

@Component
public class ReviewQueryResolver implements GraphQLQueryResolver {

    private final ReviewService reviewService;

    public ReviewQueryResolver(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public List<ReviewDto> recentReviews(int count, int cursor) {
        try {
            return reviewService.getRecentReviews(count, cursor);
        } catch (IllegalArgumentException e) {
            throw GraphqlErrorException.newErrorException()
                    .message(e.getMessage())
                    .build();
        }
    }
}
