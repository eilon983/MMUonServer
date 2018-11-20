package main.java.com.hit.services;


import main.java.com.hit.dm.DataModel;

import java.io.IOException;

public class CacheUnitController<T>
        extends java.lang.Object{
  private CacheUnitService CUS = new CacheUnitService();

    public CacheUnitController() throws IOException, ClassNotFoundException {
    }

    public boolean update(DataModel<T>[] dataModels) throws IOException, ClassNotFoundException {
        return CUS.update(dataModels);
    }
    public boolean delete(DataModel<T>[] dataModels) throws IOException, ClassNotFoundException{
        return CUS.delete(dataModels);
    }
    public DataModel<T>[] get(DataModel<T>[] dataModels)throws IOException, ClassNotFoundException{
        return CUS.get(dataModels);
    }
    public String getStatics()
    { return CUS.getStatics().toString(); }
}
