package main.java.com.hit.memory;
import main.java.com.hit.IAlgoCache;
import main.java.com.hit.dao.IDao;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.dm.Statics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheUnit<T>{
    private IAlgoCache algo;
    private IDao dao;
    public CacheUnit(IAlgoCache<Long, DataModel<T>> algo, IDao<Long, DataModel<T>> dao)
    {
        this.algo = algo;
        this.dao = dao;
    }

    public DataModel<T> [] getDataModels(long[] ids)
            throws java.lang.ClassNotFoundException,
            java.io.IOException
    {

        DataModel<T> temp_dataModel;
        List<Long> PageFaults = new ArrayList<Long>();
        List<DataModel<T>> dataModelsToReturn = new ArrayList<DataModel<T>>();
        List<DataModel<T>> returnedDataModels = new ArrayList<DataModel<T>>();
        List<DataModel<T>> dataModelsToHandle = new ArrayList<DataModel<T>>();

        for(Long id : ids)
        {
            temp_dataModel = (DataModel<T>) algo.getElement(id);
            if(temp_dataModel==null)
                PageFaults.add(id);
            else
                dataModelsToReturn.add(temp_dataModel);
        }

        for(Long id : PageFaults)
            if(id != null)
            dataModelsToHandle.add((DataModel<T>) dao.find(id));

        for(DataModel dm : dataModelsToHandle) {
            if (dm != null)
            {
                dataModelsToReturn.add(dm);
                temp_dataModel = (DataModel<T>) algo.putElement(dm.getDataModelId(), dm);
                if(temp_dataModel != null)
                returnedDataModels.add((DataModel<T>) algo.putElement(dm.getDataModelId(), dm));
        }}
        for(DataModel dm:returnedDataModels)
            if(dm != null)
            dao.save((DataModel<T>)dm);

        Statics.getInstance().incSwaps(returnedDataModels.size());

        return dataModelsToReturn.toArray(new DataModel[dataModelsToReturn.size()]);

    }
}
