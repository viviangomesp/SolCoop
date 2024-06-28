package model.share;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.contrato.Contrato;
import model.seeker.Pedido;
import model.share.Pagamento;

public class MercadoEnergia {
    Scanner sc = new Scanner(System.in);

    private List<OfertaEnergia> ofertasEnergia; // Lista de ofertas de energia
    private List<Pedido> pedidos; // Lista de pedidos de energia
    private boolean ofertasTesteAdd = false;

    public MercadoEnergia() {// Inicializa as listas de ofertas e pedidos
        this.ofertasEnergia = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void addOferta(SunShare sunShare) {
        OfertaEnergia oferta = new OfertaEnergia(sunShare.getIdUsuario(), sunShare.getEnergiaDisponivel());
        ofertasEnergia.add(oferta);// Adiciona a oferta na lista de ofertas
    }

    public void listarOfertas() {// Lista as ofertas de energia disponíveis
        System.out.println("Bem-vindo ao Mercado de Energia! Aqui você encontra as ofertas de energia disponíveis:");
        System.out.println("/// Mercado de Energia - Ofertas disponíveis ///");

        for (OfertaEnergia oferta : ofertasEnergia) { // Percorre a lista de ofertas e imprime o ID do usuário e a energia disponível
            System.out.println("ID Usuario (SunShare): " + oferta.getIdUsuario() + " | Energia disponível: " + oferta.getEnergiaDisponivel() + " kWh");
        }
    }
    // Atualiza o mercado de energia
    public void atualizarMercado() {
        // Remover ofertas com energia disponível igual a zero
        ofertasEnergia.removeIf(oferta -> oferta.getEnergiaDisponivel() <= 0);
        // Remover pedidos concluídos
        pedidos.removeIf(pedido -> "Concluído".equals(pedido.getStatus()));
    }
    // Atualiza o mercado de energia e IMPRIME as ofertas
    public void atualizarMercadoPrint() {
        // Remover ofertas com energia disponível igual a zero
        ofertasEnergia.removeIf(oferta -> oferta.getEnergiaDisponivel() <= 0);
        // Remover pedidos concluídos
        pedidos.removeIf(pedido -> "Concluído".equals(pedido.getStatus()));
        listarOfertas();
    }

    // Adicionando 3 ofertas para preencher a lista de ofertas
    public void adicionarOfertasTeste() {
        if (ofertasTesteAdd) {//Controle para saber se a oferta teste já foi inserida
            return;
        }
        List<Usuario> listaUsuarios = Usuario.getListaUsuarios();// Importa a lista de usuários
        int idShare1 = listaUsuarios.get(1).getIdUsuario();
        int idShare2 = listaUsuarios.get(3).getIdUsuario();
        int idShare3 = listaUsuarios.get(4).getIdUsuario();

        OfertaEnergia oferta1 = new OfertaEnergia(idShare1, 250);
        OfertaEnergia oferta2 = new OfertaEnergia(idShare2, 820);
        OfertaEnergia oferta3 = new OfertaEnergia(idShare3, 500);

        ofertasEnergia.add(oferta1);
        ofertasEnergia.add(oferta2);
        ofertasEnergia.add(oferta3);

        ofertasTesteAdd = true;
    }

    public boolean comprarEnergia(int idOferta, float quantidade) {
        for (OfertaEnergia oferta : ofertasEnergia) {// Percorre a lista de ofertas, se a oferta for encontrada e a quantidade de energia disponível for suficiente
            if (oferta.getIdUsuario() == idOferta && oferta.getEnergiaDisponivel() >= quantidade) {
                oferta.setEnergiaDisponivel(oferta.getEnergiaDisponivel() - quantidade);
                // Se a energia disponível for 0, remove a oferta
                if (oferta.getEnergiaDisponivel() == 0) {
                    ofertasEnergia.remove(oferta);
                }
                return true;
            }
        }
        return false;
    }

    public void criarPedido(int idVendedor, int idComprador, String nomeComprador) {
        Usuario vendedor = Usuario.getUsuarioById(idVendedor); // Busca o vendedor pelo ID
        Usuario comprador = Usuario.getUsuarioById(idComprador); // Busca o comprador pelo ID

        if (vendedor != null && comprador != null) { // Se o vendedor e o comprador existirem, cria o pedido
            Pedido pedido = new Pedido(vendedor.getNome(), idVendedor, comprador.getNome(), idComprador);
            pedidos.add(pedido);
            System.out.println("\nPedido criado com sucesso!");
            pedido.imprimirPedido();
        } else {
                System.out.println("ERRO: Usuário não encontrado.\n");
        }
    }

    /* metodo para solicitar id do vendedor e a quantidade de energia que deseja comprar */
    public void solicitarCompraEnergia() {
        Scanner sc = new Scanner(System.in);
        int idVendedor = 0;
        float quantidade = 0;
        boolean compraValida = false;

        while (!compraValida) {
            atualizarMercadoPrint();
            System.out.println("\nDigite o ID do vendedor (SunShare) que deseja comprar energia: ");
            idVendedor = sc.nextInt();
            System.out.println("Digite a quantidade de energia que deseja comprar (em kWh): ");
            quantidade = sc.nextFloat();

            Usuario vendedor = Usuario.getUsuarioById(idVendedor);
            if (vendedor == null) {
                System.out.println("ERRO: Vendedor não encontrado.\n");
                continue;
            }

            if (quantidade <= 0) {
                System.out.println("ERRO: Quantidade inválida.\n");
                continue;
            }

            if (!comprarEnergia(idVendedor, quantidade)) {
                System.out.println("Erro ao realizar a compra: Oferta não encontrada ou quantidade insuficiente.");
                continue;
            }

            compraValida = true;
            System.out.println("\nConfira os dados da compra abaixo:");
            System.out.println("ID do vendedor: " + idVendedor + " | Quantidade de energia: " + quantidade + " kWh");
            System.out.println("\nDeseja continuar a compra (S/N)?\nA seguir será exibido o contrato de compra de energia. É necessário aceitar para finalizar o pedido.");
            String continuarCompra = sc.next();

            if (continuarCompra.equalsIgnoreCase("S")) {
                Contrato contrato = new Contrato();
                boolean aceitouContrato = contrato.termosContrato();
                if (!aceitouContrato) {
                    System.out.println("Contrato não aceito. Compra cancelada.");
                    System.out.println("Voltando para o menu principal...\n");
                } else {
                    criarPedido(idVendedor, Usuario.getUsuarioById(idVendedor).getIdUsuario(), "");
                    Pagamento.processarPagamento(this, pedidos.get(pedidos.size() - 1).getIdPedido());
                }
            } else {
                System.out.println("Compra não realizada. Voltando para o menu principal...\n");
            }
        }
    }

    public Pedido getPedidoById(int idPedido) {// Procura um pedido pelo ID e retorna o pedido
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }
        return null;
    }

    public void listarPedidos() {// Metodo para listar os pedidos
        System.out.println("/// Mercado de Energia - Pedidos de energia ///");
        for (Pedido pedido : pedidos) {
            pedido.imprimirPedido();
        }
    }

    public static void main(String[] args) {
        MercadoEnergia mercadoEnergia = new MercadoEnergia();
        Usuario.adicionarUsuariosTestes();
        mercadoEnergia.adicionarOfertasTeste();
        mercadoEnergia.solicitarCompraEnergia();
        mercadoEnergia.atualizarMercado();
    }
}