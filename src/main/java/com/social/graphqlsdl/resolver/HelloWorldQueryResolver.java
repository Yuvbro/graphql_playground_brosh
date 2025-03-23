package com.social.graphqlsdl.resolver;

import com.social.graphqlsdl.dto.CustomerDto;
import com.social.graphqlsdl.dto.MessageDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {
    public String getHelloWorld() {
        return "Hello World from Yuval Brosh and Fiverr";
    }

    public String getGreetingMessage(String firstName, String lastName) {
        return String.format("Hello from Brosh %s %s", firstName, lastName);
    }

    public MessageDto getMessage() {
        return MessageDto.builder()
                .id(UUID.randomUUID())
                .text("Hello from Brosh and Fiverr")
                .build();
    }

    public List<Integer> getRollDice() {
        return Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    public CustomerDto customer() {
        return CustomerDto.builder()
                .birthDate(LocalDate.now())
                .build();
    }
}
