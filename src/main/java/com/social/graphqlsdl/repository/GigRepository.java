package com.social.graphqlsdl.repository;

import com.social.graphqlsdl.model.Gig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GigRepository extends JpaRepository<Gig, UUID> {
    List<Gig> findAllBySellerId(UUID sellerId);
}
