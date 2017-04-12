package fgtssms.andersonsilva.com.fgtssms.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fgtssms.andersonsilva.com.fgtssms.entity.Sms;

/**
 * Created by anderson.silva on 14/02/2017.
 */
public class SmsUtils {


    private  List<Sms> sms_All;


    static NumberFormat formato2 = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));






    /**
     *
     * @param lista
     * @param ordem
     */
    public static void ordenaListaValorData(List<Sms> lista,final int ordem){
        Collections.sort(lista,(new Comparator<Sms>() {
            @Override
            public int compare(Sms sms, Sms t1) {
                Date data1 = null;
                Date data2 = null;
                try{
                    data1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sms.getDataCompra());
                    data2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(t1.getDataCompra());
                    return data1.compareTo(data2) * ordem;
                }catch (Exception e){
                    return 0;
                }

            }
        }));
    }


    /**
     * Formata do formato mes ano
     *
     * @param listaSms
     */
    private static void formataDataParaExibicao(List<Sms> listaSms) {
        for(Sms s:listaSms){
            try {
                s.setDataCompra(new SimpleDateFormat("MM/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(s.getDataCompra())));
            }catch (Exception e){

            }
        }
    }





    /**
     *
     * @return
     */
    public static ArrayList<Sms> getAllSms(ContentResolver cr ) {
        try {
            ArrayList<Sms> listaSms = new ArrayList<Sms>();
            Uri message = Uri.parse("content://sms/");
            String[] reqCols = new String[] { "_id", "address", "body","date" };
            Cursor c = cr.query(message, reqCols, null, null, null);
            //  startManagingCursor(c);
            int totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int i = 0; i < totalSMS; i++) {
                    Sms objSms = new Sms();
                    objSms.setMsg(c.getString(c.getColumnIndexOrThrow("body")));

                    if (objSms.getMsg() != null) {

                        if (objSms.getMsg().indexOf("CAIXA informa: ") > -1) {
                            try {
                                String data = c.getString(c.getColumnIndexOrThrow("date"));
                                preencheObjetoSms(cr, data, objSms,listaSms);


                            } catch (Exception e) {

                            }
                        }

                    }
                    c.moveToNext();
                }
            }
            c.close();
            return listaSms;
        }catch(Exception e){
            return new ArrayList<Sms>();
        }
    }

    /**
     *
     * @param cr
     * @param objSms
     *
     */
    public static void preencheObjetoSms(ContentResolver cr,String data,Sms objSms,ArrayList<Sms>  listaSms){
/**        CAIXA informa: Juros e Atualizacao Monetaria de R$ 21,34, conta FGTS 00000033608, saldo atual R$ 6.243,64. DUVIDAS: 0800-726-0207
        Enviado do meu dispositivo Samsung*/
        if (objSms.getMsg().indexOf("CAIXA informa: Juros e Atualizacao Monetaria")>-1){
            objSms.setValorAtual(objSms.getMsg().substring(objSms.getMsg().indexOf("saldo atual R$ ")+15,objSms.getMsg().indexOf(". DUVIDAS:")));
            objSms.setValorAtualizacaoMonetaria(objSms.getMsg().substring(objSms.getMsg().indexOf("Monetaria de R$ ")+16,objSms.getMsg().indexOf(", conta FGTS")));
            objSms.setValorDeposito("0");
            objSms.setNumeroContaFgts(objSms.getMsg().substring(objSms.getMsg().indexOf("conta FGTS ")+11,objSms.getMsg().indexOf(", saldo atual")));
            objSms.setNumeroCaixa(objSms.getMsg().substring(objSms.getMsg().indexOf("DUVIDAS: ")+9,objSms.getMsg().indexOf("DUVIDAS: ")+9+13));
            objSms.setValorPrevisao("");
            objSms.setVlrAtual(new BigDecimal(objSms.getValorAtual().replace(".","").replace(",",".")));
            objSms.setVlrAtualizacaoMonetaria(new BigDecimal(objSms.getValorAtualizacaoMonetaria().replace(".","").replace(",",".")));
            objSms.setCompetenciaCaixa("");

            try{
                Date d = new Date(new Long(data));
                objSms.setDataCompetenciaCaixa(d);
            }catch (Exception e){}
            objSms.setTipoMovimentacao("Atualização");
            listaSms.add(objSms);
        }else    if (objSms.getMsg().indexOf("CAIXA informa: Deposito ")>-1){
/**CAIXA informa: Deposito R$ 544,34, conta FGTS 00000033608, competencia  01/2017. DUVIDAS: 0800-726-0207**/
            objSms.setValorAtual("");
            objSms.setValorAtualizacaoMonetaria("");
            objSms.setValorDeposito(objSms.getMsg().substring(objSms.getMsg().indexOf("Deposito R")+12,objSms.getMsg().indexOf(", conta")));
            objSms.setNumeroContaFgts(objSms.getMsg().substring(objSms.getMsg().indexOf("conta FGTS ")+11,objSms.getMsg().indexOf(", competencia")));
            objSms.setNumeroCaixa(objSms.getMsg().substring(objSms.getMsg().indexOf("DUVIDAS: ")+9,objSms.getMsg().indexOf("DUVIDAS: ")+9+13));
            objSms.setValorPrevisao("");
            objSms.setCompetenciaCaixa(objSms.getMsg().substring(objSms.getMsg().indexOf("competencia  ")+13,objSms.getMsg().indexOf(". DUVIDAS")));
            try{ objSms.setDataCompetenciaCaixa(new SimpleDateFormat("dd/MM/yyyy").parse("01/"+objSms.getCompetenciaCaixa()));}catch (Exception e){}
            objSms.setVlrAtual(new BigDecimal(0));
            objSms.setVlrAtualizacaoMonetaria(new BigDecimal(0));
            objSms.setTipoMovimentacao("Deposito");
            listaSms.add(objSms);
       //CAIXA informa: Liberacao do valor para saque R$ 4.714,88 em  28/04/2016, conta FGTS 00000000496. DUVIDAS: 0800-726-0207
        }else if (objSms.getMsg().indexOf("CAIXA informa: Liberacao do valor")>-1){
            objSms.setValorAtual("-"+objSms.getMsg().substring(objSms.getMsg().indexOf("saque R$ ")+9,objSms.getMsg().indexOf(" em ")));
            objSms.setValorAtualizacaoMonetaria("");
            objSms.setValorDeposito("");
            objSms.setNumeroContaFgts(objSms.getMsg().substring(objSms.getMsg().indexOf("conta FGTS ")+11,objSms.getMsg().indexOf(". DUVIDAS:")));
            objSms.setNumeroCaixa(objSms.getMsg().substring(objSms.getMsg().indexOf("DUVIDAS: ")+9,objSms.getMsg().indexOf("DUVIDAS: ")+9+13));
            objSms.setValorPrevisao("");
            objSms.setCompetenciaCaixa(objSms.getMsg().substring(objSms.getMsg().indexOf(" em ")+4,objSms.getMsg().indexOf(" em ")+14));
            try{ objSms.setDataCompetenciaCaixa(new SimpleDateFormat("dd/MM/yyyy").parse(objSms.getCompetenciaCaixa()));}catch (Exception e){}
            objSms.setVlrAtual(new BigDecimal(objSms.getValorAtual().replace(".","").replace(",",".")));
            objSms.setVlrAtual(objSms.getVlrAtual().multiply(new BigDecimal(-1)));
            objSms.setVlrAtualizacaoMonetaria(new BigDecimal(0));
            objSms.setTipoMovimentacao("Saque");
            listaSms.add(objSms);
        }

    }







}

