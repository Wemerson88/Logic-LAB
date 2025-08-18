package models;

public class Professor extends Usuario {

    public Professor(String nome, String login, String email, String senha) {
        super(nome, login, email, senha); 
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + getNome() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
