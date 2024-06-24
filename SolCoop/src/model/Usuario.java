package model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import model.partner.SunPartner;
import model.seeker.Pedido;
import model.seeker.SunSeeker;
import model.share.MercadoEnergia;
import model.share.Pagamento;
import model.share.SunShare;
public class Usuario {

    protected static int contadorId = 1;
    protected int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String numeroTelefone;
    private Endereco endereco;
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    static MercadoEnergia mercado = new MercadoEnergia();
    static Pagamento pagamento = new Pagamento();
    

    public Usuario(int idUsuario, String nome, String email, String senha, String numeroTelefone, Endereco endereco) {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static void adicionarUsuariosTestes() {
        Usuario usuario1 = new SunSeeker(contadorId++, "Pedro", "pedroteste@gmail.com", "teste123", "71999999999", new Endereco("Cidade1", "Bairro1", "Rua1", "1", "11111-111"));
        Usuario usuario2 = new SunShare(contadorId++, "Felipe", "felipeteste@gmail.com", "teste123", "71999999999", new Endereco("Cidade2", "Bairro2", "Rua2", "2", "22222-222"), new MercadoEnergia());
        Usuario usuario3 = new SunSeeker(contadorId++, "Emily", "emilyteste@gmail.com", "teste123", "71999999999", new Endereco("Cidade3", "Bairro3", "Rua3", "3", "33333-333"));
        Usuario usuario4 = new SunShare(contadorId++, "Beatriz", "beatrizteste@gmail.com", "teste123", "71999999999", new Endereco("Cidade4", "Bairro4", "Rua4", "4", "44444-444"), new MercadoEnergia());
        Usuario usuario5 = new SunShare(contadorId++, "Ana", "anateste@gmail.com", "teste123", "71999999999", new Endereco("Cidade5", "Bairro5", "Rua5", "5", "55555-555"), new MercadoEnergia());
        listaUsuarios.add(usuario1);
        listaUsuarios.add(usuario2);
        listaUsuarios.add(usuario3);
        listaUsuarios.add(usuario4);
        listaUsuarios.add(usuario5);
    }

    public static void listarUsuarios() {
        System.out.println("\nListar todos usuarios do SolCoop: ");
        for (Usuario usuario : listaUsuarios) {
            System.out.println("ID: " + usuario.getIdUsuario() + " | Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail() + " | Telefone: " + usuario.getNumeroTelefone() + " | Endereço: " + usuario.getEndereco());
        }
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("\nBem vindo ao SolCoop! Aqui você pode compartilhar e comprar energia solar!");
            System.out.println("1 - Registar novo usuario");
            System.out.println("2 - Listar ofertas de energia");
            System.out.println("3 - Listar todos usuarios");
            System.out.println("4 - Sair");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    registarNovoUsuario();
                    break;
                case 2:
                    System.out.println("Listar ofertas de energia");
                    MercadoEnergia mercadoEnergia = new MercadoEnergia();
                    mercadoEnergia.adicionarOfertasTeste();
                    mercadoEnergia.listarOfertas();
                    break;
                case 3:
                    listarUsuarios();
                    break;
                case 4:
                    System.out.println("Saindo do SolCoop... Até mais!");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    System.out.println("Digite uma opção válida (1, 2 ou 3)");
                    break;
            }
        }
    }

    public static Usuario getUsuarioById(int idUsuario) {//Procura um usuário pelo ID e retorna o usuário
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public static void registarNovoUsuario() {//ajustar endereco
        Scanner sc = new Scanner(System.in);
        int tipoUsuario;
        int novoId = contadorId++; //Gera um novo ID para o usuário

        System.out.println("\n\n/// Cadastro de novo usuário ///");

        System.out.println("Digite o nome do usuário: ");
        String nome = sc.nextLine();

        System.out.println("Digite o email do usuário: ");
        String email = sc.nextLine();

        System.out.println("Digite a senha do usuário: ");
        String senha = sc.nextLine();

        System.out.println("Digite o número de telefone do usuário: ");
        String numeroTelefone = sc.nextLine();

        System.out.println("Digite a cidade do usuário: ");
        String cidade = sc.nextLine();

        System.out.println("Digite o bairro do usuário: ");
        String bairro = sc.nextLine();

        System.out.println("Digite a rua do usuário: ");
        String rua = sc.nextLine();

        System.out.println("Digite o número da casa do usuário: ");
        String numero = sc.nextLine();

        System.out.println("Digite o CEP do usuário: ");
        String endereco = sc.nextLine();

        Endereco enderecoUsuario = new Endereco(cidade, bairro, rua, numero, endereco);

        System.out.println("Agora você é um usuário do SolCoop! Seja bem vindo, " + nome + "!\n");

        System.out.println("Escolha qual tipo de usuário você se identifica: ");
        System.out.println("1 - SunShare - Usuario que compartilha a energia gerada através de painéis solares.");
        System.out.println("2 - SunSeeker - Usuario que compra energia gerada através de painéis solares.");
        System.out.println("3 - SunPartner - Usuário que gostaria de dividir uma nova compra, junto a outro usuário, de placa solar.");
        tipoUsuario = sc.nextInt();

        Usuario usuario = null;//Se não for inicializado, o compilador não deixa compilar

        switch (tipoUsuario) {
            case 1:
                System.out.println("\nVocê escolheu ser um SunShare!");
                System.out.println("Você se tornou um compartilhador de energia solar! Por favor, cadastre os dados da sua geração de energia:\n");
                SunShare sunShare = new SunShare(novoId, nome, email, senha, numeroTelefone, new Endereco(nome, email, senha, numeroTelefone, endereco), new MercadoEnergia());
                sunShare.entradaDados();
                sunShare.verificarMercado();
                listaUsuarios.add(sunShare);
                break;

            case 2:
                System.out.println("\nVocê escolheu ser um SunSeeker!");
                SunSeeker sunSeeker = new SunSeeker(novoId, nome, email, senha, numeroTelefone, new Endereco(nome, email, senha, numeroTelefone, endereco));
                sunSeeker.ColetandoDados();
                sunSeeker.PrintValorFinal();
                listaUsuarios.add(sunSeeker);
                System.out.println();//Pular linha
                mercado.adicionarOfertasTeste();
                mercado.listarOfertas();
                mercado.solicitarCompraEnergia();
                mercado.criarPedido(tipoUsuario, novoId, nome);
                Pagamento.processarPagamento(mercado, 1);
                
                break;
            case 3: //nao está funcionando os métodos
                System.out.println("\nVocê escolheu ser um SunPartner!");
                SunPartner sunPartner = new SunPartner(novoId, nome, email, senha, numeroTelefone, new Endereco(nome, email, senha, numeroTelefone, endereco), "cep", SunPartner.solicitarUltimasContas(), new ArrayList<String>());
                SunPartner.solicitarInformacoesDoUsuario();
                sunPartner.solicitarOrcamento();
                listaUsuarios.add(sunPartner);
                break;
            default:
                System.out.println("Opção inválida!");
                System.out.println("Digite uma opção válida (1, 2 ou 3)");
                break;
        }

    }
    public static void main(String[] args) {
        adicionarUsuariosTestes();
        menu();
    }
}
