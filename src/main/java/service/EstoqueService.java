package service;

/**
 * Interface remota agregadora de todos os serviços do estoque.
 * Esta interface estende as interfaces específicas de Produto, Categoria, 
 * Movimentação e Relatórios, fornecendo um ponto único de acesso a todas
 * as operações disponíveis no sistema de gerenciamento de estoque.
 * 
 * Todos os métodos são herdados das interfaces estendidas:
 * - ProdutoService: operações relacionadas a produtos
 * - CategoriaService: operações relacionadas a categorias
 * - MovimentacaoService: operações de movimentação de estoque
 * - RelatorioService: geração de relatórios e consultas consolidadas
 * 
 * @author bnsant
 * @version 1.0
 */
public interface EstoqueService extends 
        ProdutoService,
        CategoriaService,
        MovimentacaoService,
        RelatorioService {
    // Nenhum método aqui. Todos vêm das interfaces estendidas.
}
