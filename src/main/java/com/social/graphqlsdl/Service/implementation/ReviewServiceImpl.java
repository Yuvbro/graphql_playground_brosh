package com.social.graphqlsdl.Service.implementation;

import com.social.graphqlsdl.Service.ReviewService;
import com.social.graphqlsdl.dto.ReviewDto;
import com.social.graphqlsdl.model.Gig;
import com.social.graphqlsdl.model.Review;
import com.social.graphqlsdl.model.Seller;
import com.social.graphqlsdl.repository.GigRepository;
import com.social.graphqlsdl.repository.ReviewRepository;
import com.social.graphqlsdl.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final SellerRepository sellerRepository;
    private final GigRepository gigRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, SellerRepository sellerRepository, GigRepository gigRepository) {
        this.reviewRepository = reviewRepository;
        this.sellerRepository = sellerRepository;
        this.gigRepository = gigRepository;
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
        if (count < 1) {
            throw new IllegalArgumentException("Count must be greater than 0");
        }

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

    @Override
    public UUID createReview(ReviewDto reviewDto) {
        Seller seller = sellerRepository.findById(reviewDto.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + reviewDto.getSellerId()));
        Gig gig = gigRepository.findById(reviewDto.getGigId())
                .orElseThrow(() -> new RuntimeException("Gig not found with ID: " + reviewDto.getGigId()));

        Review review = Review.builder()
                .text(reviewDto.getText())
                .gig(gig)
                .seller(seller)
                .build();
        Review newReview = reviewRepository.saveAndFlush(review);
        return newReview.getId();
    }
}
