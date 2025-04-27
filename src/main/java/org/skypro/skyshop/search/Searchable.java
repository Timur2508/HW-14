package org.skypro.skyshop.search;

import java.util.UUID;

public interface Searchable {

    String getSearchTerm();
    String getContentType();
    String getTitle();
    UUID getId(); // Добавленный метод

    default String getStringRepresentation(Searchable searchable) {
        return searchable.getSearchTerm() + " (" + searchable.getContentType() + ")";
    }

    int countOccurrences(String searchTerm, String search);
    boolean search(String query);
}