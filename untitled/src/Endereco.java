public class Endereco {
    private int id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    public Endereco(int id, String rua, int numero, String bairro, String cidade, String estado, String cep, String complemento) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return rua + ", " + numero + ", " + bairro + ", " + cidade + " - " + estado + ", " + cep + (complemento.isEmpty() ? "" : " (" + complemento + ")");
    }
}