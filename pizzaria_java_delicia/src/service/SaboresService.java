package service;

import java.util.HashSet;
import java.util.Set;

public class SaboresService {
    private Set<String> sabores = new HashSet<>();

    public boolean adicionar(String sabor) {
        if (sabor == null) return false;
        return sabores.add(sabor.trim());
    }

    public boolean remover(String sabor) {
        return sabores.remove(sabor);
    }

    public boolean existe(String sabor) {
        return sabores.contains(sabor);
    }

    public Set<String> listar() {
        return sabores;
    }
}
