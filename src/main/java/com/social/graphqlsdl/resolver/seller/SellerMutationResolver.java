package com.social.graphqlsdl.resolver.seller;

import com.social.graphqlsdl.Service.SellerService;
import com.social.graphqlsdl.dto.SellerDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SellerMutationResolver implements GraphQLMutationResolver {

    private final SellerService sellerService;

    public SellerMutationResolver(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public UUID createSeller(SellerDto sellerDto) {
        return sellerService.createSeller(sellerDto);
    }
}
