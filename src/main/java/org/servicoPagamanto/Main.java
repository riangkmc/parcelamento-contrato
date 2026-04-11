package org.servicoPagamanto;

import org.servicoPagamanto.model.entities.Contrato;
import org.servicoPagamanto.model.entities.Parcela;
import org.servicoPagamanto.service.ContratoService;
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

        ContratoService contratoService = context.getBean(ContratoService.class);
        contratoService.removerTodos();

        /*Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        ContratoService contratoService = context.getBean(ContratoService.class);

        System.out.println("Entre com os dados do contrato: ");
        System.out.printf("Numero: ");
        long numero = sc.nextLong();
        System.out.printf("Valor: ");
        BigDecimal valor = sc.nextBigDecimal();
        System.out.printf("Parcelas: ");
        int parcelas = sc.nextInt();

        Contrato contrato = new Contrato(numero, LocalDate.now(), valor);
        contratoService.processarContrato(contrato, parcelas);
        contratoService.salvar(contrato);
        for (Parcela p : contrato.getParcelas()) {
            System.out.println(p);
        }

        System.out.println("Salvo no banco!");

        sc.close();*/

    }
}