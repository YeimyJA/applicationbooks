package com.yeimy.applicationbooks.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yeimy.applicationbooks.model.Book;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface BookRepository extends JpaRepository<Book, Integer>{
}
