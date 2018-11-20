package main.java.com.hit.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.server.CacheUnitServerDriver;
import main.java.com.hit.server.Request;

import java.io.IOException;

public class CacheUnitModel
        extends java.util.Observable
        implements Model
{
    private String server_Answer;
    private CacheUnitClient client;
    private ObjectMapper mapper;

    public CacheUnitModel() throws IOException {
           client = new CacheUnitClient();
         mapper = new ObjectMapper();
    }

    @Override
    public <T> void updateModelData(T t) throws IOException {

        try {

            server_Answer = client.send(mapper.writeValueAsString( (Request<DataModel<T>[]> )t));
            setChanged();
            notifyObservers(server_Answer);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}