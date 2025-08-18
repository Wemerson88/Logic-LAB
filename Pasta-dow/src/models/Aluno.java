package models;

public class Aluno extends Usuario {
    private String curso;

    public Aluno(String nome, String login, String email, String senha, String curso) {
        super(nome, login, email, senha); 
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + getNome() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
