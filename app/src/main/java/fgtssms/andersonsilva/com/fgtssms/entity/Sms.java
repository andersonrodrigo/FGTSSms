package fgtssms.andersonsilva.com.fgtssms.entity;

import android.graphics.Bitmap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Sms {
    private String _id;
    private String _address;
    private String _msg;
    private String _readState; //"0" for have not read sms and "1" for have read sms
    private String _time;
    private String _folderName;

    private String tipoMovimentacao;
    private String numeroTeLigou;
    private String dataLigacao;
    private String horaLigacao;

    private String loja;
    private String dataCompra;
    private String valor;
    private String valorReal;
    private String finalCartao;
    private String banco;

    private String valorDeposito;
    private String valorAtualizacaoMonetaria;
    private String valorAtual;
    private String numeroContaFgts;
    private String nomeConta;
    private String numeroCaixa;
    private String valorPrevisao;
    private String competenciaCaixa;
    private Date dataCompetenciaCaixa;
    private List<Sms> listaMensagensConta;

    private BigDecimal vlrPrevisao;
    private BigDecimal vlrDeposito;
    private BigDecimal vlrAtualizacaoMonetaria;
    private BigDecimal vlrAtual;

    private Bitmap imagemContato;

    private String nomeContato;

    private String dataInicial;
    private String dataFinal;

    private List<String> ocorrencias;

    public String getId(){
        return _id;
    }
    public String getAddress(){
        return _address;
    }
    public String getMsg(){
        return _msg;
    }
    public String getReadState(){
        return _readState;
    }
    public String getTime(){
        return _time;
    }
    public String getFolderName(){
        return _folderName;
    }


    public void setId(String id){
        _id = id;
    }
    public void setAddress(String address){
        _address = address;
    }
    public void setMsg(String msg){
        _msg = msg;
    }
    public void setReadState(String readState){
        _readState = readState;
    }
    public void setTime(String time){
        _time = time;
    }
    public void setFolderName(String folderName){
        _folderName = folderName;
    }


    public BigDecimal getVlrAtual() {
        return vlrAtual;
    }

    public void setVlrAtual(BigDecimal vlrAtual) {
        this.vlrAtual = vlrAtual;
    }

    public BigDecimal getVlrAtualizacaoMonetaria() {
        return vlrAtualizacaoMonetaria;
    }

    public void setVlrAtualizacaoMonetaria(BigDecimal vlrAtualizacaoMonetaria) {
        this.vlrAtualizacaoMonetaria = vlrAtualizacaoMonetaria;
    }

    public BigDecimal getVlrDeposito() {
        return vlrDeposito;
    }

    public void setVlrDeposito(BigDecimal vlrDeposito) {
        this.vlrDeposito = vlrDeposito;
    }

    public String getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(String valorAtual) {
        this.valorAtual = valorAtual;
    }

    public String getValorAtualizacaoMonetaria() {
        return valorAtualizacaoMonetaria;
    }

    public void setValorAtualizacaoMonetaria(String valorAtualizacaoMonetaria) {
        this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
    }

    public String getValorDeposito() {
        return valorDeposito;
    }

    public void setValorDeposito(String valorDeposito) {
        this.valorDeposito = valorDeposito;
    }

    /** BRADESCO CARTOES:
     * COMPRA APROVADA NO CARTAO FINAL 9900
     * EM 12/02/2017 07:40 VALOR DE $ 10,28 NO(A)
     * PANIFICADORA F E C GUI BELO HORIZON.
     * **/

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getValorReal() {
        return valorReal;
    }

    public void setValorReal(String valorReal) {
        this.valorReal = valorReal;
    }

    public String getFinalCartao() {
        return finalCartao;
    }

    public void setFinalCartao(String finalCartao) {
        this.finalCartao = finalCartao;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroTeLigou() {
        return numeroTeLigou;
    }

    public void setNumeroTeLigou(String numeroTeLigou) {
        this.numeroTeLigou = numeroTeLigou;
    }

    public String getHoraLigacao() {
        return horaLigacao;
    }

    public void setHoraLigacao(String horaLigacao) {
        this.horaLigacao = horaLigacao;
    }

    public String getDataLigacao() {
        return dataLigacao;
    }

    public void setDataLigacao(String dataLigacao) {
        this.dataLigacao = dataLigacao;
    }

    public List<String> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<String> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public Bitmap getImagemContato() {
        return imagemContato;
    }

    public void setImagemContato(Bitmap imagemContato) {
        this.imagemContato = imagemContato;
    }

    public String getNumeroContaFgts() {
        return numeroContaFgts;
    }

    public void setNumeroContaFgts(String numeroContaFgts) {
        this.numeroContaFgts = numeroContaFgts;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public String getNumeroCaixa() {
        return numeroCaixa;
    }

    public void setNumeroCaixa(String numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    public String getValorPrevisao() {
        return valorPrevisao;
    }

    public void setValorPrevisao(String valorPrevisao) {
        this.valorPrevisao = valorPrevisao;
    }

    public BigDecimal getVlrPrevisao() {
        return vlrPrevisao;
    }

    public void setVlrPrevisao(BigDecimal vlrPrevisao) {
        this.vlrPrevisao = vlrPrevisao;
    }

    public String getCompetenciaCaixa() {
        return competenciaCaixa;
    }

    public void setCompetenciaCaixa(String competenciaCaixa) {
        this.competenciaCaixa = competenciaCaixa;
    }

    public Date getDataCompetenciaCaixa() {
        return dataCompetenciaCaixa;
    }

    public void setDataCompetenciaCaixa(Date dataCompetenciaCaixa) {
        this.dataCompetenciaCaixa = dataCompetenciaCaixa;
    }

    public List<Sms> getListaMensagensConta() {
        return listaMensagensConta;
    }

    public void setListaMensagensConta(List<Sms> listaMensagensConta) {
        this.listaMensagensConta = listaMensagensConta;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
}