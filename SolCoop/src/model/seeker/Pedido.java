package model.seeker;
// Importações de classes que acho que vá precisar
import model.Usuario;
import model.partner.*;
import model.share.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pedido{     
    public Pedido(int numPedido, String dataCriacao, String nomeClienteShare, int numeroClienteShare, String nomeClienteSeeker, int numClienteSeeker){
        this.numPedido = numPedido;
        this.dataCriacao = dataCriacao;
        this.nomeClienteShare = nomeClienteShare;
        this.numeroClienteShare = numeroClienteShare;
        this.nomeClienteSeeker = nomeClienteSeeker;
        this.numClienteSeeker = numClienteSeeker;

    }
    static Scanner sc = new Scanner(System.in);

    private List<Pedido> listaPedidos = new ArrayList<>();

    private int numPedido;
    private String dataCriacao;
    private String nomeClienteShare;
    private int numeroClienteShare;
    private String nomeClienteSeeker;
    private int numClienteSeeker;
    private String estadoPedido;

    //* INICIO DOS SETS E GETS *//
    /* Set do número do pedido */
    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }
    /* Get do número do pedido */
    public int getNumPedido() {
        return numPedido;
    }
    /* Set da data de criação do pedido */
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    /* Get da data de criação do pedido */
    public String getDataCriacao() {
        return dataCriacao;
    }
    /* Set do nome do cliente Share */
    public void setNomeClienteShare(String nomeClienteShare) {
        this.nomeClienteShare = nomeClienteShare;
    }
    /* Get do nome do cliente Share */
    public String getNomeClienteShare() {
        return nomeClienteShare;
    }
    /* Set do número do cliente Share */
    public void setNumeroClienteShare(int numeroClienteShare) {
        this.numeroClienteShare = numeroClienteShare;
    }
    /* Get do número do cliente Share */
    public int getNumeroClienteShare() {
        return numeroClienteShare;
    }
    /* Set do nome do cliente Seeker */
    public void setNomeClienteSeeker(String nomeClienteSeeker) {
        this.nomeClienteSeeker = nomeClienteSeeker;
    }
    /* Get do nome do cliente Seeker */
    public String getNomeClienteSeeker() {
        return nomeClienteSeeker;
    }
    /* Set do número do cliente Seeker */
    public void setNumClienteSeeker(int numClienteSeeker) {
        this.numClienteSeeker = numClienteSeeker;
    }
    /* Get do número do cliente Seeker */
    public int getNumClienteSeeker() {
        return numClienteSeeker;
    }
    /* Set do estado do pedido NÃO USADO POR ENQUANTO */
    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    /* Get do estado do pedido NÃO USADO POR ENQUANTO */ 
    public String getEstadoPedido() {
        return estadoPedido;
    }
    //* FIM DOS SETS E GETS *//
    
    /* Método para criar um pedido */
    public void criarPedido(){//Aqui deveria puxar do seeker a nova compra de energia e do share a venda de energia e preencheria automaticamente
    }

    /* Método para finalizar e/ou cancelar um pedido */
    public void aprovarOuCancelarPedido(Scanner sc, Pedido pedido) {
        do{
            System.out.println("Você deseja aprovar o pedido? (s/n)");
            String resp = sc.next();
        
                if (resp.equalsIgnoreCase("s")) {
                    System.out.println("Prosseguindo para o pagamento...");
                    // Precisa do camninho para o pagamento AQUI
                    break; // por enquanto que não temos o caminho para o pagamento

                } else if (resp.equalsIgnoreCase("n")) {/* Confirmando que deseja cancelar e excluir o pedido */
                    System.out.println("Você tem certeza de que deseja cancelar e excluir o pedido? (s/n)");
                    resp = sc.next();
        
                    if (resp.equalsIgnoreCase("s")) {
                        System.out.println("Cancelando o pedido...");
                    } else {
                        System.out.println("O pedido não foi cancelado.");
                    }
                } else {
                    System.out.println("Resposta inválida. Por favor, responda com 's' para sim ou 'n' para não.");
                }
        }while (true);
    }

}



