package com.grapqlproject.config;

import com.grapqlproject.service.UserProfileFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class GraphQLConfig {

    @Value("classpath:user.graphqls")
    private Resource resourceFile;

    private GraphQL graphQl;

    @Autowired
    UserProfileFetcher userProfileFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        File file = resourceFile.getFile();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
        .dataFetcher("userProfile", userProfileFetcher)).build();


        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQl = GraphQL.newGraphQL(schema).build();
    }

    public GraphQL getGraphQl() {
        return graphQl;
    }
}
