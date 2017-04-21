package fgtssms.andersonsilva.com.fgtssms;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fgtssms.andersonsilva.com.fgtssms.adapter.ExtratoGeralAdapter;
import fgtssms.andersonsilva.com.fgtssms.entity.Sms;
import fgtssms.andersonsilva.com.fgtssms.utils.SmsUtils;

public class ExtratoActivity extends AppCompatActivity {

    Spinner spinnerContas;
    List<Sms> listaSms;
    ListView listaGeral;
    Sms smsSelecionado;
    ExtratoGeralAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Extrato");
        alimentaComboContas();
    }

    private void alimentaComboContas() {
        spinnerContas = (Spinner) findViewById(R.id.spinnerContas);
        List<String> list = new ArrayList<String>();
        list.add("Selecione uma Conta");
        listaSms = SmsUtils.getAllSms(getContentResolver());
        listaSms = SmsUtils.agrupaSms(listaSms,getBaseContext(),getPackageName(),this);
        if (listaSms!=null){
            for (Sms sms:listaSms){
                list.add(sms.getNomeConta()!=null?sms.getNomeConta():"" + sms.getNumeroContaFgts());
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContas.setAdapter(dataAdapter);
        listaGeral = (ListView) findViewById(R.id.listExtratoGeral);
        View header = getLayoutInflater().inflate(R.layout.header_extrato_geral, null);
        listaGeral.addHeaderView(header);

        spinnerContas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listaGeral = (ListView) findViewById(R.id.listExtratoGeral);
               if (id>0) {
                   Sms sms = listaSms.get(position - 1);
                   smsSelecionado = sms;

                   adapter = new ExtratoGeralAdapter(sms.getListaMensagensConta(), getApplicationContext());
                   listaGeral.setAdapter(adapter);

                   listaGeral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           //Sms dataModel= listaSms.get(position);
                           Sms dataModel = smsSelecionado.getListaMensagensConta().get(position-1);
              /*  Snackbar.make(view, dataModel.getMsg(), Snackbar.LENGTH_LONG)
                        .setAction("Mensagem completa", null).show();*/
                           AlertDialog alertDialog = new AlertDialog.Builder(ExtratoActivity.this).create();
                           alertDialog.setTitle("Mensagem Completa");
                           alertDialog.setMessage(dataModel.getMsg());
                           alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                   new DialogInterface.OnClickListener() {
                                       public void onClick(DialogInterface dialog, int which) {
                                           dialog.dismiss();
                                       }
                                   });
                           alertDialog.show();
                       }
                   });
               }else{
                   adapter = new ExtratoGeralAdapter(new ArrayList<Sms>(), getApplicationContext());
                   listaGeral.setAdapter(adapter);
                  // listaGeral.addHeaderView(null);
               }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                listaGeral = (ListView) findViewById(R.id.listExtratoGeral);

                listaGeral.setAdapter(null);
                listaGeral.addHeaderView(null);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
