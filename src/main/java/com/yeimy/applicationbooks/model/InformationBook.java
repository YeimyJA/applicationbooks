package com.yeimy.applicationbooks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InformationBook(Integer id, String title, String authors, String bookshelves, String languages, int download_count) {
}
