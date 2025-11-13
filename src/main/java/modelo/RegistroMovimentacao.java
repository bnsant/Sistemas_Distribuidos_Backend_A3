package modelo;

import java.io.Serializable;

/**
 * Classe que representa um registro de movimentação de estoque.
 * Armazena informações sobre entradas e saídas de produtos do estoque,
 * incluindo tipo de movimentação, quantidade, observações e data.
 * 
 * @author Sistema de Controle de Estoque
 * @version 1.0
 */
public class RegistroMovimentacao implements Serializable {
    /**
     * Versão serial para garantir compatibilidade na serialização.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador único do registro de movimentação.
     */
    private int id;
    
    /**
     * ID do produto relacionado à movimentação.
     */
    private int produtoId;
    
    /**
     * Tipo de movimentação (Entrada ou Saída).
     */
    private String tipoMovimentacao;
    
    /**
     * Quantidade movimentada.
     */
    private int quantidade;
    
    /**
     * Observações sobre a movimentação.
     */
    private String observacao;
    
    /**
     * Data da movimentação.
     */
    private String dataMovimentacao;

    /**
     * Construtor padrão que inicializa um registro vazio.
     */
    public RegistroMovimentacao() {
    }

    /**
     * Construtor completo do registro de movimentação.
     * 
     * @param id Identificador único do registro
     * @param produtoId ID do produto
     * @param tipoMovimentacao Tipo de movimentação (Entrada ou Saída)
     * @param quantidade Quantidade movimentada
     * @param observacao Observações sobre a movimentação
     * @param dataMovimentacao Data da movimentação
     */
    public RegistroMovimentacao(int id, int produtoId, String tipoMovimentacao, int quantidade, String observacao, String dataMovimentacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.dataMovimentacao = dataMovimentacao;
    }

    /**
     * Retorna o identificador único do registro.
     * 
     * @return ID do registro
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único do registro.
     * 
     * @param id ID do registro
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o ID do produto relacionado.
     * 
     * @return ID do produto
     */
    public int getProdutoId() {
        return produtoId;
    }

    /**
     * Define o ID do produto relacionado.
     * 
     * @param produtoId ID do produto
     */
    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    /**
     * Retorna o tipo de movimentação.
     * 
     * @return Tipo de movimentação (Entrada ou Saída)
     */
    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    /**
     * Define o tipo de movimentação.
     * 
     * @param tipoMovimentacao Tipo de movimentação (Entrada ou Saída)
     */
    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    /**
     * Retorna a quantidade movimentada.
     * 
     * @return Quantidade movimentada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade movimentada.
     * 
     * @param quantidade Quantidade movimentada
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna as observações sobre a movimentação.
     * 
     * @return Observações
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Define as observações sobre a movimentação.
     * 
     * @param observacao Observações
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    /**
     * Retorna a data da movimentação.
     * 
     * @return Data da movimentação
     */
    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    /**
     * Define a data da movimentação.
     * 
     * @param dataMovimentacao Data da movimentação
     */
    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
    
    /**
     * Retorna uma representação em string do registro de movimentação.
     * 
     * @return String com os dados do registro
     */
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
