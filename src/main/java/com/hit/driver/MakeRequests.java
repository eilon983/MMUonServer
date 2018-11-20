package main.java.com.hit.driver;

import main.java.com.hit.dm.DataModel;
import main.java.com.hit.server.Request;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MakeRequests {
    public static <T>void main(String[] args) throws IOException {
        Map headers = new HashMap<String, String>();
        DataModel dm1 = new DataModel((long) 111111, "There");
        DataModel dm2 = new DataModel((long) 222222, "is");
        DataModel dm3 = new DataModel((long) 333333, "starsman");
        DataModel dm4 = new DataModel((long) 444444, "in");
        DataModel dm5 = new DataModel((long) 555555, "the");
        DataModel dm6 = new DataModel((long) 666666, "sky");
        DataModel dm7 = new DataModel((long) 777777, 'G');
        DataModel[] dms1 = {dm1, dm2};
        DataModel[] dms2 = {dm1, dm3, dm4, dm5, dm6, dm7};
        DataModel[] dms3 = {dm7};
        DataModel[] dms4 = {dm5, dm6};
        DataModel[] dms5 = {dm1, dm3, dm4, dm5, dm6, dm7};

        ////////
        headers.put("action", "UPDATE");
        Request<DataModel<T>[]> request1 = new Request<>(headers, dms1);
        File file1 = new File("lib\\Requests\\request1.txt");
        /////////
        FileOutputStream fos = new FileOutputStream(file1);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(request1);
        oos.flush();
        oos.close();
        fos.close();
        /////


        ///////
        headers.put("action", "GET");
        Request<DataModel<T>[]> request2 = new Request<>(headers, dms2);
        File file2 = new File("lib\\Requests\\request2.txt");
        /////////
        fos = new FileOutputStream(file2);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(request2);
        oos.flush();
        oos.close();
        fos.close();
        ///////



        headers.put("action", "UPDATE");
        Request<DataModel<T>[]> request3 = new Request<>(headers, dms3);
        File file3 = new File("lib\\Requests\\request3.txt");
        /////////
        fos = new FileOutputStream(file3);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(request2);
        oos.flush();
        oos.close();
        fos.close();
//////////




        headers.put("action", "DELETE");
        Request<DataModel<T>[]> request4 = new Request<>(headers, dms4);
        File file4 = new File("lib\\Requests\\request4.txt");
        /////////
        fos = new FileOutputStream(file4);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(request3);
        oos.flush();
        oos.close();
        fos.close();
///////////////



        headers.put("action", "GET");
        Request<DataModel<T>[]> request5 = new Request<>(headers, dms5);
        File file5 = new File("lib\\Requests\\request5.txt");
///
        fos = new FileOutputStream(file5);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(request4);
        oos.flush();
        oos.close();
        fos.close();



    }
}
