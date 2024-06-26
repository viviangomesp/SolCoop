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

    private List<OfertaEnergia> ofertasEnergia; //Lista de ofertas de energia
    private List<Pedido> pedidos; //Lista de pedidos de energia

    public MercadoEnergia() {//Inicializa as listas de ofertas e pedidos
        this.ofertasEnergia = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void addOferta (SunShare sunShare){
        OfertaEnergia oferta = new OfertaEnergia(sunShare.getIdUsuario(), sunShare.getEnergiaDisponivel());
        ofertasEnergia.add(oferta);// Adiciona a oferta na lista de ofertas
    }

    public void listarOfertas(){// Lista as ofertas de energia disponíveis
        System.out.println("Bem-vindo ao Mercado de Energia! Aqui você encontra as ofertas de energia disponíveis:");
        System.out.println("/// Mercado de Energia - Ofertas disponíveis ///");
        for (OfertaEnergia oferta : ofertasEnergia){
            System.out.println("ID Usuario (SunShare): " + oferta.getIdUsuario() + " | Energia disponível: " + oferta.getEnergiaDisponivel() + " kWh");
        }
    }

    //Adicionando 5 ofertas para preencher a lista de ofertas
    public void adicionarOfertasTeste(){
    List<Usuario> listaUsuarios = Usuario.getListaUsuarios();//Importa a lista de usuários
    int idShare1 = listaUsuarios.get(1).getIdUsuario();
    int idShare2 = listaUsuarios.get(3).getIdUsuario();
    int idShare3 = listaUsuarios.get(4).getIdUsuario(); 

    OfertaEnergia oferta1 = new OfertaEnergia(idShare1, 250);
    OfertaEnergia oferta2 = new OfertaEnergia(idShare2, 820);
    OfertaEnergia oferta3 = new OfertaEnergia(idShare3, 500);

    ofertasEnergia.add(oferta1);
    ofertasEnergia.add(oferta2);
    ofertasEnergia.add(oferta3);
    }

    public boolean comprarEnergia(int idOferta, float quantidade) {
        for (OfertaEnergia oferta : ofertasEnergia) {//Percorre a lista de ofertas
            //Se a oferta for encontrada e a quantidade de energia disponível for suficiente
            if (oferta.getIdUsuario() == idOferta && oferta.getEnergiaDisponivel() >= quantidade) {
                oferta.setEnergiaDisponivel(oferta.getEnergiaDisponivel() - quantidade);
                //Se a energia disponível for 0, remove a oferta
                if (oferta.getEnergiaDisponivel() == 0) {
                    ofertasEnergia.remove(oferta);
                }
                return true;
            }
        }
        return false;
    }

    public void criarPedido(int idVendedor, int idComprador, String nomeComprador) {
        Usuario vendedor = null; // Inicializa o vendedor como nulo
        Usuario comprador = null; // Inicializa o comprador como nulo
        do {
            vendedor = Usuario.getUsuarioById(idVendedor); // Busca o vendedor pelo ID
            comprador = Usuario.getUsuarioById(idComprador); // Busca o comprador pelo ID
            if (vendedor != null && comprador != null) { // Se o vendedor e o comprador existirem, cria o pedido
                Pedido pedido = new Pedido(vendedor.getNome(), idVendedor, comprador.getNome(), idComprador);
                pedidos.add(pedido);
                System.out.println("Pedido criado com sucesso!");
                pedido.imprimirPedido();
            } else { // Caso nao existirem, exibe mensagem de erro e solicita novamente o ID do vendedor e a quantidade de energia
                System.out.println("ERRO: Usuário não encontrado.\n");
                System.out.println("Por favor, digite os seguintes dados corretamente: ");
                solicitarCompraEnergia();
            }
        } while (vendedor == null || comprador == null);
    }

    /* metodo para solicitar id do vendedor e a quantidade de energia que deseja comprar */
    public void solicitarCompraEnergia() {
        Scanner sc = new Scanner(System.in);
        int idVendedor = 0;
        float quantidade = 0;
        String continuarCompra;
        do {
            System.out.println("\nDigite o ID do vendedor (SunShare) que deseja comprar energia: ");
            idVendedor = sc.nextInt();
            System.out.println("Digite a quantidade de energia que deseja comprar (em kWh): ");
            quantidade = sc.nextFloat();
            
            Usuario vendedor = Usuario.getUsuarioById(idVendedor);
            if (vendedor == null || quantidade <= 0) {
                System.out.println("ERRO: Vendedor não encontrado ou quantidade inválida.\n");
            }
        } while (Usuario.getUsuarioById(idVendedor) == null || quantidade <= 0);

        if (comprarEnergia(idVendedor, quantidade)) {
            System.out.println("ID do vendedor: " + idVendedor + " | Quantidade de energia: " + quantidade + " kWh");
            System.out.println("Deseja continuar a compra (S/N)?\nA seguir será exibido o contrato de compra de energia. É necessário aceitar para finalizar o pedido.");
            continuarCompra = sc.next();
            if (continuarCompra.equalsIgnoreCase("S")) {
                Contrato contrato = new Contrato();
                boolean aceitouContrato = contrato.termosContrato();
            } else {
                System.out.println("Contrato nao aceito. Compra cancelada.");
                return;
            }
            criarPedido(idVendedor, Usuario.getUsuarioById(idVendedor).getIdUsuario(), Usuario.getUsuarioById(idVendedor).getNome());
            Pagamento pagamento = new Pagamento();
            pagamento.processarPagamento(this, pedidos.get(pedidos.size() - 1).getIdPedido());//Processa o pagamento do pedido criado

        } else {
            System.out.println("Erro ao realizar a compra: Oferta não encontrada ou quantidade insuficiente.");
            System.out.println("Tente novamente a seguir: \n");
            listarOfertas();
            solicitarCompraEnergia();
        }
    }

    public Pedido getPedidoById(int idPedido) {//Procura um pedido pelo ID e retorna o pedido
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }
        return null;
    }

    public void listarPedidos() {//Metodo para listar os pedidos
        System.out.println("/// Mercado de Energia - Pedidos de energia ///");
        for (Pedido pedido : pedidos) {
            pedido.imprimirPedido();
        }
    }
    

    public static void main(String[] args) {
        MercadoEnergia mercadoEnergia = new MercadoEnergia();
        Usuario.adicionarUsuariosTestes();
        mercadoEnergia.adicionarOfertasTeste();
        mercadoEnergia.listarOfertas();
        mercadoEnergia.solicitarCompraEnergia();
    }
}
