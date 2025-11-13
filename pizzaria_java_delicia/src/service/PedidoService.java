package service;

import model.Pedido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PedidoService {
    private List<Pedido> pedidosAbertos = new ArrayList<>();

    public void adicionar(Pedido p) {
        pedidosAbertos.add(p);
    }

    public List<Pedido> listar() {
        return new ArrayList<>(pedidosAbertos);
    }

    public Pedido buscarPorNumero(int numero) {
        for (Pedido p : pedidosAbertos) {
            if (p.getNumero() == numero) return p;
        }
        return null;
    }

    public boolean removerPorNumero(int numero) {
        Pedido p = buscarPorNumero(numero);
        if (p != null) {
            return pedidosAbertos.remove(p);
        }
        return false;
    }

    public void ordenarPorValor() {
        Collections.sort(pedidosAbertos, Comparator.comparingDouble(Pedido::getValor));
    }

    public void ordenarPorCliente() {
        Collections.sort(pedidosAbertos, Comparator.comparing(Pedido::getCliente));
    }
}
