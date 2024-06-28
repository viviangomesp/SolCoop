package model.share;

import java.util.ArrayList;
import java.util.List;
import model.seeker.Pedido;
import java.util.Scanner;
import model.share.MercadoEnergia;


public class Pagamento {
    private static List<Pedido> pedidos = new ArrayList<>();//Lista de pedidos

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public static void processarPagamento(MercadoEnergia mercadoEnergia, int idPedido) {
        Pedido pedido = mercadoEnergia.getPedidoById(idPedido);//Busca o pedido pelo ID

        if (pedido != null) {//Se o pedido for encontrado, exibe o pedido e pergunta se deseja aceitar
            Scanner sc = new Scanner(System.in);
            System.out.println("\nDeseja aceitar o pedido? (S/N): ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("S")) {//Se a resposta for sim, altera o status do pedido para concluído
                pedido.setStatus("Concluído");
                //Adiciona o pedido na lista de pedidos
                Pagamento.pedidos.add(pedido);
                System.out.println("Pedido aceito e concluído!");
                //exibir imprimir pedido
                Pedido pedido1 = mercadoEnergia.getPedidoById(idPedido);
                pedido1.imprimirPedido();
            } else {//Se a resposta for não, altera o status do pedido para recusado
                System.out.println("Pedido recusado!");
            }
        } else {//Se o pedido não for encontrado, exibe mensagem de erro
            System.out.println("Pedido não encontrado!");
        }
    }
}