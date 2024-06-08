package model;

import java.util.Scanner;

import model.share.SunShare;
public class Usuario {
    
    protected int idUsuario;
    private String nome; //variaveis protegidas
    private String email;
    private String senha;
    private String numeroTelefone;
    private String endereco;

    public Usuario(int idUsuario, String nome, String email, String senha, String numeroTelefone, String endereco) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;
        this.endereco = endereco;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public static void menu () { //add id do usuario
        Scanner sc = new Scanner(System.in);
        int opcao;
        System.out.println("\nBem vindo ao SolCoop! Aqui você pode compartilhar e comprar energia solar!");
        System.out.println("1 - Registar novo usuario");
        System.out.println("2 - Listar ofertas de energia");
        System.out.println("3 - Sair");
        opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                registarNovoUsuario();
                break;
            case 2:
                System.out.println("Listar ofertas de energia"); //add metodo depois
                break;
            case 3:
                System.out.println("Sair");
                break;
            default:
                System.out.println("Opção inválida!");
                System.out.println("Digite uma opção válida (1, 2 ou 3)");
                opcao = sc.nextInt();
                break;
        }
    }

    public static void registarNovoUsuario (){
        Scanner sc = new Scanner(System.in);
        int tipoUsuario;
        System.out.println("\n\n/// Cadastro de novo usuário ///");

        System.out.println("Digite o nome do usuário: ");
        String nome = sc.nextLine();

        System.out.println("Digite o email do usuário: ");
        String email = sc.nextLine();

        System.out.println("Digite a senha do usuário: ");
        String senha = sc.nextLine();
        
        System.out.println("Digite o número de telefone do usuário: ");
        String numeroTelefone = sc.nextLine();

        System.out.println("Digite o endereço do usuário: ");
        String endereco = sc.nextLine();

        System.out.println("Agora você é um usuário do SolCoop! Seja bem vindo, " + nome + "!\n");
        System.out.println("Escolha qual tipo de usuário você se identifica: ");
        System.out.println("1 - SunShare - Usuario que compartilha a energia gerada através de painéis solares.");
        System.out.println("2 - SunSeeker - Usuario que compra energia gerada através de painéis solares.");
        System.out.println("3 - SunPartner - Usuário que gostaria de dividir uma nova compra junto a outro usuário de placa solar.");
        tipoUsuario = sc.nextInt();

        switch (tipoUsuario) {
            case 1:
                System.out.println("\nVocê escolheu ser um SunShare!");
                System.out.println("Você se tornou um compartilhador de energia solar! Por favor, cadastre os dados da sua geração de energia:\n");
                SunShare sunShare = new SunShare(0, nome, email, senha, numeroTelefone, endereco);
                sunShare.entradaDados();
                sunShare.verificarMercado();
                break;
            case 2:
                System.out.println("Você escolheu ser um SunSeeker!");
                //metodos do seeker
                break;
            case 3:
                System.out.println("Você escolheu ser um SunPartner!");
                //metodos do partner
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        
    }
    
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario(0, "nome", "email", "senha", "numeroTelefone", "endereco");
        usuario1.registarNovoUsuario();
    }
}
