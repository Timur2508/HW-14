package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.search.Searchable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    private List<Searchable> searchableItems;

    @BeforeEach
    public void setUp() {
        searchableItems = new ArrayList<>();
        searchableItems.add(new Product(UUID.randomUUID(), "Книга hello", 10.99, false));
        searchableItems.add(new Product(UUID.randomUUID(), "Футболка hello hello", 20.99, true));
        searchableItems.add(new Article(UUID.randomUUID(), "hello Notebook hello", "Article"));
    }

    @Test
    public void testSearchNoObjectsInStorage() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());
        List<Object> results = searchService.search("hello");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchNoMatchingObjects() {
        when(storageService.getAllSearchable()).thenReturn(searchableItems);
        List<Object> results = searchService.search("world");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchWithMatchingObject() {
        when(storageService.getAllSearchable()).thenReturn(searchableItems);
        List<Object> results = searchService.search("hello");
        assertFalse(results.isEmpty());
        assertEquals(3, results.size());
    }
}