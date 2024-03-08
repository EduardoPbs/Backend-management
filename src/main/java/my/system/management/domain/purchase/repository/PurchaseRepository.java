package my.system.management.domain.purchase.repository;

import my.system.management.domain.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
}
