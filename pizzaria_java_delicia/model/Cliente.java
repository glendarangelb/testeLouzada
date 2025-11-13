package model;

public class Cliente {
    private String nome;
    private String telefone;
    private String email;

    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Cliente{name='%s', telefone='%s', email='%s'}", nome, telefone, email);
    }
}
