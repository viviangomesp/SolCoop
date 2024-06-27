package model.partner;

import model.Endereco;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class SunPartner extends Usuario {
    public SunPartner(int idUsuario, String nome, String email, String senha, String numeroTelefone, Endereco endereco, String cep, List<Float> ultimasContas, List<String> empresasEscolhidas) {
        super(idUsuario, nome, email, senha, numeroTelefone, endereco);
        this.cep = cep;
        this.ultimasContas = new ArrayList<>();
        this.empresasEscolhidas = new ArrayList<>();
    }

    private String cep;
    private List<Float> ultimasContas;
    private List<String> empresasEscolhidas;
    private static Queue<SunPartner> filaDeEspera = new LinkedList<>();
    public float mediaConsumo = 0;
    public float margemErro = .15f; // 15% de margem de erro
    public float consumoTotal = 0;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Float> getUltimasContas() {
        return ultimasContas;
    }

    public void setUltimasContas(List<Float> ultimasContas) {
        this.ultimasContas = ultimasContas;
    }

    public List<String> getEmpresasEscolhidas() {
        return empresasEscolhidas;
    }

    public void setEmpresasEscolhidas(List<String> empresasEscolhidas) {
        this.empresasEscolhidas = empresasEscolhidas;
    }

    /* Método para calcular o consumo total de energia */
    public float ConsumoTotalPartner() {
        /* Calcular a média de consumo */
        float soma = 0;
        for (Float valorConta : ultimasContas) {
            soma += valorConta;
        }
        mediaConsumo = soma / ultimasContas.size();

        /* Calcular o consumo total de energia (add margem de erro) */
        consumoTotal = mediaConsumo + (mediaConsumo * margemErro);

        return consumoTotal;
    }

    /* Método para solicitar informações do usuário */
    public void solicitarInfoUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAqui você poderá solicitar um orçamento de energia solar e ficar a espera de um parceiro SolCoop!");
        System.out.println("Por favor, insira suas informações necessárias:");
        System.out.println("Insira o valor em kWh (quilowatt-hora) das suas últimas 12 contas de energia: ");

        // Adiciona os valores das últimas contas de energia à lista
        for (int i = 0; i < 12; i++) {
            System.out.print("Conta [" + (i + 1) + "]: ");
            float conta = scanner.nextFloat();
            ultimasContas.add(conta);
        }
        PrintInfoUsuario(); // Chama o metodo de printar as infos do usuario
    }

    /* Metodo para printar as informações do usuário */
    public void PrintInfoUsuario() {
        float consumoFinal = ConsumoTotalPartner();
        System.out.printf("\nSua média de consumo nas últimas 12 contas de energia: %.2f kWh", mediaConsumo);
        System.out.printf("\nMédia com margem de segurança de 15%%: %.2f kWh", consumoFinal);
        System.out.println("\nA margem de segurança é importante para que o seu consumo seja sempre atendido com energia limpa.");

        filaDeEspera.add(this); // Adiciona o usuário à fila
        escolhaEmpresa(this);
    }

    /* Método para escolher uma empresa */
    public static void escolhaEmpresa(SunPartner sunPartner) {
        Scanner sc = new Scanner(System.in);
        int escolhaDaEmpresa;

        listarEmpresas();

        do {
            System.out.println("\nEscolha uma empresa para solicitar um orçamento: ");
            escolhaDaEmpresa = sc.nextInt();

            switch (escolhaDaEmpresa) {
                case 1:
                    sunPartner.getEmpresasEscolhidas().add("BRZ Solar");
                    System.out.println("Orçamento solicitado com sucesso para a empresa BRZ Solar!");
                    break;
                case 2:
                    sunPartner.getEmpresasEscolhidas().add("Inove Solar Engenharia");
                    System.out.println("Orçamento solicitado com sucesso para a empresa Inove Solar Engenharia!");
                    break;
                case 3:
                    sunPartner.getEmpresasEscolhidas().add("RenovaSol");
                    System.out.println("Orçamento solicitado com sucesso para a empresa RenovaSol!");
                    break;
                case 4:
                    sunPartner.getEmpresasEscolhidas().add("DellaSol");
                    System.out.println("Orçamento solicitado com sucesso para a empresa DellaSol!");
                    break;
                case 5:
                    sunPartner.getEmpresasEscolhidas().add("Sun7");
                    System.out.println("Orçamento solicitado com sucesso para a empresa Sun7!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    escolhaDaEmpresa = sc.nextInt();
                    break;
            }
        } while (escolhaDaEmpresa < 1 || escolhaDaEmpresa > 5);
    }

    /* Método para listar a fila de usuários */
    public void listarFilaDeUsuarios() {
        if (filaDeEspera == null || filaDeEspera.isEmpty()) {
            System.out.println("\nA fila de espera está vazia.");
            return;
        }
        System.out.println("\nFila de Usuários:");
        for (SunPartner sunPartner : filaDeEspera) {
            String empresas = (sunPartner.getEmpresasEscolhidas() != null) ? sunPartner.getEmpresasEscolhidas().toString() : "Nenhuma empresa escolhida";
            System.out.println("Nome: " + sunPartner.getNome() + " | CEP: " + sunPartner.getCep() + " | Empresas escolhidas: " + empresas);
        }
    }

    /* Método para listar as empresas parceiras */
    public static void listarEmpresas() {
        System.out.println("\nConheça as empresas parceiras do SolCoop:");
        System.out.println("1. BRZ Solar");
        System.out.println("2. Inove Solar Engenharia");
        System.out.println("3. RenovaSol");
        System.out.println("4. DellaSol");
        System.out.println("5. Sun7");
    }

    /* Método para solicitar orçamento */
    public void solicitarOrcamento() {
        float consumoFinal = ConsumoTotalPartner();
        System.out.println("/// Informações do orçamento: ///");
        System.out.println("CEP: " + cep);
        System.out.printf("Quantidade de energia desejada (com margem de segurança de 15%%) é %.2f kWh: \n", consumoFinal);
        System.out.println("Empresas escolhidas: " + empresasEscolhidas);
        System.out.println("\nVamos encontrar um parceiro SolCoop para você. Entraremos em contato em breve!\n");
    }

    public static void main(String[] args) {
        SunPartner sunPartner = new SunPartner(0, "", "", "", "", null, "", new ArrayList<>(), new ArrayList<>());
        sunPartner.solicitarInfoUsuario();
        sunPartner.solicitarOrcamento();
        sunPartner.listarFilaDeUsuarios();
    }
}
