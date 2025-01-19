package com.yeimy.applicationbooks.service;

public interface IConvierteDatos {
    <T> T getInformation(String json, Class<T> clase);
}
