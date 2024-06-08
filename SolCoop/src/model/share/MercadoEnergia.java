package model.share;

import java.util.ArrayList;
import java.util.List;

public class MercadoEnergia {
    public List<Float> ofertasEnergia;
    SunShare sunShare; //Importando a classe SunShare para utilizar suas informações

    public MercadoEnergia(List<Float> ofertasEnergia) {
        this.ofertasEnergia = ofertasEnergia;
    }

    public void listarOfertas() {
        System.out.println("\nOfertas disponíveis no mercado de energia:");
        for (int i = 0; i < ofertasEnergia.size(); i++) {
            System.out.println(sunShare.getIdUsuario() + sunShare.getEnergiaDisponivel());
        }

    }

    public static void main(String[] args) {
        MercadoEnergia mercadoEnergia = new MercadoEnergia(new ArrayList<>());
        mercadoEnergia.listarOfertas();
    }

}
