package service;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class VendasService {
    private Map<String, Integer> vendas = new HashMap<>();

    public void registrarVenda(String sabor) {
        if (sabor == null) return;
        vendas.put(sabor, vendas.getOrDefault(sabor, 0) + 1);
    }

    public int totalVendido(String sabor) {
        return vendas.getOrDefault(sabor, 0);
    }

    public Map<String, Integer> all() {
        return vendas;
    }

    public Map<String, Integer> ranking() {
        // retorna um map ordenado por quantidade desc (LinkedHashMap)
        return vendas.entrySet().stream()
                .sorted((a,b) -> b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
