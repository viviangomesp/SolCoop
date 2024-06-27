package model.partner;

import model.Endereco;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class SunPartner extends Usuario{

    private String cep;
    private List<Float> ultimasContas;
    private List<String> empresasEscolhidas;
    private static Queue<SunPartner> filaDeEspera = new LinkedList<>();
    
    public SunPartner(int idUsuario, String nome, String email, String senha, String numeroTelefone, Endereco endereco, String cep, List<Float> ultimasContas, List<String> empresasEscolhidas) {
        super(idUsuario, nome, email, senha, numeroTelefone, endereco);
        this.cep = cep;
        this.ultimasContas = ultimasContas;
        this.empresasEscolhidas = empresasEscolhidas;
    }

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

    public void adicionarEmpresasTestes() {
        empresasEscolhidas.add("1. BRZ Solar");
        empresasEscolhidas.add("2. Inove Solar Engenharia");
        empresasEscolhidas.add("3. RenovaSol");
        empresasEscolhidas.add("4. DellaSol");
        empresasEscolhidas.add("5. Sun7");
    }

    public void solicitarOrcamento() {
        System.out.println("Informações do orçamento:");
        System.out.println("CEP: " + cep);
        System.out.println("Quantidade de energia desejada (com margem de segurança de 15%): " + calcularMediaComMargemSeguranca(calcularMedia(ultimasContas)));
        System.out.println("Empresas escolhidas: " + empresasEscolhidas);
        System.out.println("\nVamos encontrar um parceiro SolCoop para você. Entraremos em contato em breve!\n");
    }

    public static List<Float> solicitarUltimasContas() {
        List<Float> ultimasContas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 12; i++) {
            System.out.print("Conta [" + (i + 1) + "]: ");
            ultimasContas.add(Float.parseFloat(scanner.nextLine()));
        }

        return ultimasContas;
    }

    public static float calcularMedia(List<Float> contas) {
        float soma = 0;
        for (float conta : contas) {
            soma += conta;
        }
        float media = soma / contas.size();
        return media;
    }

    public static float calcularMediaComMargemSeguranca(float media) {
        return media * 1.15f;
    }

    public static SunPartner solicitarInformacoesDoUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aqui você pode solicitar um orçamento de energia solar e ficar a espera de um parceiro SolCoop!");
        System.out.println("Insira o valor em kWh das suas últimas 12 contas de energia: ");
        System.out.println("Por favor, insira suas informações:");

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        List<Float> ultimasContas = solicitarUltimasContas();
        float mediaContas = calcularMedia(ultimasContas);
        float mediaComMargemSeguranca = calcularMediaComMargemSeguranca(mediaContas);

        System.out.println("\nMédia das últimas 12 contas de energia: %.2f kWh" + mediaContas);
        System.out.println("Média com margem de segurança de 15%: %.2f kWh" + mediaComMargemSeguranca);
        System.out.println("A margem de segurança é importante para que o seu consumo seja sempre atendido com energia limpa.");

        
        SunPartner sunPartner = new SunPartner(0, "", "", "", "", null, cep, ultimasContas, new ArrayList<>());
        filaDeEspera.add(sunPartner); // Adiciona o usuário à fila
        escolhaEmpresa();
        return sunPartner;
    }

    public static void listarFilaDeUsuarios() {
        System.out.println("\nFila de Usuários:");
        for (SunPartner sunPartner : filaDeEspera) {
            System.out.println("Nome: " + sunPartner.getNome() + " | CEP: " + sunPartner.getCep() + " | Empresas escolhidas: " + sunPartner.getEmpresasEscolhidas());
        }
    }

    public static void listarEmpresas(SunPartner sunPartner) {
        System.out.println("\nConheça as empresas parceiras do SolCoop:");
        for (String empresa : sunPartner.getEmpresasEscolhidas()) {
            System.out.println(empresa);
        }
        
    }

    public static void escolhaEmpresa (){
        Scanner sc = new Scanner(System.in);
        int escolhaDaEmpresa;

        SunPartner sunPartner = new SunPartner(0, "", "", "", "", null, "", new ArrayList<>(), new ArrayList<>());
        sunPartner.adicionarEmpresasTestes();
        listarEmpresas(sunPartner);

        do{
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

    public static void main(String[] args) {
        SunPartner sunPartner = solicitarInformacoesDoUsuario();
        sunPartner.solicitarOrcamento();
        listarFilaDeUsuarios();
    }
}

