package my.system.management.domain.purchase.repository;

import my.system.management.domain.purchase.model.Purchase;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    List<Purchase> findAll(Sort sort);
}
