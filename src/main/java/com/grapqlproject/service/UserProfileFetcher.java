package com.grapqlproject.service;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class UserProfileFetcher implements DataFetcher<String> {


    @Override
    public String get(DataFetchingEnvironment environment) {
        return "Hello";
    }
}
