package service;

import model.Pedido;
import java.util.*;

public class PizzeriaService {
    private Set<String> sabores = new HashSet<>();
    private List<Pedido> pedidosAbertos = new ArrayList<>();
    private Queue<Pedido> filaEntregas = new LinkedList<>();
    private PriorityQueue<Pedido> filaPrioritaria =
            new PriorityQueue<>(Comparator.comparingInt(Pedido::getTempoPreparo));
    private Stack<Pedido> pedidosCancelados = new Stack<>();
    private Map<String, Integer> vendasPorSabor = new HashMap<>();

    public boolean adicionarSabor(String sabor) { return sabores.add(sabor); }
    public boolean removerSabor(String sabor) { return sabores.remove(sabor); }
    public boolean existeSabor(String sabor) { return sabores.contains(sabor); }
    public Set<String> listarSabores() { return Collections.unmodifiableSet(sabores); }

    public void adicionarPedido(Pedido p) { pedidosAbertos.add(p); }
    public List<Pedido> listarPedidosAbertos() { return Collections.unmodifiableList(pedidosAbertos); }

    public Pedido buscarPedidoPorNumero(int n) {
        for (Pedido p : pedidosAbertos) if (p.getNumero() == n) return p;
        return null;
    }

    public void enfileirarEntrega(Pedido p) { filaEntregas.add(p); }
    public Pedido entregarProximo() { return filaEntregas.poll(); }

    public void adicionarPrioritario(Pedido p) { filaPrioritaria.add(p); }
    public Pedido pegarPrioritario() { return filaPrioritaria.poll(); }

    public void cancelarPedido(Pedido p) {
        pedidosAbertos.remove(p);
        pedidosCancelados.push(p);
    }

    public void registrarVenda(Pedido p) {
        vendasPorSabor.put(p.getSabor(), vendasPorSabor.getOrDefault(p.getSabor(), 0) + 1);
    }

    public List<Map.Entry<String,Integer>> rankingSabores() {
        List<Map.Entry<String,Integer>> lista = new ArrayList<>(vendasPorSabor.entrySet());
        lista.sort((a,b) -> b.getValue() - a.getValue());
        return lista;
    }
}
