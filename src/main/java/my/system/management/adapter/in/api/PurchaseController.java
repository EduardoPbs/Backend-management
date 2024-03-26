package my.system.management.adapter.in.api;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import my.system.management.domain.purchase.dto.DataDetailsPurchase;
import my.system.management.domain.purchase.dto.DataFinishPurchase;
import my.system.management.domain.purchase.model.Purchase;
import my.system.management.domain.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Purchase>> findAll(
            @RequestParam(required = false, defaultValue = "date") String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        final List<Purchase> purchaseList = purchaseService.findAll(sort);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsPurchase> find(@PathVariable("id") String id) {
        final Purchase purchaseFound = purchaseService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataDetailsPurchase(purchaseFound));
    }

    @PostMapping
    public ResponseEntity<String> save() {
        final Purchase purchaseCreated = purchaseService.save(new Purchase(
                new ArrayList<>(),
                BigDecimal.ZERO
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseCreated.getId());
    }

    @PostMapping("/add-items")
    public ResponseEntity addItem(@RequestBody @Valid DataFinishPurchase data) {
        purchaseService.addItemInPurchase(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/finish/{id}")
    @Transactional
    public ResponseEntity completeStatus(@PathVariable("id") String id) {
        final Purchase purchase = purchaseService.findById(id);
        purchase.completeStatus();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/pending/{id}")
    @Transactional
    public ResponseEntity pendingStatus(@PathVariable("id") String id) {
        final Purchase purchase = purchaseService.findById(id);
        purchase.pendingStatus();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        final Purchase purchaseFound = purchaseService.getReferenceById(id);
        purchaseService.delete(purchaseFound);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
