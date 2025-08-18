package service;

import models.Log;
import models.Usuario;

import java.time.LocalDate;
import java.util.List;

public class RelatorioService {
    private final LogService logService; //logs de acesso
    private final List<Usuario> usuarios; //Lista de usuários cadastrados

    
    public RelatorioService(LogService logService, List<Usuario> usuarios) {
        this.logService = logService;
        this.usuarios = usuarios;
    }

    // Relatório Geral de Usuários
    public void gerarRelatorioGeral() {
        System.out.println("\n--- Relatorio Geral de Usuarios ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado no sistema.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario); 
            }
        }
    }

    // Relatório por Usuário
    public void gerarRelatorioPorUsuario(Usuario usuario) {
        System.out.println("\n--- Relatorio Detalhado do Usuario: " + usuario.getNome() + " ---");
        List<Log> logs = logService.obterLogs();
        boolean encontrou = false;

        for (Log log : logs) {
            if (log.getUsuario().equals(usuario)) {
                System.out.println(log); 
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum acesso registrado para o usuario: " + usuario.getNome());
        }
    }

    // Relatório Semanal de Acessos
    public void gerarRelatorioSemanal(LocalDate inicio, LocalDate fim) {
        System.out.println("\n--- Relatorio de Acessos por Periodo ---");
        System.out.println("Periodo: " + inicio + " a " + fim);
        List<Log> logs = logService.obterLogs();
        boolean encontrou = false;

        for (Log log : logs) {
            LocalDate dataLog = log.getDataHora().toLocalDate();
            if (!dataLog.isBefore(inicio) && !dataLog.isAfter(fim)) {
                System.out.println(log); 
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum acesso registrado nesse período.");
        }
    }
}
