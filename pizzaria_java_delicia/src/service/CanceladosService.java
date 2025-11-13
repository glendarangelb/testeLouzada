package service;

import model.Pedido;
import java.util.Stack;

public class CanceladosService {
    private Stack<Pedido> stack = new Stack<>();

    public void cancelar(Pedido p) { stack.push(p); }

    public Pedido recuperarUltimo() {
        return stack.isEmpty() ? null : stack.pop();
    }

    public Pedido verUltimo() {
        return stack.isEmpty() ? null : stack.peek();
    }

    public boolean vazio() { return stack.isEmpty(); }
}
