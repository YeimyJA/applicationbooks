package com.yeimy.applicationbooks.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
        @jakarta.persistence.Id
        private int Id;
        @Column(unique = true)
        private String title;
        private String authors;
        private String languages;
        private int download_count;
        private String subjects;
    
    public Book(){}
    
    public Book(InformationBook informationBook){
        this.title = informationBook.title();
        this.authors = informationBook.authors();
        this.download_count = informationBook.download_count();
        this.languages = informationBook.languages();
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "[Id=" + Id + ", title=" + title + ", authors=" + authors + ", languages=" + languages
                + ", download_count=" + download_count + ", subjects=" + subjects;
    }
}  
