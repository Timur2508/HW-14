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
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        UUID productId3 = UUID.randomUUID();
        UUID productId4 = UUID.randomUUID();
        UUID productId5 = UUID.randomUUID();

        productStorage.put(productId1, new Product(productId1, "Книга hello", 10.99, false));
        productStorage.put(productId2, new Product(productId2, "Футболка hello hello", 20.99, true));
        productStorage.put(productId3, new Product(productId3, "Телефон", 500.00, false));
        productStorage.put(productId4, new Product(productId4, "Ноутбук hello", 1000.00, false));
        productStorage.put(productId5, new Product(productId5, "Планшет hello", 300.00, false));

        UUID articleId1 = UUID.randomUUID();
        UUID articleId2 = UUID.randomUUID();
        UUID articleId3 = UUID.randomUUID();

        articleStorage.put(articleId1, new Article(articleId1, "hello Notebook hello", "Article"));
        articleStorage.put(articleId2, new Article(articleId2, "hello", "Article"));
        articleStorage.put(articleId3, new Article(articleId3, "hello world", "Article"));
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