import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private String data;
    private Restaurante restaurante;
    private double valor;
    private Promo promo;
    private StatusEntrega statusEntrega;
    private FormaPagamento formaPagamento;
    private String obs;
    private double troco;
    private Endereco endereco;
    private List<PedidoProduto> produtos;
    private List<Acompanhamento> acompanhamentos;

    public Pedido(int id, String data, Restaurante restaurante, double valor, Promo promo, StatusEntrega statusEntrega, FormaPagamento formaPagamento, String obs, double troco, Endereco endereco) {
        this.id = id;
        this.data = data;
        this.restaurante = restaurante;
        this.valor = valor;
        this.promo = promo;
        this.statusEntrega = statusEntrega;
        this.formaPagamento = formaPagamento;
        this.obs = obs;
        this.troco = troco;
        this.endereco = endereco;
        this.produtos = new ArrayList<>();
        this.acompanhamentos = new ArrayList<>();
    }

    public double getValor() {
        return this.valor;
    }

    public List<Acompanhamento> getAcompanhamentos() {
        return this.acompanhamentos;
    }

    public void addProduto(PedidoProduto pedidoProduto) {
        produtos.add(pedidoProduto);
    }

    public void addAcompanhamento(Acompanhamento acompanhamento) {
        acompanhamentos.add(acompanhamento);
    }

    public int getId() {
        return this.id;
    }

    public String getData() {
        return this.data;
    }

    public List<PedidoProduto> getProdutos() {
        return this.produtos;
    }

}
