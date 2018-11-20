package main.java.com.hit.driver;

import main.java.com.hit.controller.CacheUnitController;
import main.java.com.hit.controller.Controller;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.model.CacheUnitClient;
import main.java.com.hit.model.CacheUnitModel;
import main.java.com.hit.model.Model;
import main.java.com.hit.server.Request;
import main.java.com.hit.view.CacheUnitView;
import main.java.com.hit.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Driver<T> {

    public static <T>void main(String[] args) throws IOException {
     Model model = new CacheUnitModel();
     View view = new CacheUnitView();
     Controller controller = new CacheUnitController(model, view);
     ((CacheUnitModel)model).addObserver(controller);
     ((CacheUnitView)view).addObserver(controller);
     view.start();
    }
}
