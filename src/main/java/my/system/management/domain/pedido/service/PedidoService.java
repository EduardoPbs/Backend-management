package my.system.management.domain.pedido.service;

import my.system.management.domain.pedido.model.Pedido;
import my.system.management.domain.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> findAll(){
        return repository.findAll();
    }

    public Optional<Pedido> findById(String id){
        return repository.findById(id);
    }

    public Pedido getReferenceById(String id){
        return repository.getReferenceById(id);
    }

    public Pedido save(Pedido pedido){
        return repository.save(pedido);
    }

    public void delete(Pedido pedido){
        repository.delete(pedido);
    }
}
