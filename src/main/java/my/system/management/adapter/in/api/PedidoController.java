package my.system.management.adapter.in.api;

import jakarta.validation.Valid;
import my.system.management.domain.itensPedido.dto.DadosCadastroItemPedido;
import my.system.management.domain.itensPedido.model.ItemPedido;
import my.system.management.domain.itensPedido.service.ItemPedidoService;
import my.system.management.domain.pedido.dto.DadosDetalhesPedido;
import my.system.management.domain.pedido.dto.DadosFinalizarPedido;
import my.system.management.domain.pedido.model.Pedido;
import my.system.management.domain.pedido.service.PedidoService;
import my.system.management.domain.produto.model.Produto;
import my.system.management.domain.produto.service.ProdutoService;
import my.system.management.infra.exception.PedidoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping
    @Transactional
    public ResponseEntity novoPedido(UriComponentsBuilder uriBuilder){
        final Pedido pedido = pedidoService.save(
                new Pedido(
                        UUID.randomUUID().toString(),
                        new ArrayList<>(),
                        BigDecimal.ZERO
                ));
        final URI location = uriBuilder.path("pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(pedido.getId());
    }

    @PostMapping("/add-items")
    @Transactional
    public ResponseEntity addItem(@RequestBody @Valid DadosFinalizarPedido dados, UriComponentsBuilder uriBuilder){
        Pedido pedidoRecuperado = pedidoService.findById(dados.pedido_id()).orElseThrow(() -> new PedidoNotFoundException());
        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(DadosCadastroItemPedido dado : dados.dataItems()){
            Produto produtoRecuperado = produtoService.findById(dado.produtoId());

            ItemPedido novoItem = new ItemPedido(
                    dado.quantidade(),
                    pedidoRecuperado,
                    produtoRecuperado
            );

            BigDecimal valorItem = BigDecimal.valueOf(dado.quantidade()).multiply(produtoRecuperado.getValor());
            total = total.add(valorItem);
            itens.add(novoItem);
        }

        pedidoRecuperado.setData(LocalDateTime.now());
        pedidoRecuperado.setTotal(total);
        System.out.println("\n\n\nITENS:" + itens + "\n\n\n");

        itemPedidoService.saveAll(itens);

        return ResponseEntity.status(HttpStatus.OK).build();
   }

    @GetMapping
    public ResponseEntity<List<DadosDetalhesPedido>> getAllPedidos(){
        final List<Pedido> pedidos = pedidoService.findAll();
        List<DadosDetalhesPedido> dadosPedidos = new ArrayList<>();

        for (Pedido p : pedidos) {
            dadosPedidos.add(new DadosDetalhesPedido(p));
        }

        return ResponseEntity.status(HttpStatus.OK).body(dadosPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable("id") String id){
//        System.out.println(pedidoService.getReferenceById(id).getItens());
        final Pedido pedido = pedidoService.getReferenceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removePedido(@PathVariable("id") String id){
        final Pedido pedidoRecuperado = pedidoService.getReferenceById(id);
        pedidoService.delete(pedidoRecuperado);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
