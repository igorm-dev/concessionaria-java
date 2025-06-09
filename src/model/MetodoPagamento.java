package model;

public enum MetodoPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    PIX("Pix"),
    BOLETO("Boleto");

    private final String descricao;

    private MetodoPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDeStringcao() {
        return this.descricao;
    }
}
