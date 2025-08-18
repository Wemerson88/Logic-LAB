package service;

import models.Log;

import java.util.ArrayList;
import java.util.List;

public class LogService {
    private final List<Log> logs;

    public LogService() {
        this.logs = new ArrayList<>();
    }

    public void adicionarLog(Log log) {
        logs.add(log);
        System.out.println("Log adicionado: " + log);
    }

    public List<Log> obterLogs() {
        return logs;
    }

    
    public void resetarLogs() {
        logs.clear();
        System.out.println("Todos os logs foram resetados.");
    }
}
