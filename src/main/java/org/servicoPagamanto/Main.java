package org.servicoPagamanto;



import jakarta.persistence.EntityManager;
import org.servicoPagamanto.config.JpaConnection;
import org.servicoPagamanto.dao.ContratoDao;
import org.servicoPagamanto.dao.ParcelaDao;
import org.servicoPagamanto.model.entities.Contrato;
import org.servicoPagamanto.model.entities.Parcela;
import org.servicoPagamanto.service.ContratoService;
import org.servicoPagamanto.service.PayPalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        EntityManager em = JpaConnection.getEntityManager();

        ContratoDao contratoDao = new ContratoDao(em);
        ParcelaDao parcelaDao = new ParcelaDao(em);

        try {
            System.out.println("Entre com os dados do contrato: ");
            System.out.printf("Numero: ");
            long numero = sc.nextLong();
            System.out.printf("Valor: ");
            double valor = sc.nextDouble();
            System.out.printf("Parcelas: ");
            int parcelas = sc.nextInt();

            Contrato contrato = new Contrato(numero, LocalDate.now(), BigDecimal.valueOf(valor));
            ContratoService contratoService = new ContratoService(new PayPalService());

            contratoService.processarContrato(contrato, parcelas);

            for (Parcela parcela : contrato.getParcelas()) {
                System.out.println(parcela);
            }

            System.out.println(contratoService.getValorComJurosContrato(contrato));

            //contratoDao.salvar(contrato);


            //lista todos contratos
            /*/for (Contrato contratoBanco : contratoDao.listarTodos()) {
                System.out.println(contratoBanco);
            }*/

            //lista todas parcelas
          /*  for (Parcela parcelaBanco : parcelaDao.listarTodos()) {
                System.out.println(parcelaBanco.getId() + " " +parcelaBanco);
            }*/


            //busca contrato por id
           /* System.out.println(contratoDao.buscarPorId(11L));
*/

            //atualiza contrato
           /* Contrato contrato = contratoDao.buscarPorId(11L);
            contrato.setValorTotal(BigDecimal.valueOf(3000));
            contratoDao.atualizar(contrato);*/

            //exclui todos contratos e parcelas
            //contratoDao.removerTodos();

        }catch (InputMismatchException e) {
            System.out.println("erro: digite apenas números válidos.");
        }
        finally {
            sc.close();
        }

    }
}
