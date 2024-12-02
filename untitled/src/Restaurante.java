public class Restaurante {
    private int id;
    private String nome;
    private Endereco endereco;
    private String telefone;
    private String categoria;
    private String horarioFuncionamento;
    private boolean isRetirada;

    public Restaurante(int id, String nome, Endereco endereco, String telefone, String categoria, String horarioFuncionamento, boolean isRetirada) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.categoria = categoria;
        this.horarioFuncionamento = horarioFuncionamento;
        this.isRetirada = isRetirada;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public boolean isRetirada() {
        return isRetirada;
    }
}
