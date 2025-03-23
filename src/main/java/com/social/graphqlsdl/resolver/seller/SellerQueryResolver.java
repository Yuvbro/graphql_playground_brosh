package com.social.graphqlsdl.resolver.seller;

import com.social.graphqlsdl.Service.SellerService;
import com.social.graphqlsdl.dto.SellerDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class SellerQueryResolver implements GraphQLQueryResolver {

    private final SellerService sellerService;

    public SellerQueryResolver(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public List<SellerDto> getSellers() {
        return sellerService.getSellers();
    }
}
