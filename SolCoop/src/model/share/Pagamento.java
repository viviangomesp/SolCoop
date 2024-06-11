package model.share;

import model.seeker.Pedido;
import java.util.Scanner;

public class Pagamento {

    public static void processarPagamento(MercadoEnergia mercadoEnergia, int idPedido) {
        Pedido pedido = mercadoEnergia.getPedidoById(idPedido);
        if (pedido != null) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Deseja aceitar o pedido? (S/N): ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                pedido.setStatus("Concluído");
                System.out.println("Pedido aceito e concluído!");
            } else {
                System.out.println("Pedido recusado!");
            }
        } else {
            System.out.println("Pedido não encontrado!");
        }
    }
}

