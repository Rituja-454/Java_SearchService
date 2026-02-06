package com.search.controller;

import com.search.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    
    @GetMapping
    public ResponseEntity<List<String>> searchProductsUnder50() {
        List<String> results = searchService.searchProductsUnder50();
        return ResponseEntity.ok(results);
    }
}
