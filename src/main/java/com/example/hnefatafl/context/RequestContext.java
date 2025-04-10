package com.example.hnefatafl.context;

public class RequestContext {
    private static final ThreadLocal<String> requestIdHolder = new ThreadLocal<>();

    public static void setClientId(String clientId) {
        requestIdHolder.set(clientId);
    }

    public static String getClientId() {
        return requestIdHolder.get();
    }

    public static void clear() {
        requestIdHolder.remove();
    }
}