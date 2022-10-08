package br.com.alura.leilao.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario JOAO =  new Usuario("João");
    private final Usuario FERNANDO =  new Usuario("Fernando");
    private final Usuario AMANDA =  new Usuario("Amanda");
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
        CONSOLE.propoe(new Lance(JOAO,250.0));
        Double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(250.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_pegarMaiorLance_variosLancesOrdemDecrescente(){
        CONSOLE.propoe(new Lance(JOAO,400.0));
        CONSOLE.propoe(new Lance(FERNANDO,120.0));

        Double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(400.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_pegarMaiorLance_variosLancesOrdemCrescente(){
        CONSOLE.propoe(new Lance(FERNANDO,120.0));
        CONSOLE.propoe(new Lance(JOAO,250.0));
        Double maiorLanceRetornado = CONSOLE.getMaiorLance();
        assertEquals(250.0, maiorLanceRetornado, DELTA);
    }
    @Test
    public void deve_pegarMenorLance_lanceUnico(){
        CONSOLE.propoe(new Lance(JOAO,250.0));
        Double menorLanceRetornado = CONSOLE.getMenorLance();
        assertEquals(250.0, menorLanceRetornado, DELTA);
    }

    @Test
    public void deve_pegarMenorLance_variosLancesOrdemDecrescente(){
        CONSOLE.propoe(new Lance(JOAO,400.0));
        CONSOLE.propoe(new Lance(FERNANDO,120.0));

        Double menorLanceRetornado = CONSOLE.getMenorLance();
        assertEquals(120.0, menorLanceRetornado, DELTA);
    }

    @Test
    public void deve_pegarMenorLance_variosLancesOrdemCrescente(){
        CONSOLE.propoe(new Lance(JOAO,120.0));
        CONSOLE.propoe(new Lance(FERNANDO,250.0));
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


    @Test
    public void deve_DevolverTresMaioresLances_QuandoReceberExatosTresLances(){
        CONSOLE.propoe(new Lance(JOAO,200.00));
        CONSOLE.propoe(new Lance(FERNANDO,300.00));
        CONSOLE.propoe(new Lance(AMANDA,400.00));

        List<Lance> tresMaioresLances = CONSOLE.getTresMaioresLances();

        assertEquals(3,tresMaioresLances.size());

        assertEquals(400.00,tresMaioresLances.get(0).getValor(),DELTA);
        assertEquals(300.00,tresMaioresLances.get(1).getValor(),DELTA);
        assertEquals(200.00,tresMaioresLances.get(2).getValor(),DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLances = CONSOLE.getTresMaioresLances();
        assertEquals(0,tresMaioresLances.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeUmLances(){
        CONSOLE.propoe(new Lance(FERNANDO,300.00));
        List<Lance> tresMaioresLances = CONSOLE.getTresMaioresLances();
        assertEquals(1,tresMaioresLances.size());
        assertEquals(300.00,tresMaioresLances.get(0).getValor(),DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeDoisLances(){
        CONSOLE.propoe(new Lance(FERNANDO,300.00));
        CONSOLE.propoe(new Lance(AMANDA,400.00));
        List<Lance> tresMaioresLances = CONSOLE.getTresMaioresLances();
        assertEquals(2,tresMaioresLances.size());
        assertEquals(400.00,tresMaioresLances.get(0).getValor(),DELTA);
        assertEquals(300.00,tresMaioresLances.get(1).getValor(),DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        CONSOLE.propoe(new Lance(FERNANDO,100.00));
        CONSOLE.propoe(new Lance(AMANDA,360.00));
        CONSOLE.propoe(new Lance(FERNANDO,250.00));
        CONSOLE.propoe(new Lance(AMANDA,400.00));
        List<Lance> tresMaioresLances = CONSOLE.getTresMaioresLances();
        assertEquals(3,tresMaioresLances.size());
        assertEquals(400.00,tresMaioresLances.get(0).getValor(),DELTA);
        assertEquals(360.00,tresMaioresLances.get(1).getValor(),DELTA);
        assertEquals(250.00,tresMaioresLances.get(2).getValor(),DELTA);

    }
}