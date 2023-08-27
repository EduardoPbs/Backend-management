package my.system.management.domain.itensPedido.service;

import my.system.management.domain.itensPedido.model.ItemPedido;
import my.system.management.domain.itensPedido.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    public List<ItemPedido> findAll(){
        return repository.findAll();
    }

    public Optional<ItemPedido> findById(String id){
        return repository.findById(id);
    }

    public ItemPedido getReferenceById(String id){
        return repository.getReferenceById(id);
    }

    public ItemPedido save(ItemPedido itemPedido){
        return repository.save(itemPedido);
    }

    public List<ItemPedido> saveAll(List<ItemPedido> items){
        return repository.saveAll(items);
    }
}
