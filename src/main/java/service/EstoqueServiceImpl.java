package service;

import dao.ProdutoDAO;
import dao.CategoriaDAO;
import dao.RegistroMovimentacaoDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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

    @Override
    public Produto buscarProdutoPorId(int id) throws RemoteException {
        return produtoDAO.ProcurarProdutoID(id);
    }

    @Override
    public Produto buscarProdutoPorNome(String nome) throws RemoteException {
        return produtoDAO.ProcurarProdutoNome(nome);
    }

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

    @Override
    public void registrarMovimentacao(RegistroMovimentacao m) throws RemoteException {
        registroMovimentacaoDAO.registrarMovimentacaoEAtualizarSaldo(m, produtoDAO);
    }

    @Override
    public List<RegistroMovimentacao> listarMovimentacoes() throws RemoteException {
        return registroMovimentacaoDAO.listarTodasMovimentacoes();
    }

    @Override
    public List<RegistroMovimentacao> listarMovimentacoesPorProduto(int produtoId) throws RemoteException {
        return registroMovimentacaoDAO.listarMovimentacoesPorProduto(produtoId);
    }
    
    @Override
    public List<Produto> listarProdutosOrdenadosPorNome() throws RemoteException {
        return produtoDAO.listarProdutoOrdenadoPorNome();
    }
    
    @Override
    public List<Produto> listarProdutosAbaixoMinimo() throws RemoteException {
        return produtoDAO.listarProdutosAbaixoMinimo();
    }
    
    @Override
    public List<String[]> listarQuantidadePorCategoria() throws RemoteException {
        return produtoDAO.listarQuantidadePorCategoria();
    }
    
    @Override
    public List<Object[]> listarBalancoFisicoFinanceiro() throws RemoteException {
        return produtoDAO.listarBalancoFisicoFinanceiro();
    }
    
    @Override
    public double calcularValorTotalEstoque() throws RemoteException {
        return produtoDAO.calcularValorTotalEstoque();
    }
    
    @Override
    public String[] produtoComMaisEntrada() throws RemoteException {
        return registroMovimentacaoDAO.produtoComMaisEntrada();
    }
    
    @Override
    public String[] produtoComMaisSaida() throws RemoteException {
        return registroMovimentacaoDAO.produtoComMaisSaida();
    }
    
    @Override
    public boolean reajustarPrecosPercentual(double percentual) throws RemoteException {
        try {
            return produtoDAO.reajustarPrecosPercentual(percentual);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao reajustar preços: " + e.getMessage());
        }
    }
}
