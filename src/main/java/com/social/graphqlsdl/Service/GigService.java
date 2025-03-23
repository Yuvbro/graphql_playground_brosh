package com.social.graphqlsdl.Service;

import com.social.graphqlsdl.dto.GigDto;

import java.util.List;
import java.util.UUID;

public interface GigService {
    List<GigDto> getAllGigsBySellerId(UUID sellerId);

    List<GigDto> getRecentGigs(int count, int cursor);

    UUID createGig(GigDto gigDto);

    Integer getGigCountBySellerId(UUID sellerId);
    GigDto getGigById(UUID gigId);
}
