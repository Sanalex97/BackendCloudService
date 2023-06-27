package com.example.backendcloudservice.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    protected int num = 1;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    protected static Logger logger;

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }


    public void addLog(String login, String mes) throws IOException {
        Date date = new Date(System.currentTimeMillis());
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("logBackend.txt", true), true);
            try {
                writer.println("[" + this.formatter.format(date) + " " + this.num++ + "] " + login + ":" + mes);
                writer.close();
            } catch (Throwable throwable) {
                try {
                    writer.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

