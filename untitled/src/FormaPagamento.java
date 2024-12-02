public class FormaPagamento {
    private int id;
    private String tipoDePagamento;

    public FormaPagamento(int id, String tipoDePagamento) {
        this.id = id;
        this.tipoDePagamento = tipoDePagamento;
    }

    public int getId() {
        return id;
    }

    public String getTipoDePagamento() {
        return tipoDePagamento;
    }
}
