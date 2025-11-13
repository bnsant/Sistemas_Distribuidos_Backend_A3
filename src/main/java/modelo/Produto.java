package modelo;

import java.io.Serializable;

/**
 * Classe que representa um produto no sistema de controle de estoque.
 * Contém informações sobre identificação, nome, preço, quantidade em estoque,
 * limites mínimo e máximo, unidade de medida e categoria.
 * 
 * @author Sistema de Controle de Estoque
 * @version 1.0
 */
public class Produto implements Serializable {
    /**
     * Versão serial para garantir compatibilidade na serialização.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador único do produto.
     */
    private int id;
    
    /**
     * Nome do produto.
     */
    private String nome;
    
    /**
     * Unidade de medida do produto (kg, litros, unidades, etc.).
     */
    private String unidade;
    
    /**
     * Preço unitário do produto.
     */
    private double preco;
    
    /**
     * Quantidade atual em estoque.
     */
    private int quantidade;
    
    /**
     * Quantidade mínima permitida em estoque.
     */
    private int min;
    
    /**
     * Quantidade máxima permitida em estoque.
     */
    private int max;
    
    /**
     * Categoria à qual o produto pertence.
     */
    private String categoria;

    /**
     * Construtor padrão que inicializa um produto com valores padrão.
     */
    public Produto() {
        this(0,"","",0.0,0,0,1000,"");
    }

    /**
     * Construtor completo do produto.
     * 
     * @param id Identificador único do produto
     * @param nome Nome do produto
     * @param unidade Unidade de medida
     * @param preco Preço unitário
     * @param quantidade Quantidade em estoque
     * @param min Quantidade mínima
     * @param max Quantidade máxima
     * @param categoria Categoria do produto
     */
    public Produto(int id, String nome, String unidade, double preco, int quantidade,int min, int max, String categoria) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.preco = preco;
        this.quantidade = quantidade;
        this.min = min;
        this.max = max;
        this.categoria = categoria;
    }

    /**
     * Retorna o identificador único do produto.
     * 
     * @return ID do produto
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único do produto.
     * 
     * @param id ID do produto
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     * 
     * @return Nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     * 
     * @param nome Nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a unidade de medida do produto.
     * 
     * @return Unidade de medida
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * Define a unidade de medida do produto.
     * 
     * @param unidade Unidade de medida
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * Retorna o preço unitário do produto.
     * 
     * @return Preço unitário
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço unitário do produto.
     * 
     * @param preco Preço unitário
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    /**
     * Retorna a quantidade atual em estoque.
     * 
     * @return Quantidade em estoque
     */
    public int getQuantidade(){
        return quantidade;
    }
    
    /**
     * Define a quantidade atual em estoque.
     * 
     * @param quantidade Quantidade em estoque
     */
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;  
    }

    /**
     * Retorna a quantidade mínima permitida.
     * 
     * @return Quantidade mínima
     */
    public int getMin() {
        return min;
    }

    /**
     * Define a quantidade mínima permitida.
     * 
     * @param min Quantidade mínima
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Retorna a quantidade máxima permitida.
     * 
     * @return Quantidade máxima
     */
    public int getMax() {
        return max;
    }

    /**
     * Define a quantidade máxima permitida.
     * 
     * @param max Quantidade máxima
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Retorna a categoria do produto.
     * 
     * @return Categoria do produto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     * 
     * @param nomeCategoria Nome da categoria
     */
    public void setCategoria(String nomeCategoria) {
        this.categoria = nomeCategoria;
    }
    
    /**
     * Retorna uma representação em string do produto (apenas o nome).
     * 
     * @return Nome do produto
     */
    @Override
    public String toString(){
        return this.nome;
    }
    
    /**
     * Verifica se a quantidade do produto está dentro dos limites permitidos.
     * Retorna uma mensagem indicando se está abaixo do mínimo, acima do máximo ou dentro do normal.
     * 
     * @return Mensagem de verificação da quantidade
     */
    public String VerificacaoDeQuantidade(){
        if (this.getQuantidade()<this.getMin()){
            return "A quantidade do produto: "+getNome()+" /está muito baixa, a quantidade minima é "+getMin()+" unidades";
            
        }else if(this.getQuantidade()>this.getMax()){
            return "A quantidade do produto:"+getNome()+" /é muito alta, a quantidade máxima é "+getMax()+" unidades";
        }else{
            return "produto registrado com sucesso. A quantidade é "+getQuantidade()+" unidades";
        }
    }
}

