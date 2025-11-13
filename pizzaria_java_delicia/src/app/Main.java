package app;

import model.Pedido;
import service.*;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static SaboresService sabores = new SaboresService();
    private static PedidoService pedidoService = new PedidoService();
    private static EntregaService entregaService = new EntregaService();
    private static PrioridadeService prioridadeService = new PrioridadeService();
    private static CanceladosService canceladosService = new CanceladosService();
    private static VendasService vendasService = new VendasService();
    private static Scanner sc = new Scanner(System.in);
    private static int contadorPedidos = 1;

    public static void main(String[] args) {
        seed(); // sabores iniciais para facilitar testes
        boolean running = true;
        while (running) {
            showMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": cadastrarSabor(); break;
                case "2": listarSabores(); break;
                case "3": adicionarPedido(); break;
                case "4": listarPedidos(); break;
                case "5": buscarPedido(); break;
                case "6": adicionarFila(); break;
                case "7": entregar(); break;
                case "8": verProximo(); break;
                case "9": adicionarPrioridade(); break;
                case "10": processarPrioritario(); break;
                case "11": cancelarPedido(); break;
                case "12": recuperarCancelado(); break;
                case "13": rankingSabores(); break;
                case "0": running = false; System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida"); break;
            }
        }
        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n=== Pizzaria Java Delícia - MENU ===");
        System.out.println("1 - Cadastrar sabor");
        System.out.println("2 - Listar sabores");
        System.out.println("3 - Adicionar pedido (aberto)");
        System.out.println("4 - Listar pedidos abertos");
        System.out.println("5 - Buscar pedido por número");
        System.out.println("6 - Adicionar pedido à fila de entrega");
        System.out.println("7 - Entregar próximo da fila");
        System.out.println("8 - Ver próximo da fila");
        System.out.println("9 - Adicionar pedido prioritário (express/VIP)");
        System.out.println("10 - Processar próximo prioritário");
        System.out.println("11 - Cancelar pedido");
        System.out.println("12 - Recuperar último pedido cancelado");
        System.out.println("13 - Ranking de sabores vendidos");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static void seed() {
        sabores.adicionar("Calabresa");
        sabores.adicionar("Mussarela");
        sabores.adicionar("Portuguesa");
    }

    private static void cadastrarSabor() {
        System.out.print("Nome do sabor: ");
        String s = sc.nextLine().trim();
        if (sabores.adicionar(s)) System.out.println("Sabor adicionado.");
        else System.out.println("Sabor já existe ou inválido.");
    }

    private static void listarSabores() {
        System.out.println("Sabores disponíveis:" );
        sabores.listar().forEach(s -> System.out.println(" - " + s));
    }

    private static void adicionarPedido() {
        try {
            System.out.print("Nome do cliente: "); String cliente = sc.nextLine();
            System.out.print("Sabor: "); String sabor = sc.nextLine();
            if (!sabores.existe(sabor)) {
                System.out.println("Sabor não cadastrado. Adicione antes de usar."); return;
            }
            System.out.print("Tamanho (P/M/G): "); char tam = sc.nextLine().charAt(0);
            System.out.print("Valor (ex: 39.90): "); double valor = Double.parseDouble(sc.nextLine());
            System.out.print("Tempo preparo (min): "); int tempo = Integer.parseInt(sc.nextLine());
            Pedido p = new Pedido(contadorPedidos++, cliente, sabor, tam, valor, tempo);
            pedidoService.adicionar(p);
            vendasService.registrarVenda(sabor);
            System.out.println("Pedido adicionado: " + p);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar pedido: " + e.getMessage());
        }
    }

    private static void listarPedidos() {
        System.out.println("Pedidos abertos:");
        pedidoService.listar().forEach(p -> System.out.println(" - " + p));
        System.out.println("Escolha ordenação? 1-valor 2-cliente outro-cancelar: ");
        String opt = sc.nextLine().trim();
        if (opt.equals("1")) { pedidoService.ordenarPorValor(); System.out.println("Ordenado por valor."); }
        else if (opt.equals("2")) { pedidoService.ordenarPorCliente(); System.out.println("Ordenado por cliente."); }
    }

    private static void buscarPedido() {
        System.out.print("Número do pedido: ");
        int n = Integer.parseInt(sc.nextLine());
        Pedido p = pedidoService.buscarPorNumero(n);
        System.out.println(p == null ? "Pedido não encontrado." : p);
    }

    private static void adicionarFila() {
        System.out.print("Número do pedido a enfileirar: ");
        int n = Integer.parseInt(sc.nextLine());
        Pedido p = pedidoService.buscarPorNumero(n);
        if (p == null) { System.out.println("Pedido não existe."); return; }
        entregaService.adicionar(p);
        System.out.println("Pedido colocado na fila de entrega."); 
    }

    private static void entregar() {
        Pedido p = entregaService.entregar();
        if (p == null) System.out.println("Fila vazia."); 
        else System.out.println("Entregue: " + p);
    }

    private static void verProximo() {
        Pedido p = entregaService.proximo();
        if (p == null) System.out.println("Fila vazia."); else System.out.println("Próximo: " + p);
    }

    private static void adicionarPrioridade() {
        System.out.print("Número do pedido prioritário: ");
        int n = Integer.parseInt(sc.nextLine());
        Pedido p = pedidoService.buscarPorNumero(n);
        if (p == null) { System.out.println("Pedido não existe."); return; }
        prioridadeService.adicionar(p);
        System.out.println("Pedido adicionado à fila prioritária."); 
    }

    private static void processarPrioritario() {
        Pedido p = prioridadeService.poll();
        if (p == null) System.out.println("Nenhum pedido prioritário.");
        else System.out.println("Processando prioritário: " + p);
    }

    private static void cancelarPedido() {
        System.out.print("Número do pedido a cancelar: ");
        int n = Integer.parseInt(sc.nextLine());
        Pedido p = pedidoService.buscarPorNumero(n);
        if (p == null) { System.out.println("Pedido não encontrado."); return; }
        if (pedidoService.removerPorNumero(n)) {
            canceladosService.cancelar(p);
            System.out.println("Pedido cancelado e registrado no histórico.");
        }
    }

    private static void recuperarCancelado() {
        Pedido p = canceladosService.recuperarUltimo();
        if (p == null) System.out.println("Nenhum pedido cancelado."); else {
            System.out.println("Recuperado: " + p);
            pedidoService.adicionar(p);
        }
    }

    private static void rankingSabores() {
        System.out.println("Ranking de sabores vendidos:");
        for (Map.Entry<String,Integer> e : vendasService.ranking().entrySet()) {
            System.out.println(String.format("%s -> %d", e.getKey(), e.getValue()));
        }
    }
}
