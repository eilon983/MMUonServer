
package main.java.com.hit.server;


import main.java.com.hit.services.CacheUnitController;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server extends java.lang.Object implements java.util.Observer {

    private ServerSocket SSocket;
    private boolean State;
    private HandleRequest RequesterHandler;
    public void start() throws IOException {
        int UsingPort = 12345;
        Executor executor = Executors.newFixedThreadPool(5);
        if(State) System.out.println("Server has been started\n");
        try {
            SSocket = new ServerSocket(UsingPort);
            Socket socket;
            while (State) {
                if(SSocket.isClosed() == false)
                {   socket = SSocket.accept();
                RequesterHandler = new HandleRequest<String>(socket, new CacheUnitController<String>());
                executor.execute(RequesterHandler);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable CLI, Object StrObject) {
        if (StrObject.toString().equals("start"))
        {
            State = true;

            if ((SSocket == null)||(SSocket.isClosed())) {
                Thread ServerTherad = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });
                ServerTherad.start();
            }
            else
            {System.out.println("Server is already on\n");}
        }

        if (StrObject.toString().equals("shutdown")) {
            if(State == false)
                System.out.println("Server is already off\n");
            else
            {
                State = false;
                System.out.println("Server turned off");

    }
}}}