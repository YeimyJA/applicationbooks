package com.yeimy.applicationbooks.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "authors")
public class Author {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @Transient
    private Book book;
    
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBirth_year() {
        return birth_year;
    }
    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }
    public Integer getDeath_year() {
        return death_year;
    }
    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    @Override
    public String toString() {
        return "Author Id=" + Id + ", name=" + name + ", birth_year=" + birth_year + ", death_year=" + death_year
                + ", book=" + book ;
    }
}
