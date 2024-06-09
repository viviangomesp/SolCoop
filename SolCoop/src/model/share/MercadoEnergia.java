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
            System.out.println("ID SunShare: " + oferta.getIdUsuario() + " | Energia disponível: " + oferta.getEnergiaDisponivel() + " kWh");
        }
    }
    
    public static void main(String[] args) {
        MercadoEnergia mercadoEnergia = new MercadoEnergia();
        mercadoEnergia.listarOfertas();
    }
}
