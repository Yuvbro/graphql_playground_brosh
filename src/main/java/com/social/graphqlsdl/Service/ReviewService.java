package com.social.graphqlsdl.Service;

import com.social.graphqlsdl.dto.GigDto;
import com.social.graphqlsdl.dto.ReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<ReviewDto> getFirstReviewsBySellerId(UUID sellerId, Integer count);

    List<ReviewDto> getFirstReviewsByGigId(UUID gigId, Integer count);

    List<ReviewDto> getRecentReviews(int count, int cursor);

}
