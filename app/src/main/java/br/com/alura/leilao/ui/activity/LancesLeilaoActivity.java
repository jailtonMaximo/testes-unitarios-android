package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;

public class LancesLeilaoActivity extends AppCompatActivity {
    private TextView descricao;
    private TextView maiorLance;
    private TextView menorLance;
    private TextView tresMaioresLances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lances_leilao);
        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra("leilao")){
            Leilao leilao = (Leilao) dadosRecebidos.getSerializableExtra("leilao");
            descricao = findViewById(R.id.lances_leilao_descricao);
            maiorLance = findViewById(R.id.lances_leilao_maior_lance);
            menorLance = findViewById(R.id.lances_leilao_menor_lance);
            tresMaioresLances = findViewById(R.id.lances_leilao_maiores_lances);

            descricao.setText(leilao.getDescricao());
            maiorLance.setText(leilao.getMaiorLance().toString());
            menorLance.setText(leilao.getMenorLance().toString());

            StringBuilder sb = new StringBuilder();
            for (Lance lance: leilao.getTresMaioresLances() ) {
                sb.append(lance.getUsuario().getNome() + " - " + lance.getValor() + "\n");
            }
            tresMaioresLances.setText(sb.toString());
        }
    }
}
