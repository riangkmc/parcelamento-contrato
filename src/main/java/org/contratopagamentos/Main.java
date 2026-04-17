package org.contratopagamentos;

import org.contratopagamentos.contrato.Contrato;
import org.contratopagamentos.parcela.Parcela;
import org.contratopagamentos.contrato.ContratoService;
import org.contratopagamentos.pagamento.PagamentoService;
import org.contratopagamentos.pagamento.PayPalService;
import org.contratopagamentos.pagamento.PicPayService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

/*

        ContratoService contratoService = context.getBean(ContratoService.class);
        contratoService.removerTodos();


*/

      /*  Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        System.out.println("Entre com os dados do contrato: ");
        System.out.printf("Numero: ");
        long numero = sc.nextLong();
        System.out.printf("Valor: ");
        BigDecimal valor = sc.nextBigDecimal();
        System.out.printf("Parcelas: ");
        int parcelas = sc.nextInt();
        System.out.println("Escolha o tipo de pagamento: ");
        System.out.println("1 - PicPay");
        System.out.println("2 - PayPal");
        int opcao = sc.nextInt();
        PagamentoService tipoPagamento;
        if (opcao == 1) {
            tipoPagamento = new PicPayService();
        } else if (opcao == 2) {
            tipoPagamento = new PayPalService();
        } else {
            throw new IllegalArgumentException("Opção inválida");
        }
        Contrato contrato = new Contrato(numero, LocalDate.now(), valor, tipoPagamento.getTipo(),tipoPagamento);
        ContratoService contratoService = context.getBean(ContratoService.class);
        contratoService.processarContrato(contrato, parcelas);
        contratoService.salvar(contrato);
        for (Parcela p : contrato.getParcelas()) {
            System.out.println(p);
        }

        System.out.println("Salvo no banco!");

        sc.close();
*/


       /* ContratoService contratoService = context.getBean(ContratoService.class);


        System.out.println(contratoService.getValorComJurosContrato(30L));

        contratoService.imprimirParcelas(30L);*/

    }
}