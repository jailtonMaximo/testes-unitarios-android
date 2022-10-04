package br.com.alura.leilao.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario joao =  new Usuario("João");
    private final Usuario fernando =  new Usuario("Fernando");

    @Test
    public void deve_retornarDescricao_leilao() {
        // criar cenario de teste
        Leilao console = new Leilao("Console");
        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();
        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_pegarMaiorLance_lanceUnico(){
        CONSOLE.propoe(new Lance(joao,250.0));
        Double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(250.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_pegarMaiorLance_variosLancesOrdemDecrescente(){
        CONSOLE.propoe(new Lance(joao,400.0));
        CONSOLE.propoe(new Lance(fernando,120.0));

        Double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(400.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_pegarMaiorLance_variosLancesOrdemCrescente(){
        CONSOLE.propoe(new Lance(fernando,120.0));
        CONSOLE.propoe(new Lance(joao,250.0));
        Double maiorLanceRetornado = CONSOLE.getMaiorLance();
        assertEquals(250.0, maiorLanceRetornado, DELTA);
    }
    @Test
    public void deve_pegarMenorLance_lanceUnico(){
        CONSOLE.propoe(new Lance(joao,250.0));
        Double menorLanceRetornado = CONSOLE.getMenorLance();
        assertEquals(250.0, menorLanceRetornado, DELTA);
    }

    @Test
    public void deve_pegarMenorLance_variosLancesOrdemDecrescente(){
        CONSOLE.propoe(new Lance(joao,400.0));
        CONSOLE.propoe(new Lance(fernando,120.0));

        Double menorLanceRetornado = CONSOLE.getMenorLance();
        assertEquals(120.0, menorLanceRetornado, DELTA);
    }

    @Test
    public void deve_pegarMenorLance_variosLancesOrdemCrescente(){
        CONSOLE.propoe(new Lance(joao,120.0));
        CONSOLE.propoe(new Lance(fernando,250.0));
        Double menorLanceRetornado = CONSOLE.getMenorLance();
        assertEquals(120.0, menorLanceRetornado, DELTA);
    }

    @Test
    public void deve_retornar_unidade_entrega(){
        WebConversaoUnidade retorno = new WebConversaoUnidade();
//        double quantidade, double multiploVenda, String unidade, String unidadeEntregaMultiploVenda
        retorno = CONSOLE.converterParaUnidadeEntrega(
                50,
                2.5,
                "UN",
                "CX"
        );

        assertEquals("CX", retorno.getUnidade());
        assertEquals(20, retorno.getQuantidade(), DELTA);
    }

    @Test
    public void deve_quandoNaoTemUnidadeEntrega_retornar_unidade_venda(){
        WebConversaoUnidade retorno = new WebConversaoUnidade();
//        double quantidade, double multiploVenda, String unidade, String unidadeEntregaMultiploVenda
        retorno = CONSOLE.converterParaUnidadeEntrega(
                50,
                2.5,
                "UN",
                ""
        );

        assertEquals("UN", retorno.getUnidade());
        assertEquals(50, retorno.getQuantidade(), DELTA);
    }

    @Test
    public void deve_quandoNaoTemUnidadeEntrega_retornar_unidade_venda1(){
        WebConversaoUnidade retorno = new WebConversaoUnidade();
//        double quantidade, double multiploVenda, String unidade, String unidadeEntregaMultiploVenda
        retorno = CONSOLE.converterParaUnidadeEntrega(
                50,
                2.5,
                "UN",
                null
        );

        assertEquals("UN", retorno.getUnidade());
        assertEquals(50, retorno.getQuantidade(), DELTA);
    }

    @Test
    public void deve_quandoNaoTemUnidadeEntrega_retornar_unidade_venda2(){
        WebConversaoUnidade retorno = new WebConversaoUnidade();

        retorno = CONSOLE.converterParaUnidadeEntrega(
            50,
            1,
            "UN",
            null
        );

        assertEquals("UN", retorno.getUnidade());
        assertEquals(50, retorno.getQuantidade(), DELTA);
    }
}