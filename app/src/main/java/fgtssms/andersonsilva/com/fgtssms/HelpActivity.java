package fgtssms.andersonsilva.com.fgtssms;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class HelpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ajuda");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"anderson.rodrigo@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Ajuda");
                email.putExtra(Intent.EXTRA_TEXT, "");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Escolha um Cliente de email:"));

            }
        });

        TextView textView = (TextView) findViewById(R.id.textHelp);
        String texto = "Esta aplicação Lê os SMS enviados pela Caixa Econômica Federal com as informações referentes ao Seu SMS. " +
                "\nAs informações apresentadas neste aplicativo refletem as mensagens da sua caixa de entrada e organiza as informações.\n" +
                "Qualquer divergência deve ser verificada diretamente com a Caixa Econômica Federal, sendo assim este Aplicativo não possui nenhuma responsabilidade com as informações apresentadas.\n" +
                "Para solicitar a adesão ao serviço acesse a URL:";
        textView.setText(texto);
        TextView textViewUrl = (TextView) findViewById(R.id.textURL);
        String textoUrl = "http://www.caixa.gov.br/beneficios-trabalhador/fgts/FGTS-Alerta-SMS/Paginas/default.aspx?pk_campaign=adesaopf";
        textViewUrl.setText(textoUrl);
        textViewUrl.setTextColor(Color.GREEN);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }



}
