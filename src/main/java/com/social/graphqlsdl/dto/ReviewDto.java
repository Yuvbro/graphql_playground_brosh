package com.social.graphqlsdl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data  // Lombok annotation to create all the (public) getters, setters, equals, hash, and toString methods based on the fields (not public fields)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private UUID id;
    private String text;
    private UUID sellerId;
    private UUID gigId;
}
