package main.java.com.hit.dao;
import main.java.com.hit.dm.DataModel;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDao<ID extends java.io.Serializable,T>
{
    void delete(T entity) throws IOException;
    T find(ID id) throws IOException;
    void save(T entity) throws IOException;
}