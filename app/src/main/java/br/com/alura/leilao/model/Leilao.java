package br.com.alura.leilao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private Double maiorLance = Double.NEGATIVE_INFINITY;
    private Double menorLance = Double.POSITIVE_INFINITY;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance){
        Double valorLance = lance.getValor();

        if (maiorLance.isInfinite() || (valorLance > maiorLance)) {
            maiorLance = valorLance;
        }
        if(menorLance.isInfinite() || (valorLance < menorLance)) {
            menorLance = valorLance;
        }

    }

    private void calculaMaiorLance(Double valorLance) {
        if(valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    private void calculaMenorLance(Double valorLance) {
        if(valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    public Double getMaiorLance() {
        return maiorLance;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMenorLance() {
        return menorLance;
    }

    public static WebConversaoUnidade converterParaUnidadeEntrega(double quantidade, double multiploVenda, String unidade, String unidadeEntregaMultiploVenda) {
        WebConversaoUnidade retorno = new WebConversaoUnidade();
        BigDecimal quantidadeInformada = new BigDecimal(String.valueOf(quantidade));
        BigDecimal multiplo = new BigDecimal(String.valueOf(multiploVenda));
        BigDecimal resultado = quantidadeInformada;
        retorno.setUnidade(unidade);
        retorno.setQuantidade(resultado.doubleValue());

        if(unidadeEntregaMultiploVenda != null && !unidadeEntregaMultiploVenda.isEmpty() && !unidade.equals(unidadeEntregaMultiploVenda)) {
            resultado = quantidadeInformada.divide(multiplo,3,BigDecimal.ROUND_HALF_UP);
            retorno.setUnidade(unidadeEntregaMultiploVenda);
            retorno.setQuantidade(resultado.doubleValue());
        }

        return retorno;
    }
}
