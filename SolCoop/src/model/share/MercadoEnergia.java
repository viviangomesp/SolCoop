package model.share;

import java.util.ArrayList;
import java.util.List;

public class MercadoEnergia {
    private List<OfertaEnergia> ofertasEnergia;

    public MercadoEnergia() {
        this.ofertasEnergia = new ArrayList<>(); // Inicializa a lista de ofertas de energia
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
        OfertaEnergia oferta1 = new OfertaEnergia(30, 750);
        OfertaEnergia oferta2 = new OfertaEnergia(27, 230);
        OfertaEnergia oferta3 = new OfertaEnergia(12, 170);
        OfertaEnergia oferta4 = new OfertaEnergia(15, 300);
        OfertaEnergia oferta5 = new OfertaEnergia(10, 500);
        ofertasEnergia.add(oferta1);
        ofertasEnergia.add(oferta2);
        ofertasEnergia.add(oferta3);
        ofertasEnergia.add(oferta4);
        ofertasEnergia.add(oferta5);
    }
    
    public static void main(String[] args) {
        MercadoEnergia mercadoEnergia = new MercadoEnergia();
        mercadoEnergia.adicionarOfertasTeste();
        mercadoEnergia.listarOfertas();
    }
}
