import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<PedidoProduto> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        PedidoProduto item = new PedidoProduto(null, produto, quantidade);
        this.itens.add(item);
        System.out.println("Produto adicionado ao carrinho: " + produto.getNome());
    }

    public List<PedidoProduto> getItens() {
        return this.itens;
    }

    public double calcularTotal() {
        double total = 0;
        for (PedidoProduto item : itens) {
            total += item.getProduto().getPreco() * item.getQuantidade();
        }
        return total;
    }

    public void esvaziarCarrinho() {
        itens.clear();
    }
}
