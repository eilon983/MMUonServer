package main.java.com.hit.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CacheUnitClient
            extends java.lang.Object
    {
       private Socket socket;
       private OutputStreamWriter w;
       private InputStreamReader r;
       private char[] buffer;

        public CacheUnitClient() throws IOException {
            socket =  new Socket("localhost", 12345);
            w = new OutputStreamWriter(socket.getOutputStream());
            r = new InputStreamReader(socket.getInputStream());
            buffer = new char[500];
        }

        public String send(String request) {
            String bufferString = null;
            try {
                w.write(request);
                w.flush();
                buffer = new char[500];
                r.read(buffer);
                 bufferString = new String(buffer).trim().replaceAll(" ", "");


            } catch (IOException e) {
                e.printStackTrace();
            }
            return  bufferString.replaceAll("_"," ");
        }}
