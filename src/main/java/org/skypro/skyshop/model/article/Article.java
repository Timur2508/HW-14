package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable, Comparable<Article> {
    private final UUID id;
    private String title;
    private String contentType;

    public Article(UUID id, String title, String contentType) {
        this.id = id;
        this.title = title;
        this.contentType = contentType;
    }

    public UUID getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return title;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return contentType;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int countOccurrences(String searchTerm, String search) {
        String str = getStringRepresentation().toLowerCase();
        String substr = search.toLowerCase();
        int count = 0;
        int index = 0;
        int indexSubstr = str.indexOf(substr, index);
        while (indexSubstr != -1) {
            count++;
            index = indexSubstr + substr.length();
            indexSubstr = str.indexOf(substr, index);
        }
        return count;
    }

    @Override
    public boolean search(String query) {
        return countOccurrences(getSearchTerm(), query) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Article other) {
        int lengthComparison = Integer.compare(other.getSearchTerm().length(), this.getSearchTerm().length());
        if (lengthComparison != 0) {
            return lengthComparison;
        }
        return this.getSearchTerm().compareTo(other.getSearchTerm());
    }
}