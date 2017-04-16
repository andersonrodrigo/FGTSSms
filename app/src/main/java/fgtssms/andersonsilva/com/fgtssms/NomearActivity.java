package fgtssms.andersonsilva.com.fgtssms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fgtssms.andersonsilva.com.fgtssms.adapter.NomearAdapter;
import fgtssms.andersonsilva.com.fgtssms.entity.Sms;
import fgtssms.andersonsilva.com.fgtssms.utils.SmsUtils;

public class NomearActivity extends AppCompatActivity {

    ArrayList<Sms> listaSms = null;
    ListView listView = null;
    private static NomearAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomear);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nomear Contas");

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
