package com.grapqlproject.controller;

import com.grapqlproject.config.GraphQLConfig;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    GraphQLConfig graphQLConfig;


    @GetMapping
    public ResponseEntity getUserProfile(@RequestBody String graphQLquery) {
        GraphQL graphQL = graphQLConfig.getGraphQl();
        ExecutionResult result = graphQL.execute(graphQLquery);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
