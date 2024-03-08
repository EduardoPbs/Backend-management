package my.system.management.adapter.in.api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import my.system.management.domain.itemPurchase.dto.DataCreateItemPurchase;
import my.system.management.domain.itemPurchase.model.ItemPurchase;
import my.system.management.domain.itemPurchase.service.ItemPurchaseService;
import my.system.management.domain.order.model.Order;
import my.system.management.domain.product.model.Product;
import my.system.management.domain.product.service.ProductService;
import my.system.management.domain.purchase.dto.DataCreatePurchase;
import my.system.management.domain.purchase.dto.DataFinishPurchase;
import my.system.management.domain.purchase.model.Purchase;
import my.system.management.domain.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemPurchaseService itemPurchaseService;

    @GetMapping
    public ResponseEntity<List<Purchase>> findAll() {
        final List<Purchase> purchaseList = Collections.unmodifiableList(purchaseService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(purchaseList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> save() {
        final Purchase purchaseCreated = purchaseService.save(new Purchase(
                new ArrayList<>(),
                BigDecimal.ZERO
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseCreated.getId());
    }

    @PostMapping("/add-items")
    @Transactional
    public ResponseEntity addItem(@RequestBody @Valid DataFinishPurchase data) {
        purchaseService.addItemInPurchase(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") String id) {
        final Purchase purchaseFound = purchaseService.getReferenceById(id);
        purchaseService.delete(purchaseFound);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
