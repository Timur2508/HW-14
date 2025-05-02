package org.skypro.skyshop.search;

import java.util.UUID;

public interface Searchable {

    String getSearchTerm();
    String getContentType();
    UUID getId(); // Добавленный метод

    default String getStringRepresentation() {
        return getSearchTerm() + " (" + getContentType() + ")";
    }

    String getTitle();

    int countOccurrences(String searchTerm, String search);
    boolean search(String query);
}