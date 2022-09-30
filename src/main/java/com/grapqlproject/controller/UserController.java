package com.grapqlproject.controller;

import com.grapqlproject.config.GraphQLConfig;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    GraphQLConfig graphQLConfig;


    @PostMapping
    public ResponseEntity getUserProfile(@RequestBody String graphQLquery) {
        GraphQL graphQL = graphQLConfig.getGraphQl();
        ExecutionResult result = graphQL.execute(graphQLquery);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}



/*

curl -X POST \
  http://localhost:8080/user \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 030ec047-1240-360d-6a69-abce3a1e191e' \
  -d '{
 userProfile
}'

 */