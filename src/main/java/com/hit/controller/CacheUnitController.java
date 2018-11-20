package main.java.com.hit.controller;

import main.java.com.hit.dm.DataModel;
import main.java.com.hit.model.Model;
import main.java.com.hit.server.Request;
import main.java.com.hit.view.View;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class CacheUnitController<T>
        extends java.lang.Object
        implements Controller{
   private Model model;
   private View view;
   private Map headers;
   private Request<DataModel<T>[]> request;
   private File file;

    public CacheUnitController(Model model,View view){this.model = model;
    this.view = view;
     headers = new HashMap<String,String>();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg != null)
        if(arg.getClass().equals(String.class) )
        {
            if(arg.toString().equals("disconnect"))
            {

                headers.put("action","DISCONNECT");
                request = new Request<>(headers,null);
                try {
                    model.updateModelData(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(arg.toString().equals("statics"))
            {
                try {


                headers.put("action","STATICS");
                Request<DataModel<T>[]> request = new Request<>(headers,null);
                model.updateModelData(request);


                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

          else view.updateUIData(arg); //show the client the answer from server
        }
        else {

               file = (File)arg;
               FileInputStream f = null;
               try {

                   f = new FileInputStream(file);
               ObjectInputStream s = new ObjectInputStream(f);
               Request<DataModel<T>[]> request = ( Request<DataModel<T>[]> ) s.readObject();
               s.close();
                   model.updateModelData(request);


               } catch (IOException e) {
                   e.printStackTrace();
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }

           }
        }


        }

