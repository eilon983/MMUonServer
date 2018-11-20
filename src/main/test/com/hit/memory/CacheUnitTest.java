package main.test.com.hit.memory;

import main.java.com.hit.IAlgoCache;
import main.java.com.hit.LRUAlgoCacheImpl;
import main.java.com.hit.dao.DaoFileImpl;
import main.java.com.hit.dao.IDao;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.memory.CacheUnit;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

public class CacheUnitTest {
@Test
    public void getDataModelsTest() throws IOException, ClassNotFoundException {
        HashMap<Long,DataModel<String>> map = new HashMap<Long, DataModel<String>>();
        DataModel dm1 = new DataModel((long)111111,'A');map.put((long)111111,dm1);
        DataModel dm2 = new DataModel((long)222222,'B');map.put((long)222222,dm2);
        DataModel dm3 = new DataModel((long)333333,'C');map.put((long)333333,dm3);
        DataModel dm4 = new DataModel((long)444444,'D');map.put((long)444444,dm4);
        DataModel dm5 = new DataModel((long)555555,'E');map.put((long)555555,dm5);
        DataModel dm6 = new DataModel((long)666666,'F');map.put((long)666666,dm6);
        DataModel dm7 = new DataModel((long)777777,'G');map.put((long)777777,dm7);
        File file=new File("lib\\HardDisk\\dataModels.dat");
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(map);
        oos.flush();
        oos.close();
        fos.close();
        IAlgoCache<Long, DataModel<String>> algo = new LRUAlgoCacheImpl<>(3);
        IDao<Long, DataModel<String>> dao = new DaoFileImpl<>("lib\\HardDisk\\dataModels.dat");
        CacheUnit<String> cacheUnit = new CacheUnit<>(algo, dao);
    DataModel<String> [] dms2 = cacheUnit.getDataModels(new long[]{(long)3,(long)5,(long)2});
    dm1.toString();
    }




}
