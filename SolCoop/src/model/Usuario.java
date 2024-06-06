package model;

import java.util.Scanner;
public class Usuario {
    
    private String nome; //variaveis protegidas
    private String email;
    private String senha;
    private String numeroTelefone;
    private String endereco;

    public Usuario(String nome, String email, String senha, String numeroTelefone, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void entradaDadosUsuario () {
        Scanner sc = new Scanner(System.in);
        System.out.println("/// Cadastro de novo Usuário ///");
        System.out.println("Digite o nome do usuário: ");
        setNome(nome = sc.next());
        System.out.println("Digite o email do usuário: ");
        setEmail(email = sc.next());
        System.out.println("Digite a senha do usuário: ");
        setSenha(senha = sc.next());
        System.out.println("Digite o número de telefone do usuário: ");
        setNumeroTelefone(numeroTelefone = sc.next());
        System.out.println("Digite o endereço do usuário: ");
        setEndereco(endereco = sc.next());
    }
    
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario("nome", "email", "senha", "numeroTelefone", "endereco");
    }
}
