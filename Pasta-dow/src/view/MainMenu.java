package view;

import models.Aluno;
import models.Professor;
import models.Usuario;
import service.RelatorioService;
import service.UsuarioService;

import java.util.Scanner;

public class MainMenu {
    private final UsuarioService usuarioService;
    private final RelatorioService relatorioService;

    public MainMenu(UsuarioService usuarioService, RelatorioService relatorioService) {
        this.usuarioService = usuarioService;
        this.relatorioService = relatorioService;
    }

    public void exibirMenu() {
        try (Scanner scanner = new Scanner(System.in)) { 
            Usuario usuarioAtual = null;

            while (true) {
                try {
                    // Menu de autenticação
                    if (usuarioAtual == null) {
                        System.out.println("\n--- LogicLab ---");
                        System.out.println("1 - Autenticar");
                        System.out.println("2 - Sair\n");
                        System.out.print("Escolha uma opcao: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("\n[ERRO] Entrada invalida. Digite um numero.\n");
                            scanner.nextLine(); 
                            continue;
                        }

                        int opcao = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (opcao) {
                            case 1 -> {
                                AutenticacaoView autenticacaoView = new AutenticacaoView(usuarioService);
                                usuarioAtual = autenticacaoView.exibirAutenticacao(scanner);
                                if (usuarioAtual == null) {
                                    System.out.println("Falha ao autenticar.");
                                } else {
                                    System.out.println("Bem-vindo, " + usuarioAtual.getNome() + "!");
                                }
                            }
                            case 2 -> {
                                System.out.println("\nFechando o sistema...\n");
                                return; 
                            }
                            default -> System.out.println("\nOpção invalida. Tente novamente.\n");
                        }
                        continue; 
                    }

                    // Menu  após autenticação
                    System.out.println("\n--- Menu Principal ---");
                    System.out.println("1 - Atualizar Dados (Aluno)");
                    System.out.println("2 - Gerar Relatorio (Professor)");
                    System.out.println("3 - Resetar Sistema (Professor)");
                    System.out.println("4 - Sair\n");
                    System.out.print("Escolha uma opcao: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("\n[ERRO] Entrada invalida. Digite um numero.\n");
                        scanner.nextLine(); 
                        continue;
                    }

                    int opcao = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (opcao) {
                        case 1 -> {
                            if (usuarioAtual instanceof Aluno aluno) {
                                atualizarDadosAluno(scanner, aluno);
                            } else {
                                System.out.println("\n[ERRO] Apenas alunos podem atualizar seus dados.\n");
                            }
                        }
                        case 2 -> {
                            if (usuarioAtual instanceof Professor) {
                                RelatorioView relatorioView = new RelatorioView(relatorioService, usuarioService);
                                relatorioView.exibirRelatorios();
                            } else {
                                System.out.println("\n[ERRO] Apenas professores podem gerar relatórios.\n");
                            }
                        }
                        case 3 -> {
                            if (usuarioAtual instanceof Professor professor) {
                                usuarioService.resetarSistema();
                                System.out.println("\nSistema resetado pelo professor: " + professor.getNome());
                            } else {
                                System.out.println("\n[ERRO] Apenas professores podem resetar o sistema.\n");
                            }
                        }
                        case 4 -> {
                            System.out.println("\nDeslogando...\n");
                            usuarioAtual = null;
                        }
                        default -> System.out.println("\n[ERRO] Opcao invalida. Tente novamente.\n");
                    }

                } catch (java.util.NoSuchElementException e) {
                    System.out.println("\n[ERRO] Sem entrada disponivel. Forneca entradas válidas.\n");
                    break;
                } catch (Exception e) {
                    System.out.println("\n[ERRO] Ocorreu um erro: " + e.getMessage() + "\n");
                    scanner.nextLine(); 
                }
            }
        }
    }

    private void atualizarDadosAluno(Scanner scanner, Aluno aluno) {
        System.out.print("Novo nome: ");
        aluno.setNome(scanner.nextLine());

        System.out.print("Novo login: ");
        aluno.setLogin(scanner.nextLine());

        System.out.print("Novo email: ");
        aluno.setEmail(scanner.nextLine());

        System.out.print("Nova senha: ");
        aluno.setSenha(scanner.nextLine());

        System.out.print("Novo curso: ");
        aluno.setCurso(scanner.nextLine());

        System.out.println("Dados atualizados com sucesso!");
    }
}
