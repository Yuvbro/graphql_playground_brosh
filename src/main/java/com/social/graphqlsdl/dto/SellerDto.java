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
public class SellerDto {
    private UUID id;
    private String name;
    private String email;

    // GraphQL first look to resolve all field in the query here since te query resolver returns <SellerDto>.
    // If it fails it will look for a field resolver for that field - function with the same name of the field.

}
