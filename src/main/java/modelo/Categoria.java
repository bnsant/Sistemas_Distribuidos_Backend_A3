package modelo;

import java.io.Serializable;

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nomeCategoria;
    private String tamanho;
    private String embalagem;

    public Categoria(int id, String nomeCategoria, String tamanho, String embalagem) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.tamanho = tamanho; 
        this.embalagem = embalagem; 
    }

    public Categoria() {
        this(0, "", "", "");
    }

    public Categoria(String nomeCategoria, String tamanho, String embalagem) {
        this(0, nomeCategoria, tamanho, embalagem);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nome) {
        this.nomeCategoria = nome;
    }

    @Override
    public String toString() {
        return nomeCategoria;
    }
    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }
}
