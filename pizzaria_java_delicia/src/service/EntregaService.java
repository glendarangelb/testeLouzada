package service;

import model.Pedido;
import java.util.LinkedList;
import java.util.Queue;

public class EntregaService {
    private Queue<Pedido> fila = new LinkedList<>();

    public void adicionar(Pedido p) { fila.add(p); }

    public Pedido entregar() { return fila.poll(); } // remove head or null

    public Pedido proximo() { return fila.peek(); }

    public boolean vazia() { return fila.isEmpty(); }

    public int tamanhoFila() { return fila.size(); }
}
