package br.com.bancoms.service;

import br.com.bancoms.dao.ContaDAO;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Conta;
import br.com.bancoms.util.Validador;

import java.util.ArrayList;

public class ContaService {
    private static ContaService contaService;
    private ContaDAO contaDAO;

    private ContaService() {
        contaDAO = new ContaDAO();
    }

    public static ContaService getInstance() {

        if (contaService != null) {
            return contaService;
        }

        contaService = new ContaService();

        return contaService;

    }

    public Conta consultarConta(int numeroConta) {
        return contaDAO.consultarConta(numeroConta);
    }

    private boolean atualizarSaldo(Conta conta) {
        return contaDAO.atualizarSaldo(conta);
    }

    private boolean sacar(Conta contaSessao, double valorOperacao) {
        return contaSessao.sacar(valorOperacao);
    }

    /*Realiza o depósito na conta informada e retorna com resultado o Movimento realizado*/
    public MovimentoTO realizarDeposito(Conta conta, double valor) {

        conta.depositar(valor);

        if (atualizarSaldo(conta)) {
            return new MovimentoTO(conta.getId(), valor, MovimentoTO.DEPOSITO, "DEPÓSITO CONTA-PRÓPRIA", conta.getNumero(), conta.getNumero());
        } else {
            conta.sacar(valor);
        }

        return null;
    }

    /*Realiza o depósito na conta do número informado e retorna como resultado os movimentos realizado.*/
    public ArrayList<MovimentoTO> realizarDeposito(Conta contaOrigem, String numeroContaDestino, double valor) throws Exception {

        ArrayList<MovimentoTO> listaMovimentosRealizado = new ArrayList<>();

        Conta contaDestino = consultarConta(validarDadosDeposito(numeroContaDestino));

        if (contaDestino != null) {

            contaDestino.depositar(valor);

            if (atualizarSaldo(contaDestino)) {
                listaMovimentosRealizado.add(new MovimentoTO(contaOrigem.getId(), valor, MovimentoTO.DEPOSITO, "DEPÓSITO OUTRA-CONTA", contaOrigem.getNumero(), contaDestino.getNumero()));
                listaMovimentosRealizado.add(new MovimentoTO(contaDestino.getId(), valor, MovimentoTO.DEPOSITO, "DEPÓSITO OUTRA-CONTA", contaOrigem.getNumero(), contaDestino.getNumero()));
            } else {
                contaDestino.sacar(valor);
            }

        } else {
            throw new Exception("Conta informada inválida");
        }

        return listaMovimentosRealizado;

    }

    private int validarDadosDeposito(String numeroConta) throws Exception {

        Validador.Valor<Integer> valor;

        if ((valor = Validador.validar(numeroConta)).resposta == Validador.CAMPO_VALIDO) {
            return valor.valor;
        } else {
            throw new Exception("Número da Conta inválido");
        }
    }

}
