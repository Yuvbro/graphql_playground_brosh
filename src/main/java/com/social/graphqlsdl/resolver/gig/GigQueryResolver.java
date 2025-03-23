package com.social.graphqlsdl.resolver.gig;

import com.social.graphqlsdl.Service.GigService;
import com.social.graphqlsdl.dto.GigDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class GigQueryResolver implements GraphQLQueryResolver {

    private final GigService gigService;

    public GigQueryResolver(GigService gigService) {
        this.gigService = gigService;
    }

    public List<GigDto> recentGigs(int count, int cursor) {
        return gigService.getRecentGigs(count, cursor);
    }
}
