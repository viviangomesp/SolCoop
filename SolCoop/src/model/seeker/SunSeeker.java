package model.seeker;

import model.Endereco;
import model.Usuario;
import model.share.MercadoEnergia;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Declaração do SunSeeker */
public class SunSeeker extends Usuario {
    Scanner sc = new Scanner(System.in);

    public SunSeeker(int idUsuario, String nome, String email, String senha, String numeroTelefone, Endereco endereco) {
        super(idUsuario, nome, email, senha, numeroTelefone, endereco);
        this.ultimasContas = new ArrayList<>();
    }

    private float mediaConsumo = 0;
    private float energiaDesejada;
    public float valorTributo = 0;
    private List<Float> ultimasContas;
    public float margemErro = .15f; // 15% de margem de erro
    public float consumoTotal = 0;

    // *INICIO DOS SET E GETS DO SunSeeker *//
    /* SET do id do usuario */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /* GET do id do usuario */
    public int getIdUsuario() {
        return idUsuario;
    }
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

    public float getMediaConsumo() {
        return mediaConsumo;
    }

    public void setMediaConsumo(float mediaConsumo) {
        this.mediaConsumo = mediaConsumo;
    }

    // *FIM DOS SET E GETS DO SunSeeker *//

    /* Método para calcular quanto o Seeker vai pagar com adição de 15% de margem de erro e o valor do tributo local */
    public float PrecoFinalSeeker(float conta) {
        /* Adicionar uma conta a lista de contas */
        ultimasContas.add(conta);
    
        /* Calcular a média de consumo */
        float soma = 0;
        for (Float valorConta : ultimasContas) {
            soma += valorConta;
        }
        mediaConsumo = soma / ultimasContas.size();
    
        /* Calcular o consumo total de energia (add margem de erro) */
        consumoTotal = mediaConsumo + (mediaConsumo * margemErro);
    
        /* Calcular o valor da conta de energia */ 
        return consumoTotal * valorTributo;
    }

    /* Método para recolher o valor do tributo da energia */
    public void ColetandoDados() {
        System.out.println("Voce se tornou um comprador de energia limpa! Por favor, cadastre os seus dados: ");
        System.out.println("\nPrimeiro informe o valor do tributo da energia da sua região (Deve aparecer em sua conta de energia)\nExemplo: 0,50 (R$/kWh)");
        System.out.println("O valor do tributo será adicionado ao valor final a ser pago.");
        System.out.println("Informe o valor do tributo: ");
        setValorTributo(sc.nextFloat());
        /* Pedindo as ultimas contas para realização dos cálculos  */
        System.out.println("\n/// Agora pediremos as ultimas 12 contas de energia para calcular sua media de consumo mensal ///");
        System.out.println("Informe consumo de energia em kWh (quilowatt-hora) presente nas contas de energia que sera usado para calcular sua media de consumo.\n");
        for (int i = 1; i <= 12; i++) {
            System.out.println("Informe o valor da conta [" + i + "] (em kWh): ");
            float conta = sc.nextFloat();
            ultimasContas.add(conta);
        }
    }

    /* Método para imprimir o valor a ser pago pelo seeker */
    public void PrintValorFinal(){
        PrecoFinalSeeker(0.0f); // Chamando o método para atualizar as variáveis
        System.out.printf("Sua media de consumo mensal é: %.2f kWh\n", mediaConsumo);
        System.out.printf("O preço final da energia com os 15% margem de seguranca: R$ %.2f\n", PrecoFinalSeeker(0.0f));
    }

    public static void main(String[] args) {
        SunSeeker sunSeeker = new SunSeeker(0, "nome", "email", "senha", "numeroTelefone", new Endereco("cidade", "bairro", "rua", "numero", "cep"));
        sunSeeker.ColetandoDados();
        sunSeeker.PrintValorFinal();
    }

}
