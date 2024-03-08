package my.system.management.domain.itemPurchase.repository;

import my.system.management.domain.itemPurchase.model.ItemPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPurchaseRepository extends JpaRepository<ItemPurchase, String> {

}
