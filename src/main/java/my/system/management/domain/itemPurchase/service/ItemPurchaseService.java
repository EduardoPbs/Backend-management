package my.system.management.domain.itemPurchase.service;

import my.system.management.domain.itemPurchase.repository.ItemPurchaseRepository;
import my.system.management.domain.itemPurchase.model.ItemPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPurchaseService {

    @Autowired
    private ItemPurchaseRepository repository;

    public List<ItemPurchase> findAll() {
        return repository.findAll();
    }

    public Optional<ItemPurchase> findById(String id) {
        return repository.findById(id);
    }

    public ItemPurchase getReferenceById(String id) {
        return repository.getReferenceById(id);
    }

    public ItemPurchase save(ItemPurchase itemPurchase) {
        return repository.save(itemPurchase);
    }

    public List<ItemPurchase> saveAll(List<ItemPurchase> items) {
        return repository.saveAll(items);
    }
}
