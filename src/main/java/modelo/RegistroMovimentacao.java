package modelo;

import java.io.Serializable;

public class RegistroMovimentacao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int produtoId;
    private String tipoMovimentacao;
    private int quantidade;
    private String observacao;
    private String dataMovimentacao;

    public RegistroMovimentacao() {
    }

    public RegistroMovimentacao(int id, int produtoId, String tipoMovimentacao, int quantidade, String observacao, String dataMovimentacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.dataMovimentacao = dataMovimentacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
    
    @Override
    public String toString(){
        return "RegistroMovimentacao{"+
                "id ="+id+
                ",produto id ="+ produtoId+
                ",tipoMovimentacao="+ tipoMovimentacao +'\''+
                "quantidade ="+quantidade+
                "observacao="+observacao+ '\''+
                ", dataMovimentacao='" + dataMovimentacao + '\'' +
            '}';
    }
    
}
