package main;

import models.Professor;
import models.Usuario;
import service.LogService;
import service.RelatorioService;
import service.UsuarioService;
import view.MainMenu;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            // Inicializando serviços
            LogService logService = new LogService();
            UsuarioService usuarioService = new UsuarioService();
            List<Usuario> usuarios = usuarioService.getUsuarios();
            RelatorioService relatorioService = new RelatorioService(logService, usuarios);

            // Criando um usuário administrador (Professor)
            Professor admin = new Professor("Admin", "admin", "admin@complab.com", "1234");
            usuarioService.adicionarUsuario(admin);

            System.out.println("Professor inicial cadastrado com sucesso!");

            // Iniciando o menu principal
            MainMenu mainMenu = new MainMenu(usuarioService, relatorioService);
            mainMenu.exibirMenu();

        } catch (Exception e) {
            System.err.println("Erro ao inicializar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
