package service;

import models.Log;
import models.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Log> logs = new ArrayList<>();

    // Adiciona o  usuário
    public boolean adicionarUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Erro: Usuario invalido.");
            return false;
        }
        if (usuarios.stream().anyMatch(u -> u.getLogin().equalsIgnoreCase(usuario.getLogin()))) {
            System.out.println("Erro: Usuario com login já existente.");
            return false;
        }
        usuarios.add(usuario);
        System.out.println("Usuario adicionado com sucesso: " + usuario.getNome());
        return true;
    }

    // Remove o  usuário
    public boolean removerUsuario(Usuario usuario) {
        if (usuarios.remove(usuario)) {
            System.out.println("Usuario removido com sucesso: " + usuario.getNome());
            return true;
        } else {
            System.out.println("Erro: Usuario nao encontrado.");
            return false;
        }
    }

    // Autenticar  o usuário
    public Usuario autenticarUsuario(String login, String senha) {
        if (login == null || login.isEmpty() || senha == null || senha.isEmpty()) {
            System.out.println("Erro: Login ou senha invalidos.");
            return null;
        }
        return usuarios.stream()
                .filter(u -> u.autenticar(login, senha))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Erro: Login ou senha incorretos.");
                    return null;
                });
    }

    // Registra o  acesso do usuário
    public void registrarAcesso(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Erro: Usuario invalido para registro de acesso.");
            return;
        }
        Log log = new Log(usuario, LocalDateTime.now());
        logs.add(log);
        System.out.println("Acesso registrado: " + usuario.getNome() + " em " + log.getDataHora());
    }

    // Resetar sistema (apagar usuários e logs)
    public void resetarSistema() {
        usuarios.clear();
        logs.clear();
        System.out.println("Sistema resetado com sucesso.");
    }

    // gera lista de usuários 
    public List<Usuario> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    // gera lista de logs 
    public List<Log> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    // faz a bsuca do usuário por nome
    public Usuario buscarUsuarioPorNome(String nome) {
        return usuarios.stream()
                .filter(usuario -> usuario.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    //usuários cadastrados
    public void exibirUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
        } else {
            System.out.println("\n--- Usuarios Cadastrados ---");
            usuarios.forEach(usuario -> System.out.println(usuario));
        }
    }

    //os logs registrados
    public void exibirLogs() {
        if (logs.isEmpty()) {
            System.out.println("Nenhum log registrado.");
        } else {
            System.out.println("\n--- Logs Registrados ---");
            logs.forEach(log -> System.out.println(log));
        }
    }
}
