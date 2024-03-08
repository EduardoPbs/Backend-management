package my.system.management.domain.orderItem.service;

import my.system.management.domain.orderItem.model.OrderItem;
import my.system.management.domain.orderItem.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public List<OrderItem> findAll(){
        return repository.findAll();
    }

    public Optional<OrderItem> findById(String id){
        return repository.findById(id);
    }

    public OrderItem getReferenceById(String id){
        return repository.getReferenceById(id);
    }

    public OrderItem save(OrderItem orderItem){
        return repository.save(orderItem);
    }

    public List<OrderItem> saveAll(List<OrderItem> items){
        return repository.saveAll(items);
    }
}
