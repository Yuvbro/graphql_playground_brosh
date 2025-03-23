package com.social.graphqlsdl.repository;

import com.social.graphqlsdl.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findAllBySellerId(UUID sellerId, Pageable pageable);

    List<Review> findAllByGigId(UUID gigId, Pageable pageable);
}
