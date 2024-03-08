package my.system.management.configuration;

import my.system.management.domain.cashRegister.model.CashRegister;
import my.system.management.domain.cashRegister.repository.CashRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CashRegisterRepository cashRegisterRepository;

    @Override
    public void run(String... args) throws Exception {
        if (cashRegisterRepository.count() == 0) {
            final CashRegister cashRegister = new CashRegister(BigDecimal.ZERO);
            cashRegisterRepository.save(cashRegister);
        }
    }
}
