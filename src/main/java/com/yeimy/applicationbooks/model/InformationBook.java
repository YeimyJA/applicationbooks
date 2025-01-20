package com.yeimy.applicationbooks.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InformationBook(Integer id, String title, List<Author> authors, List<String> languages, int download_count) {
}