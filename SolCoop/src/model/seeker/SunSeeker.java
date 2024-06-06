package model.seeker;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Declaração do SunSeeker */
public class SunSeeker extends Usuario{
     Scanner sc = new Scanner(System.in);
        public SunSeeker(String nome, String email, String senha, String numeroTelefone, String endereco) {
            super(nome, email, senha, numeroTelefone, endereco);
            this.ultimasContas = new ArrayList<>();
        }
        protected float energiaDesejada;
        public float valorTributo;
        protected List<Float> ultimasContas;
        public float margemErro = .15f; // 15% de margem de erro

        //*INICIO DOS SET E GETS DO SunSeeker *//
        /* SET da energia desejada */
        public void setEnergiaDesejada(float energiaDesejada) {
            this.energiaDesejada = energiaDesejada;
        }

        /* GET da energia desejada */
        public float getEnergiaDesejada() {
            return energiaDesejada;
        }

        /* SET do valor do tributo */
        public void setValorTributo(float valorTributo) {
            this.valorTributo = valorTributo;
        }

        /* GET do valor do tributo */
        public float getValorTributo() {
            return valorTributo;
        }

        /* SET das ultimas contas */
        public void setUltimasContas(List<Float> ultimasContas) {
            this.ultimasContas = ultimasContas;
        }

        /* GET das ultimas contas */
        public List<Float> getUltimasContas() {
            return ultimasContas;
        }

        //*FIM DOS SET E GETS DO SunSeeker *//

        //*Inicio dos metodos para calcular o preço da energioa *//
        /* Metodo para calcular a media de consumo das ultimas contas */
        public float calcularMediaConsumo(){
            float soma = 0;
            for (Float conta : ultimasContas) {
                soma += conta;
            }
            return soma/ultimasContas.size();
        }
        public float consumoTotal(){
            return calcularMediaConsumo() + margemErro;
        }
        /* Metodo para calcular o valor da conta de energia */
        public float calcularPrecoFinal(float mediaConsumo){
            return consumoTotal() * valorTributo;
        }

        /* Metodo para adicionar uma conta a lista de contas */
        public void adicionarConta(float conta){
            ultimasContas.add(conta);
        }
        //*Fim dos metodos para calcular o preço da energioa *//

        //*Inicio dos metodos para interagir com o SunSeeker *//
        /*Metodo para recolher o valor do tributo da energia */
        public void recolherTributo(){
            System.out.println("Primeiro informe o valor do tributo da energia: " );
            setValorTributo(sc.nextFloat());
        }
        /* Metodo para adicionar uma conta a lista de contas */
        public void adicionarConta(){
            for (int i = 1; i <= 12; i++) {
                System.out.println("Informe o valor da conta [" + i + "] (em kWh): ");
                float conta = sc.nextFloat();
                ultimasContas.add(conta);
            
            }        
        }
    public static void main(String[] args) {
        SunSeeker sunSeeker = new SunSeeker("nome", "email", "senha", "numeroTelefone", "endereco");
        sunSeeker.recolherTributo();
        sunSeeker.adicionarConta();
        System.out.printf("A media de consumo é: %.2f kWh\n", sunSeeker.calcularMediaConsumo());
        System.out.printf("O preço da energia é: R$ %.2f\n", sunSeeker.calcularPrecoFinal(0) );  

    }
        
}
