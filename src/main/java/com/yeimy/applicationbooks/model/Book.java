package com.yeimy.applicationbooks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
        @jakarta.persistence.Id
        private int id;
        @Column(unique = true)
        private String title;
        private String authors;
        private String languages;
        private int download_count;
    
    public Book(InformationBook informationBook){
        id = informationBook.id();
        title = informationBook.title();
        authors = informationBook.authors();
        download_count = informationBook.download_count();
        languages = informationBook.languages();
        //subjects= informationBook.subjects();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Id=" + id + ", title=" + title + ", authors=" + authors + ", languages=" + languages + ", download_count=" + download_count;
    }

}  
