package com.nhnacademy.aiot;

import java.util.HashMap;
import java.util.Map;

public class Request extends Thread{
    static final String FIELD_CONTENT_LENGTH = "Content-length";
    String method;
    String path;
    String version;
    Map<String, String> fieldMap;
    
    String body;

    public Request(String method, String path, String version) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.fieldMap = new HashMap<>();
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addField(String line) {
        String[] fields = line.split("\\s", 2);
        if(fields.length != 2) {
            throw new InvalidHttpRequestException();
        }
        addField(fields[0].trim(), fields[1].trims());
    }

    public void addField(String key, String value){
        try {
            Integer.parseInt(value);

        } catch (Exception e) {
            // throw new 
        }
        
        fieldMap.put(key.toLowerCase(), value);
    }

    public int getCountContentLength() {
        return Integer.parseInt(FIELD_CONTENT_LENGTH);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s %s%s", getMethod(), getPath(), getVersion(), CRLF));
        fieldMap.forEach((k, v) -> builder.append(String.format("%s: %s%s", k, v, CRLF)));
    }
}
