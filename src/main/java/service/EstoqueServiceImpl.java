package service;

import dao.ProdutoDAO;
import dao.CategoriaDAO;
import dao.RegistroMovimentacaoDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import modelo.Produto;
import modelo.Categoria;
import modelo.RegistroMovimentacao;
import java.sql.SQLException;

public class EstoqueServiceImpl extends UnicastRemoteObject implements EstoqueService {

    private final ProdutoDAO produtoDAO;
    private final CategoriaDAO categoriaDAO;
    private final RegistroMovimentacaoDAO registroMovimentacaoDAO;

    public EstoqueServiceImpl() throws RemoteException {
        this.produtoDAO = new ProdutoDAO();
        this.categoriaDAO = new CategoriaDAO();
        this.registroMovimentacaoDAO = new RegistroMovimentacaoDAO();
    }

    // ========== PRODUTO ==========
    @Override
    public void criarProduto(Produto p) throws RemoteException {
        produtoDAO.CadastrarProduto(p);
    }

    @Override
    public void atualizarProduto(Produto p) throws RemoteException {
        produtoDAO.AtualizarProduto(p);
    }

    @Override
    public void excluirProduto(int id) throws RemoteException {
        produtoDAO.DeletarProdutoID(id);
    }

    @Override
    public List<Produto> listarProdutos() throws RemoteException {
        return produtoDAO.getMinhaListaProdutos();
    }

    // Métodos adicionais do ProdutoDAO
    public Produto buscarProdutoPorId(int id) throws RemoteException {
        return produtoDAO.ProcurarProdutoID(id);
    }

    public Produto buscarProdutoPorNome(String nome) throws RemoteException {
        return produtoDAO.ProcurarProdutoNome(nome);
    }

    public int obterMaiorIdProduto() throws RemoteException {
        return produtoDAO.MaiorID();
    }

    public ArrayList<String> buscarCategoriasProdutos() throws RemoteException {
        return produtoDAO.buscarCategorias();
    }

    public List<Produto> buscarProdutosPorCategoria(String categoria) throws RemoteException {
        try {
            return produtoDAO.buscarPorCategoria(categoria);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao buscar produtos por categoria: " + e.getMessage());
        }
    }

    public List<Produto> buscarProdutosPorNome(String nome) throws RemoteException {
        try {
            return produtoDAO.buscarPorNome(nome);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao buscar produtos por nome: " + e.getMessage());
        }
    }

    public List<Produto> buscarProdutosPorNomeECategoria(String nome, String categoria) throws RemoteException {
        try {
            return produtoDAO.buscarPorNomeECategoria(nome, categoria);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao buscar produtos por nome e categoria: " + e.getMessage());
        }
    }

    public boolean registrarEntradaProduto(int produtoId, int quantidadeEntrada, String observacao) throws RemoteException {
        return produtoDAO.RegistrarEntradaProduto(produtoId, quantidadeEntrada, observacao);
    }

    public boolean registrarSaidaProduto(int produtoId, int quantidadeSaida, String observacao) throws RemoteException {
        return produtoDAO.RegistrarSaidaProduto(produtoId, quantidadeSaida, observacao);
    }

    public void atualizarPrecoProduto(int idProduto, double novoPreco) throws RemoteException {
        try {
            produtoDAO.atualizarPreco(idProduto, novoPreco);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao atualizar preço: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutosOrdenadosPorNome() throws RemoteException {
        return produtoDAO.listarProdutoOrdenadoPorNome();
    }

    public List<Produto> listarProdutosAbaixoMinMax() throws RemoteException {
        return produtoDAO.listarProdutosAbaixoMinMax();
    }

    public List<String[]> listarQuantidadePorCategoria() throws RemoteException {
        return produtoDAO.listarQuantidadePorCategoria();
    }

    // ========== CATEGORIA ==========
    @Override
    public void criarCategoria(Categoria c) throws RemoteException {
        try {
            categoriaDAO.salvar(c);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao criar categoria: " + e.getMessage());
        }
    }

    @Override
    public void atualizarCategoria(Categoria c) throws RemoteException {
        try {
            categoriaDAO.atualizar(c);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    @Override
    public void excluirCategoria(int id) throws RemoteException {
        try {
            categoriaDAO.excluir(id);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao excluir categoria: " + e.getMessage());
        }
    }

    @Override
    public List<Categoria> listarCategorias() throws RemoteException {
        try {
            return categoriaDAO.listarCategorias();
        } catch (SQLException e) {
            throw new RemoteException("Erro ao listar categorias: " + e.getMessage());
        }
    }

    // ========== MOVIMENTAÇÃO ==========
    @Override
    public void registrarMovimentacao(RegistroMovimentacao m) throws RemoteException {
        registroMovimentacaoDAO.registrarMovimentacao(m);
    }

    @Override
    public List<RegistroMovimentacao> listarMovimentacoes() throws RemoteException {
        return registroMovimentacaoDAO.listarTodasMovimentacoes();
    }

    // Métodos adicionais do RegistroMovimentacaoDAO
    public List<RegistroMovimentacao> listarMovimentacoesPorProduto(int produtoId) throws RemoteException {
        return registroMovimentacaoDAO.listarMovimentacoesPorProduto(produtoId);
    }
}
