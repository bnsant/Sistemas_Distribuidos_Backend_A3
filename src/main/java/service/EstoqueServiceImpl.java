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

/**
 * Implementação do serviço remoto de estoque.
 * Fornece a implementação concreta de todos os métodos definidos na interface EstoqueService.
 * Utiliza os DAOs para realizar operações no banco de dados.
 * 
 * @author bnsant
 * @version 1.0
 */
public class EstoqueServiceImpl extends UnicastRemoteObject implements EstoqueService {

    /**
     * DAO para operações com produtos.
     */
    private final ProdutoDAO produtoDAO;
    
    /**
     * DAO para operações com categorias.
     */
    private final CategoriaDAO categoriaDAO;
    
    /**
     * DAO para operações com registros de movimentação.
     */
    private final RegistroMovimentacaoDAO registroMovimentacaoDAO;

    /**
     * Construtor que inicializa os DAOs necessários.
     * 
     * @throws RemoteException Se ocorrer erro na inicialização do objeto remoto
     */
    public EstoqueServiceImpl() throws RemoteException {
        this.produtoDAO = new ProdutoDAO();
        this.categoriaDAO = new CategoriaDAO();
        this.registroMovimentacaoDAO = new RegistroMovimentacaoDAO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void criarProduto(Produto p) throws RemoteException {
        produtoDAO.CadastrarProduto(p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void atualizarProduto(Produto p) throws RemoteException {
        produtoDAO.AtualizarProduto(p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void excluirProduto(int id) throws RemoteException {
        produtoDAO.DeletarProdutoID(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Produto> listarProdutos() throws RemoteException {
        return produtoDAO.getMinhaListaProdutos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Produto buscarProdutoPorId(int id) throws RemoteException {
        return produtoDAO.ProcurarProdutoID(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Produto buscarProdutoPorNome(String nome) throws RemoteException {
        return produtoDAO.ProcurarProdutoNome(nome);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void criarCategoria(Categoria c) throws RemoteException {
        try {
            categoriaDAO.salvar(c);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao criar categoria: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void atualizarCategoria(Categoria c) throws RemoteException {
        try {
            categoriaDAO.atualizar(c);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void excluirCategoria(int id) throws RemoteException {
        try {
            categoriaDAO.excluir(id);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao excluir categoria: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Categoria> listarCategorias() throws RemoteException {
        try {
            return categoriaDAO.listarCategorias();
        } catch (SQLException e) {
            throw new RemoteException("Erro ao listar categorias: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registrarMovimentacao(RegistroMovimentacao m) throws RemoteException {
        registroMovimentacaoDAO.registrarMovimentacaoEAtualizarSaldo(m, produtoDAO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegistroMovimentacao> listarMovimentacoes() throws RemoteException {
        return registroMovimentacaoDAO.listarTodasMovimentacoes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegistroMovimentacao> listarMovimentacoesPorProduto(int produtoId) throws RemoteException {
        return registroMovimentacaoDAO.listarMovimentacoesPorProduto(produtoId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Produto> listarProdutosOrdenadosPorNome() throws RemoteException {
        return produtoDAO.listarProdutoOrdenadoPorNome();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Produto> listarProdutosAbaixoMinimo() throws RemoteException {
        return produtoDAO.listarProdutosAbaixoMinimo();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String[]> listarQuantidadePorCategoria() throws RemoteException {
        return produtoDAO.listarQuantidadePorCategoria();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object[]> listarBalancoFisicoFinanceiro() throws RemoteException {
        return produtoDAO.listarBalancoFisicoFinanceiro();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double calcularValorTotalEstoque() throws RemoteException {
        return produtoDAO.calcularValorTotalEstoque();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String[] produtoComMaisEntrada() throws RemoteException {
        return registroMovimentacaoDAO.produtoComMaisEntrada();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String[] produtoComMaisSaida() throws RemoteException {
        return registroMovimentacaoDAO.produtoComMaisSaida();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean reajustarPrecosPercentual(double percentual) throws RemoteException {
        try {
            return produtoDAO.reajustarPrecosPercentual(percentual);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao reajustar preços: " + e.getMessage());
        }
    }
}
