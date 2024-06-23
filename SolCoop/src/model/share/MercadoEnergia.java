package model.share;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.seeker.Pedido;

public class MercadoEnergia {

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
        OfertaEnergia oferta1 = new OfertaEnergia(30, 100);
        OfertaEnergia oferta2 = new OfertaEnergia(31, 200);
        OfertaEnergia oferta3 = new OfertaEnergia(32, 300);
        OfertaEnergia oferta4 = new OfertaEnergia(33, 400);
        OfertaEnergia oferta5 = new OfertaEnergia(34, 500);
        ofertasEnergia.add(oferta1);
        ofertasEnergia.add(oferta2);
        ofertasEnergia.add(oferta3);
        ofertasEnergia.add(oferta4);
        ofertasEnergia.add(oferta5);
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
    
    private void criarPedido(int idVendedor, int idComprador, String nomeComprador) {
        Usuario vendedor = Usuario.getUsuarioById(idVendedor);//Procura o vendedor pelo ID
        Usuario comprador = Usuario.getUsuarioById(idComprador);//Procura o comprador pelo ID
        if (vendedor != null && comprador != null) {//Se o vendedor e o comprador existirem, cria o pedido
            Pedido pedido = new Pedido(vendedor.getNome(), idVendedor, comprador.getNome(), idComprador);
            pedidos.add(pedido);
            System.out.println("Pedido criado com sucesso!");
            pedido.imprimirPedido();;
        } else { //Caso nao existirem, exibe mensagem de erro
            System.out.println("Erro ao criar o pedido: Usuário não encontrado.");
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
        mercadoEnergia.adicionarOfertasTeste();
        mercadoEnergia.listarOfertas();
    }
}
