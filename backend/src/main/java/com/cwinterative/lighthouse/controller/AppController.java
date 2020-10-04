package com.cwinterative.lighthouse.controller;

import java.util.stream.Collectors;

import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    protected Discovery discoveryService;


    @GetMapping(path = "/hazards")
    public String findHazard(@RequestParam String query) {
        QueryOptions options = new QueryOptions.Builder("f297d470-a3be-42fe-a384-c803595844bf","012839cd-284f-4f75-94ca-3e9dce188f45").naturalLanguageQuery(query).build();
        QueryResponse response = discoveryService.query(options).execute().getResult();
        String titles = response.getResults().stream().map(r -> (String) r.get("title"))
        .collect(Collectors.joining("\n<p>"));
        return titles;
    }
    
}
