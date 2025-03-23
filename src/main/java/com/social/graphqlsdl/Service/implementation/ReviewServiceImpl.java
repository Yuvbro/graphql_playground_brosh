package com.social.graphqlsdl.Service.implementation;

import com.social.graphqlsdl.Service.ReviewService;
import com.social.graphqlsdl.dto.ReviewDto;
import com.social.graphqlsdl.model.Gig;
import com.social.graphqlsdl.model.Review;
import com.social.graphqlsdl.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDto> getFirstReviewsBySellerId(UUID sellerId, Integer count) {
        List<Review> reviewsBySellerId = reviewRepository.findAllBySellerId(sellerId, PageRequest.of(0, count));
        return reviewsBySellerId.stream()
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .text(review.getText())
                        .gigId(review.getGig().getId())
                        .sellerId(review.getSeller().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getFirstReviewsByGigId(UUID gigId, Integer count) {
        List<Review> reviewsByGigId = reviewRepository.findAllByGigId(gigId, PageRequest.of(0, count));
        return reviewsByGigId.stream()
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .text(review.getText())
                        .gigId(review.getGig().getId())
                        .sellerId(review.getSeller().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getRecentReviews(int count, int cursor) {
        PageRequest pageable = PageRequest.of(cursor, count);
        Page<Review> lastReviews = reviewRepository.findAll(pageable);

        return lastReviews.stream()
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .text(review.getText())
                        .gigId(review.getGig().getId())
                        .sellerId(review.getSeller().getId())
                        .build())
                .collect(Collectors.toList());

    }
}
