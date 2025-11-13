package modelo;

import java.io.Serializable;

/**
 * Classe que representa uma categoria de produtos no sistema de estoque.
 * Uma categoria agrupa produtos com características similares e possui
 * informações sobre tamanho e embalagem.
 * 
 * @author Sistema de Controle de Estoque
 * @version 1.0
 */
public class Categoria implements Serializable {
    /**
     * Versão serial para garantir compatibilidade na serialização.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador único da categoria.
     */
    private int id;
    
    /**
     * Nome da categoria.
     */
    private String nomeCategoria;
    
    /**
     * Tamanho da categoria (Pequeno, Médio, Grande).
     */
    private String tamanho;
    
    /**
     * Tipo de embalagem (Lata, Vidro, Plástico, etc.).
     */
    private String embalagem;

    /**
     * Construtor completo da categoria.
     * 
     * @param id Identificador único da categoria
     * @param nomeCategoria Nome da categoria
     * @param tamanho Tamanho da categoria
     * @param embalagem Tipo de embalagem
     */
    public Categoria(int id, String nomeCategoria, String tamanho, String embalagem) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.tamanho = tamanho; 
        this.embalagem = embalagem; 
    }

    /**
     * Construtor padrão que inicializa uma categoria vazia.
     */
    public Categoria() {
        this(0, "", "", "");
    }

    /**
     * Construtor que cria uma nova categoria sem ID (será gerado pelo banco).
     * 
     * @param nomeCategoria Nome da categoria
     * @param tamanho Tamanho da categoria
     * @param embalagem Tipo de embalagem
     */
    public Categoria(String nomeCategoria, String tamanho, String embalagem) {
        this(0, nomeCategoria, tamanho, embalagem);
    }

    /**
     * Retorna o identificador único da categoria.
     * 
     * @return ID da categoria
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único da categoria.
     * 
     * @param id ID da categoria
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome da categoria.
     * 
     * @return Nome da categoria
     */
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    /**
     * Define o nome da categoria.
     * 
     * @param nome Nome da categoria
     */
    public void setNomeCategoria(String nome) {
        this.nomeCategoria = nome;
    }

    /**
     * Retorna uma representação em string da categoria (apenas o nome).
     * 
     * @return Nome da categoria
     */
    @Override
    public String toString() {
        return nomeCategoria;
    }
    
    /**
     * Retorna o tamanho da categoria.
     * 
     * @return Tamanho da categoria
     */
    public String getTamanho() {
        return tamanho;
    }

    /**
     * Define o tamanho da categoria.
     * 
     * @param tamanho Tamanho da categoria
     */
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Retorna o tipo de embalagem da categoria.
     * 
     * @return Tipo de embalagem
     */
    public String getEmbalagem() {
        return embalagem;
    }

    /**
     * Define o tipo de embalagem da categoria.
     * 
     * @param embalagem Tipo de embalagem
     */
    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }
}
