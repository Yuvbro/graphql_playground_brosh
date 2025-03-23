package com.social.graphqlsdl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GigDto {
    private UUID id;
    private String title;
    private String description;
    private String category;
    private UUID sellerId;
    private UUID reviewId;
}
