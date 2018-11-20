package main.java.com.hit
        .server;



import main.java.com.hit.util.CLI;

public class CacheUnitServerDriver
        extends java.lang.Object {
    public CacheUnitServerDriver(){}
    public static void main(java.lang.String[] args){
        CLI cli = new CLI(System.in, System.out);
        Server server = new Server();
        cli.addObserver(server);
        new Thread(cli).start();
    }
}
