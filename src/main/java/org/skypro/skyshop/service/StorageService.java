package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        initializeTestData();
    }

    private void initializeTestData() {
        // Добавление тестовых данных
        productStorage.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "Книга hello", 10.99, false));
        productStorage.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "Футболка hello hello", 20.99, true));
        productStorage.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "Телефон", 500.00, false));
        productStorage.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "Ноутбук hello", 1000.00, false));
        productStorage.put(UUID.randomUUID(), new Product(UUID.randomUUID(), "Планшет hello", 300.00, false));

        articleStorage.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "hello Notebook hello", "Article"));
        articleStorage.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "hello", "Article"));
        articleStorage.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "hello world", "Article"));
    }

    public Collection<Product> getAllProducts() {
        return productStorage.values();
    }

    public Collection<Article> getAllArticles() {
        return articleStorage.values();
    }

    public Collection<Searchable> getAllSearchable() {
        Collection<Searchable> searchableItems = new ArrayList<>();
        searchableItems.addAll(productStorage.values());
        searchableItems.addAll(articleStorage.values());
        return searchableItems;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }
}