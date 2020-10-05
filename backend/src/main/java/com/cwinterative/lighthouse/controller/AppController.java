package com.cwinterative.lighthouse.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.cwinterative.lighthouse.model.QResult;
import com.cwinterative.utils.FileWriter;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AppController {

    @Autowired
    protected Discovery discoveryService;

    @Autowired
    protected FileWriter fileWriter;

    @GetMapping(path = "/hazards", produces = "application/json")
    public ResponseEntity<Iterable<QResult>> findHazard(@RequestParam String query) {
        System.out.println("Query: " + query);
        QueryOptions options = new QueryOptions.Builder("f297d470-a3be-42fe-a384-c803595844bf","012839cd-284f-4f75-94ca-3e9dce188f45").naturalLanguageQuery(query).build();
        
        Response<QueryResponse> responseResult = discoveryService.query(options).execute();
        QueryResponse response = responseResult.getResult();
        // System.out.println("Response: " + response);
        fileWriter.write(response.toString());
        List<QResult> searchResults = response.getResults().stream().map(r -> { 
            QResult res = new QResult();
            res.setText(r.get("text").toString());

            return res;
        })
        .collect(Collectors.toList());
        return ResponseEntity.ok(searchResults);
    }
    
}
