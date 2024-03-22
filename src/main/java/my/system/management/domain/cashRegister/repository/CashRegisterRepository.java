package my.system.management.domain.cashRegister.repository;

import my.system.management.domain.cashRegister.model.CashRegister;
import my.system.management.domain.purchase.model.Purchase;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CashRegisterRepository extends JpaRepository<CashRegister, String> {

    @Query("SELECT p FROM Purchase p WHERE MONTH(p.date) = :month")
    Optional<List<Purchase>> getPurchaseByDate(@Param("month") int month);

    Optional<CashRegister> findTopByOrderByCreatedAtDesc();

    List<CashRegister> findAll(Sort sort);
}
