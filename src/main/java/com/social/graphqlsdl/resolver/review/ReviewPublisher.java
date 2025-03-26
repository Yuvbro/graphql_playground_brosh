package com.social.graphqlsdl.resolver.review;

import com.social.graphqlsdl.dto.ReviewDto;
import org.reactivestreams.Publisher; // Interface for reactive, asynchronous stream producers
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class ReviewPublisher {

    private final Sinks.Many<ReviewDto> reviewSinkProcessor;  // A hot source you can emit events into — it becomes a stream

    public ReviewPublisher() {
        this.reviewSinkProcessor = Sinks.many().replay().latest(); // Sends only the most recent value to new subscribers
    }

    public Publisher<ReviewDto> getRecentReview() {
        return reviewSinkProcessor.asFlux(); // Converts the sink into a Flux (Reactor's stream type), which implements Publisher
    }

    // Any active subscriber listening to getRecentReview() will receive that value in real-time.
    public void publish(ReviewDto reviewDto) {
        reviewSinkProcessor.emitNext(reviewDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Publisher<ReviewDto> getRecentReviewsBySellerId(UUID sellerId) {
        return reviewSinkProcessor.asFlux()
                .filter(reviewDto -> reviewDto.getSellerId().equals(sellerId));
    }
}
