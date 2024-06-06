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
        boolean compartilharEnergia = false;//variavel local para capturar se o usuario já compartilha energia

        System.out.println("/// Cadastro de novo item no Mercado de Energia ///");
        System.out.println("Aqui você cadastra todos os dados da sua geração de energia solar!");

        System.out.println("Digite o total de energia que você gera (em kWh): ");
        setEnergiaTotal(energiaTotal = sc.nextFloat());

        System.out.println("Digite a energia que você já compartilha (S/N): ");
        compartilharEnergia = sc.next().equalsIgnoreCase("S");
        if (compartilharEnergia) {
            System.out.println("Digite a energia que você compartilha (em kWh): ");
            setEnergiaCompartilhada(energiaCompartilhada = sc.nextFloat());
        } else {
            System.out.println("Você não compartilha energia.");
            setEnergiaCompartilhada(energiaCompartilhada = 0);
        }

        System.out.println("Digite a energia disponivel para a venda (em kWh): ");
        setEnergiaDisponivel(energiaDisponivel = sc.nextFloat());

        sc.close();
    }

    public void imprimirShare () {
        System.out.println("/// Confira os dados cadastrados abaixo ///");
        System.out.println("Energia total: " + getEnergiaTotal());
        System.out.println("Energia compartilhada: " + getEnergiaCompartilhada());
        System.out.println("Energia disponível: " + getEnergiaDisponivel());
    }

    public void verificarMercado() {
        Scanner sc = new Scanner(System.in);
        boolean confirmaMercado = false;
        System.out.println("/// Verificação do Mercado de Energia ///");
        System.out.println("Por favor, confirme se todos os dados estão corretos:");

        imprimirShare();

        System.out.println("Você está ciente de que esses dados serão enviados para o Mercado de Energia, deseja continuar? (S/N)");
        confirmaMercado = sc.next().equalsIgnoreCase("S");
        if (confirmaMercado) {
            System.out.println("Dados enviados com sucesso!");
            /// Aqui vai o código para enviar os dados para o Mercado de Energia///
        } else {
            System.out.println("Operação cancelada.");
            System.out.println("Por favor, digite novamente os dados.");
            entradaDados();
        }
        sc.close();
    }

    public void importarPrecoVenda() {
        // Aqui vai o código para importar o metodo que cuida do preço de custo da energia do seeker //  
        // Importa a mediaConsumo + 15% //
    }

    public static void main(String[] args) {
        SunShare share1 = new SunShare("nome", "email", "senha", "numeroTelefone", "endereco");
        share1.entradaDados();
        share1.verificarMercado();
    }
}

