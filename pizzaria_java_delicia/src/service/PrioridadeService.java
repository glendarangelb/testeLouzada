package service;

import model.Pedido;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PrioridadeService {
    private PriorityQueue<Pedido> pq;

    public PrioridadeService() {
        // menor tempoPreparo -> maior prioridade
        pq = new PriorityQueue<>(Comparator.comparingInt(Pedido::getTempoPreparo));
    }

    public void adicionar(Pedido p) { pq.add(p); }

    public Pedido poll() { return pq.poll(); }

    public Pedido peek() { return pq.peek(); }

    public boolean vazia() { return pq.isEmpty(); }

    public int tamanho() { return pq.size(); }
}
