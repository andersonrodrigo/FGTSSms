package fgtssms.andersonsilva.com.fgtssms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fgtssms.andersonsilva.com.fgtssms.adapter.NomearAdapter;
import fgtssms.andersonsilva.com.fgtssms.entity.Conta;
import fgtssms.andersonsilva.com.fgtssms.entity.Sms;
import fgtssms.andersonsilva.com.fgtssms.utils.DBAdapter;
import fgtssms.andersonsilva.com.fgtssms.utils.SmsUtils;

public class NomearActivity extends AppCompatActivity {
    NomearActivity that = this;
    ArrayList<Sms> listaSms = null;
    ListView listView = null;
    Button btnSalvar;
    private static NomearAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomear);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nomear Contas");
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView lista = (ListView) findViewById(R.id.listContas);
                List<Conta> contas = new ArrayList<Conta>();
                for(int i =0;i<lista.getChildCount();i++){
                    RelativeLayout layout = (RelativeLayout) lista.getChildAt(i);
                    EditText editText = (EditText) layout.findViewById(R.id.nomeConta);
                    TextView textView = (TextView) layout.findViewById(R.id.numeroConta);
                    System.out.print(textView.getText());
                    System.out.print(editText.getText());
                    Conta conta = new Conta();
                    conta.setNome(editText.getText().toString());
                    conta.setNumero(textView.getText().toString());
                    contas.add(conta);
                }

             SmsUtils.salvarContas(contas,getBaseContext(),getPackageName(),that);
                Toast.makeText(NomearActivity.this, "Salvo", Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        listaSms = SmsUtils.getAllSms(getContentResolver());
        listaSms = SmsUtils.agrupaSms(listaSms,getBaseContext(),getPackageName(),this);
        listView = (ListView) findViewById(R.id.listContas);
        adapter= new NomearAdapter(listaSms,getApplicationContext());
        listView.setAdapter(adapter);
    }
}
