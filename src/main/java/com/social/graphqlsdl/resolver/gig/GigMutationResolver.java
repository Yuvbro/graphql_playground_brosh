package com.social.graphqlsdl.resolver.gig;

import com.social.graphqlsdl.Service.GigService;
import com.social.graphqlsdl.dto.GigDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GigMutationResolver implements GraphQLMutationResolver {

    private final GigService gigService;

    public GigMutationResolver(GigService gigService) {
        this.gigService = gigService;
    }

    public UUID createGig(GigDto gigDto) {
        return gigService.createGig(gigDto);
    }
}
