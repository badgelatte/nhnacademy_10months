package com.nhnacademy;

public class Message {
    static int count;
    final String id;
    final long creationTime;

    public Message() {
      count++;
      id = String.format("%s-%02d".getClass().getSimpleName(), count);
      creationTime = System.currentTimeMillis();
    }

    public String getId() {
      return id;
    }

    public long getCreationTime() {
      return creationTime;
    }

    public static int getCount() {
        return count;
    }

}
