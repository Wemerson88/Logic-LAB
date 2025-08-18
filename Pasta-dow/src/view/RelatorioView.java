package view;

import models.Usuario;
import service.RelatorioService;
import service.UsuarioService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RelatorioView {
    private final RelatorioService relatorioService;
    private final UsuarioService usuarioService;

    //aceita RelatorioService e UsuarioService
    public RelatorioView(RelatorioService relatorioService, UsuarioService usuarioService) {
        this.relatorioService = relatorioService;
        this.usuarioService = usuarioService;
    }

    //menu de relatórios
    public void exibirRelatorios() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu de Relatórios ---");
            System.out.println("1. Relatorio Geral de Usuarios");
            System.out.println("2. Relatorio por Nome de Usuario");
            System.out.println("3. Relatorio de Acessos por Periodo");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao;

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> exibirRelatorioGeral();
                    case 2 -> exibirRelatorioPorUsuario(scanner);
                    case 3 -> exibirRelatorioAcessosPorPeriodo(scanner);
                    case 4 -> {
                        System.out.println("Voltando ao menu principal...");
                        return; 
                    }
                    default -> System.out.println("[ERRO] Opção invalida. Escolha entre 1 e 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] Entrada invalida. Digite um número.");
                scanner.nextLine(); 
            }
        }
    }

    //relatório geral de usuários
    private void exibirRelatorioGeral() {
        System.out.println("\n--- Relatorio Geral de Usuarios ---");
        if (usuarioService.getUsuarios().isEmpty()) {
            System.out.println("Nenhum usuario cadastrado no sistema.");
        } else {
            relatorioService.gerarRelatorioGeral();
        }
    }

    //relatório usuário específico
    private void exibirRelatorioPorUsuario(Scanner scanner) {
        System.out.print("Digite o nome do usuario: ");
        String nomeUsuario = scanner.nextLine().trim();

        if (nomeUsuario.isEmpty()) {
            System.out.println("[ERRO] Nome do usuario nao pode estar vazio.");
            return;
        }

        Usuario usuario = usuarioService.buscarUsuarioPorNome(nomeUsuario);
        if (usuario != null) {
            System.out.println("\n--- Relatorio do Usuario: " + usuario.getNome() + " ---");
            relatorioService.gerarRelatorioPorUsuario(usuario);
        } else {
            System.out.println("[ERRO] Usuario '" + nomeUsuario + "' nao encontrado.");
        }
    }

    //relatório de acessos por período
    private void exibirRelatorioAcessosPorPeriodo(Scanner scanner) {
        try {
            System.out.print("Digite a data inicial (YYYY-MM-DD): ");
            String dataInicialStr = scanner.nextLine();
            System.out.print("Digite a data final (YYYY-MM-DD): ");
            String dataFinalStr = scanner.nextLine();

            var dataInicial = java.time.LocalDate.parse(dataInicialStr);
            var dataFinal = java.time.LocalDate.parse(dataFinalStr);

            if (dataInicial.isAfter(dataFinal)) {
                System.out.println("[ERRO] A data inicial nao pode ser depois da data final.");
                return;
            }

            System.out.println("\n--- Relatorio de Acessos por Periodo ---");
            relatorioService.gerarRelatorioSemanal(dataInicial, dataFinal);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("[ERRO] Formato de data invalido. Use o formato YYYY-MM-DD.");
        }
    }
}
