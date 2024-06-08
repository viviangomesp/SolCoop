package model.partner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SunPartner {
    private String cep;
    private List<Float> ultimasContas;
    private List<String> empresasEscolhidas;

    public SunPartner(String cep, List<Float> ultimasContas, List<String> empresasEscolhidas) {
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

    public void solicitarOrcamento() {
        System.out.println("\nOrçamento solicitado com sucesso!\n");
        System.out.println("Informações do orçamento:");
        System.out.println("CEP: " + cep);
        System.out.println("Quantidade de energia desejada (com margem de segurança de 15%): " + calcularMediaComMargemSeguranca(calcularMedia(ultimasContas)));
        System.out.println("Empresas escolhidas: " + empresasEscolhidas);
        System.out.println("\nVamos encontrar um parceiro SolCoop para você. Entraremos em contato em breve!");
    }

    public static List<Float> solicitarUltimasContas() {
        List<Float> ultimasContas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira as últimas 12 contas de energia:");

        for (int i = 0; i < 12; i++) {
            System.out.print("Conta " + (i + 1) + ": ");
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

        System.out.println("Insira suas informações:");

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        List<Float> ultimasContas = solicitarUltimasContas();
        float mediaContas = calcularMedia(ultimasContas);
        float mediaComMargemSeguranca = calcularMediaComMargemSeguranca(mediaContas);

        System.out.println("Média das últimas 12 contas de energia: " + mediaContas);
        System.out.println("Média com margem de segurança de 15%: " + mediaComMargemSeguranca);
        System.out.println("A margem de segurança é importante para que o seu consumo seja sempre atendido com energia limpa.");

        List<String> empresasEscolhidas = new ArrayList<>();
        System.out.println("Insira a empresa escolhida:");
        String empresa = scanner.nextLine();
        empresasEscolhidas.add(empresa.trim());

        return new SunPartner(cep, ultimasContas, empresasEscolhidas);
    }

    public static void main(String[] args) {
        SunPartner sunPartnerInstance = solicitarInformacoesDoUsuario();
        sunPartnerInstance.solicitarOrcamento();
    }
}

    

