package model;

public class Pedido {
    private int numero;
    private String cliente;
    private String sabor;
    private char tamanho;
    private double valor;
    private int tempoPreparo;

    public Pedido(int numero, String cliente, String sabor, char tamanho, double valor, int tempoPreparo) {
        this.numero = numero;
        this.cliente = cliente;
        this.sabor = sabor;
        this.tamanho = Character.toUpperCase(tamanho);
        this.valor = valor;
        this.tempoPreparo = tempoPreparo;
    }

    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public String getSabor() { return sabor; }
    public char getTamanho() { return tamanho; }
    public double getValor() { return valor; }
    public int getTempoPreparo() { return tempoPreparo; }

    @Override
    public String toString() {
        return String.format("Pedido{#%d, cliente='%s', sabor='%s', tam=%s, R$ %.2f, tempo=%dmin}",
                numero, cliente, sabor, tamanho, valor, tempoPreparo);
    }
}
