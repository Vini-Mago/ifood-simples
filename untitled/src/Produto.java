public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private Categoria categoria;
    private Restaurante restaurante;

    public Produto(int id, String nome, String descricao, double preco, Categoria categoria, Restaurante restaurante) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.restaurante = restaurante;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
}
