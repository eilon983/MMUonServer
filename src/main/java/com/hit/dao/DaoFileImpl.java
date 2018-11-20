package main.java.com.hit.dao;

import main.java.com.hit.dm.DataModel;

import java.io.*;
import java.util.HashMap;

public class DaoFileImpl<T>
        extends java.lang.Object
        implements IDao<java.lang.Long,DataModel<T>>
{
    private HashMap<Long,DataModel<T>> map = new HashMap<Long, DataModel<T>>();
    private String filePath;
    private File file;
    public DaoFileImpl(String filePath) throws IOException, ClassNotFoundException {
        this.filePath = filePath;
        file = new File(filePath);
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        map = (HashMap<Long, DataModel<T>>) s.readObject();
        s.close();
    }

    public void update() throws IOException {

        file=new File(filePath);
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(map);
        oos.flush();
        oos.close();
        fos.close();

    }
    @Override
    public void delete(DataModel<T> t) throws IOException {
        map.remove(t.getDataModelId());
        this.update();
    }
    @Override
    public DataModel<T> find(Long id) throws IOException {
        DataModel dm = map.get(id);
        return dm;
    }
    @Override
    public void save(DataModel<T> t) throws IOException {
        map.put(t.getDataModelId(),t);
        this.update();
    }
}