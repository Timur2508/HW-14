package org.skypro.skyshop.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Product implements Searchable, Comparable<Product> {
    private final UUID id; // Добавленное поле
    private String title;
    private double price;
    private boolean isSpecial;

    public Product(UUID id, String title, double price, boolean isSpecial) {
        this.id = id; // Присваиваем значение id
        this.title = title;
        this.price = price;
        this.isSpecial = isSpecial;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return title;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "Product";
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
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Product other) {
        int lengthComparison = Integer.compare(other.getSearchTerm().length(), this.getSearchTerm().length());
        if (lengthComparison != 0) {
            return lengthComparison;
        }
        return this.getSearchTerm().compareTo(other.getSearchTerm());
    }
}