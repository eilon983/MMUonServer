package main.java.com.hit.server;

import java.util.Map;

public class Request<T>
        extends java.lang.Object
        implements java.io.Serializable {
   private Map headers;
   private T body;
        public Request(java.util.Map<java.lang.String,java.lang.String> headers,T body){
            this.headers = headers;
            this.body = body;
        }
    public java.util.Map<java.lang.String,java.lang.String> getHeaders(){
            return headers;
    }
    public void setHeaders(java.util.Map<java.lang.String,java.lang.String> headers){
            this.headers = headers; }
    public T getBody(){
            return body;
    }
    public void setBody(T body){
            this.body = body;
    }
    @Override
    public java.lang.String toString(){
            return "Action" + getHeaders().toString() + "content" + getBody().toString();
    }

}
