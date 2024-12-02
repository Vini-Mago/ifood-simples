public class Promo {
    private int id;
    private String nome;
    private String codigo;
    private double valor;
    private double valorDesconto;

    public Promo(int id, String nome, String codigo, double valor, double valorDesconto) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.valorDesconto = valorDesconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }


}
