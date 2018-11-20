package main.java.com.hit.services;

import main.java.com.hit.IAlgoCache;
import main.java.com.hit.LRUAlgoCacheImpl;
import main.java.com.hit.dao.DaoFileImpl;
import main.java.com.hit.dao.IDao;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.dm.Statics;
import main.java.com.hit.memory.CacheUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CacheUnitService<T>{
    private CacheUnit<String> CacheUnit;
    private int capacity;
    private Statics s;

    public CacheUnitService() throws IOException, ClassNotFoundException {
        capacity = 3;
        IAlgoCache<Long, DataModel<String>> algo = new LRUAlgoCacheImpl<>(capacity);
        IDao<Long, DataModel<String>> dao = new DaoFileImpl<>("lib\\HardDisk\\dataModels.dat");
        CacheUnit = new CacheUnit<>(algo, dao);
        s = Statics.getInstance();
        s.setAlgorithm("LRU");
        s.setCapacity(capacity);
    }
    public boolean update(DataModel<T>[] dataModels) throws IOException, ClassNotFoundException {
        s.incRequests();
        s.incDataModels(dataModels.length);
       long [] dataModelsIds = new long[dataModels.length];
       Arrays.sort(dataModels);
       int i = 0;
       for(DataModel dm : dataModels)
       {
           dataModelsIds[i] = dm.getDataModelId();
           i++;
       }
        DataModel<String>[] dataModelsReference = CacheUnit.getDataModels(dataModelsIds);
        Arrays.sort(dataModelsReference);

        for(i = 0 ; i < dataModels.length;i++)
            dataModelsReference[i].setContent((String) dataModels[i].getContent());

       return true;
    }
    public boolean delete(DataModel<T>[] dataModels) throws IOException, ClassNotFoundException {
        s.incDataModels(dataModels.length);
        s.incRequests();
        long [] dataModelsIds = new long[dataModels.length];
        int i = 0;
        for(DataModel dm : dataModels)
        {
            dataModelsIds[i] = dm.getDataModelId();
            i++;
        }
        DataModel<String>[] dataModelsReference = CacheUnit.getDataModels(dataModelsIds);
        for(i = 0 ; i < dataModels.length;i++)
            dataModelsReference[i].setContent(null);

    return true;
    }
    public DataModel<T>[] get(DataModel<T>[] dataModels) throws IOException, ClassNotFoundException {
        s.incDataModels(dataModels.length);
        s.incRequests();
        long [] dataModelsIds = new long[dataModels.length];
        int i = 0;
        for(DataModel dm : dataModels)
        {
            dataModelsIds[i] = dm.getDataModelId();
            i++;
        }

        return (DataModel<T>[]) CacheUnit.getDataModels(dataModelsIds);
    }
    public Statics getStatics()
    { return s; }
}
