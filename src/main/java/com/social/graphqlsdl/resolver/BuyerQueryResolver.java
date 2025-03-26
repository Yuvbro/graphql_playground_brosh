package com.social.graphqlsdl.resolver;

import com.social.graphqlsdl.dto.BuyerDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BuyerQueryResolver implements GraphQLQueryResolver {

    public BuyerDto getBuyer() {
        return BuyerDto.builder()
                .birthDate(LocalDate.now())
                .build();
    }
}
