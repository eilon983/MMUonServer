package main.java.com.hit.server;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.dm.Statics;
import main.java.com.hit.services.CacheUnitController;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HandleRequest<T> implements Runnable {
    private Socket socket;
    private CacheUnitController<T> controller;
    public HandleRequest(java.net.Socket socket, CacheUnitController<T> controller){
        this.socket = socket;
        this.controller=controller;
    }

    @Override
    public void run() {
        String str;
        InputStream is = null;
        OutputStream os = null;
        int req_Counter = 0;

        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reader req;
        Writer res;
        Type ref;
        Request<DataModel<T>[]> request;
        Map<String, String> headers;

      while ((true)&&(!socket.isClosed())) {
            try {
                char[] buffer = new char[2048];
                req = new InputStreamReader(is);
                res = new OutputStreamWriter(os);
                ref = new TypeToken<Request<DataModel<T>[]>>() {}.getType();
                req.read(buffer);
                request = new Gson().fromJson(new String(buffer).trim(), ref);
                headers = request.getHeaders();

                switch (headers.get("action"))
                {
                    case "DISCONNECT":
                    {
                        socket.close();
                        break;
                    }
                    case "STATICS":
                    {
                        res.write(controller.getStatics());
                        break;
                    }
                    case "DELETE":
                    {
                        req_Counter++;
                        if (controller.delete(request.getBody())) res.write("Request_"+ req_Counter + "_(Type:_'DELETE')_succeed.");
                        else res.write("Request_"+ req_Counter + "_(Type:_'DELETE')_failed.");
                        break;
                    }
                    case "GET":
                    {
                        req_Counter++;
                        DataModel<T>[] requestToSendBack = controller.get(request.getBody());
                        ObjectMapper mapper = new ObjectMapper();

                        try {
                            String json = mapper.writeValueAsString(requestToSendBack);
                            if(json != null)
                              //  res.write(json);
                           res.write("Request_"+ req_Counter + "_(Type:_'GET')_succeed.");
                            else
                                res.write("Request_"+ req_Counter + "_(Type:_'GET')_failed.");
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "UPDATE":
                    {
                        req_Counter++;
                        if (controller.update(request.getBody())) res.write("Request_"+ req_Counter + "_(Type:_'UPDATE')_succeed.");
                        else res.write("Request_"+ req_Counter + "_(Type:_'UPDATE')_failed.");
                        break;
                    }


                }

                        res.flush();
                    } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
