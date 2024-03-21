package my.system.management.domain.purchase.service;

import jakarta.persistence.EntityNotFoundException;
import my.system.management.domain.cashRegister.model.CashRegister;
import my.system.management.domain.cashRegister.service.CashRegisterService;
import my.system.management.domain.itemPurchase.dto.DataCreateItemPurchase;
import my.system.management.domain.itemPurchase.model.ItemPurchase;
import my.system.management.domain.itemPurchase.repository.ItemPurchaseRepository;
import my.system.management.domain.product.model.Product;
import my.system.management.domain.product.repository.ProductRepository;
import my.system.management.domain.purchase.dto.DataFinishPurchase;
import my.system.management.domain.purchase.model.Purchase;
import my.system.management.domain.purchase.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ItemPurchaseRepository itemPurchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CashRegisterService cashRegisterService;

    public List<Purchase> findAll(Sort sort) {
        return purchaseRepository.findAll(sort);
    }

    public Optional<Purchase> findById(String id) {
        return purchaseRepository.findById(id);
    }

    public Purchase getReferenceById(String id) {
        return purchaseRepository.getReferenceById(id);
    }

    public Purchase save(Purchase purchase) {
        final Purchase p = new Purchase(purchase.getItems(), purchase.getTotal());
        return purchaseRepository.save(p);
    }

    public void delete(Purchase purchase) {
        purchaseRepository.delete(purchase);
    }

    public void addItemInPurchase(DataFinishPurchase data) {
        Purchase purchaseFound = purchaseRepository.findById(data.purchase_id()).orElseThrow(() -> new EntityNotFoundException());
        List<ItemPurchase> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (DataCreateItemPurchase item : data.dataItems()) {
            Product productFound = productRepository.findById(item.productId()).orElseThrow(() -> new EntityNotFoundException());

            if (item.quantity() <= 0) {
                throw new IllegalArgumentException("Quantidade deve ser pelo menos 1");
            }

            productFound.setStock(
                    productFound.getStock() + item.quantity());

            ItemPurchase newItem = new ItemPurchase(
                    item.quantity(),
                    purchaseFound,
                    productFound
            );

            BigDecimal valorItem = BigDecimal.valueOf(item.quantity()).multiply(productFound.getValue());
            total = total.add(valorItem);
            items.add(newItem);
        }

        purchaseFound.setDate(LocalDateTime.now());
        purchaseFound.setTotal(total);

        cashRegisterService.doPullout(purchaseFound.getTotal());
        itemPurchaseRepository.saveAll(items);
    }
}
