package com.social.graphqlsdl.resolver;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.ExceptionWhileDataFetching;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * When you define a @Component that implements GraphQLErrorHandler,
 * graphql-java-kickstart auto-detects it and uses it globally to process all exceptions thrown during query execution —
 * including those from your resolvers and services.
 */
@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors.stream()
                .map(error -> {
                    if (error instanceof ExceptionWhileDataFetching) {
                        Throwable exception = ((ExceptionWhileDataFetching) error).getException();
                        if (exception instanceof IllegalArgumentException) {
                            return GraphqlErrorBuilder.newError()
                                    .message(exception.getMessage())
                                    .build();
                        }
                    }
                    return error;
                })
                .collect(Collectors.toList());
    }
}
