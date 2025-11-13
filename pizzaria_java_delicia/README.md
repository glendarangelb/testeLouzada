# Pizzaria Java Delícia - Projeto Prático (Opção B)

Estrutura do projeto:
- src/model -> classes de domínio (Pedido)
- src/service -> serviços que usam coleções Java (Set, List, Queue, Stack, PriorityQueue, Map)
- src/app -> classe Main com menu interativo

Como compilar e executar (linha de comando):

```bash
# estando na pasta pizzaria_java_delicia
javac -d out src/model/*.java src/service/*.java src/app/*.java
java -cp out app.Main
```

Dicas:
- O menu é simples e interativo via console.
- O sistema registra vendas por sabor no momento de adicionar o pedido (vendasService).
- A fila de entregas usa LinkedList (Queue). A fila prioritária usa PriorityQueue baseada em tempo de preparo.
- Para abrir no Visual Studio Code, importe a pasta como um projeto Java (instale extensão Java se necessário).
