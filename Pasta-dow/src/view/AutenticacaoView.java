package view;

import models.Usuario;
import service.UsuarioService;

import java.util.Scanner;

public class AutenticacaoView {
    private final UsuarioService usuarioService;

    public AutenticacaoView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario exibirAutenticacao(Scanner scanner) {
        try {
            System.out.print("Digite o login: ");
            String login = scanner.nextLine().trim();

            if (login.isEmpty()) {
                System.out.println("[ERRO] Login nao pode estar vazio.");
                return null;
            }

            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            if (senha.isEmpty()) {
                System.out.println("[ERRO] Senha nao pode estar vazia.");
                return null;
            }

            Usuario usuario = usuarioService.autenticarUsuario(login, senha);
            if (usuario != null) {
                return usuario;
            } else {
                System.out.println("Falha ao autenticar. Verifique suas credenciais.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Erro ao autenticar: " + e.getMessage());
            return null;
        }
    }
}
