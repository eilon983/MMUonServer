package main.java.com.hit.model;

import java.io.IOException;

public interface Model
{
    <T> void updateModelData(T t) throws IOException;
}