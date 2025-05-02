package org.skypro.skyshop.service;

import org.skypro.skyshop.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<Object> search(String pattern) {
        return storageService.getAllSearchable()
                .stream()
                .filter(searchable -> searchable.search(pattern))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}