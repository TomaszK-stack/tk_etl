package com.example.tk_etlproc.api.config.SQLLogging;

import com.example.tk_etlproc.db.log.entities.Log;
import org.springframework.stereotype.Component;

public class RequestLogContext {
    private static final ThreadLocal<String> requestLogThreadLocal = new ThreadLocal<>();

    public static String getRequestLog() {
        return requestLogThreadLocal.get();
    }

    public static void setRequestLog(String requestLog) {
        requestLogThreadLocal.set(requestLog);
    }

    public static void clearRequestLog() {
        requestLogThreadLocal.remove();
    }
}
