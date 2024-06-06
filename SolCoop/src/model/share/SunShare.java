package model.share;

import model.Usuario;
import java.util.Scanner;

public class SunShare extends Usuario {
    protected float energiaDisponivel;
    protected float energiaCompartilhada;
    protected float energiaTotal;

    public SunShare(String nome, String email, String senha, String numeroTelefone, String endereco) {
        super(nome, email, senha, numeroTelefone, endereco);
    }

    public float getEnergiaDisponivel() {
        return energiaDisponivel;
    }

    public void setEnergiaDisponivel(float energiaDisponivel) {
        this.energiaDisponivel = energiaDisponivel;
    }

    public float getEnergiaCompartilhada() {
        return energiaCompartilhada;
    }

    public void setEnergiaCompartilhada(float energiaCompartilhada) {
        this.energiaCompartilhada = energiaCompartilhada;
    }

    public float getEnergiaTotal() {
        return energiaTotal;
    }

    public void setEnergiaTotal(float energiaTotal) {
        this.energiaTotal = energiaTotal;
    }
        
    
    public void entradaDados () {
        Scanner sc = new Scanner(System.in);
        System.out.println("/// Cadastro de novo item no Mercado de Energia ///");
        System.out.println("Aqui você cadastra todos os dados da sua geração de energia solar!");
        System.out.println("Digite o total de energia que você gera: ");
        setEnergiaTotal(energiaTotal = sc.nextFloat());
        System.out.println("Digite a energia que você já compartilha (se houver): ");
        setEnergiaCompartilhada(energiaCompartilhada = sc.nextFloat());
        System.out.println("Digite a energia disponivel para a venda: ");
        setEnergiaDisponivel(energiaDisponivel = sc.nextFloat());
    }

    public void imprimirShare () {
        System.out.println("/// Confira os dados cadastrados abaixo ///");
        System.out.println("Energia total: " + getEnergiaTotal());
        System.out.println("Energia compartilhada: " + getEnergiaCompartilhada());
        System.out.println("Energia disponível: " + getEnergiaDisponivel());
    }

    public void verificarMercado() {
        System.out.println("/// Verificação do Mercado de Energia ///");
        System.out.println("Por favor, confirme se todos os dados estão corretos:");
        imprimirShare();
        System.out.println("Você está ciente de que esses dados serão enviados para o Mercado de Energia, deseja continuar? (S/N)");

        Scanner scanner = new Scanner(System.in);
        String resposta = scanner.nextLine(); //Criando a variável local para resposta do usuário

        if (resposta.equalsIgnoreCase("S")) {
            // Enviar os dados para o Mercado de Energia
            System.out.println("Dados enviados para o Mercado de Energia.");
            ////////// Aqui você pode chamar um método que envia os dados para o Mercado de Energia/////////
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    public static void main(String[] args) {
        SunShare share1 = new SunShare();
        share1.entradaDados();
        share1.verificarMercado();
    }
}

