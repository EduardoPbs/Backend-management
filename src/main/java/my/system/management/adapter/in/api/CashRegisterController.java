package my.system.management.adapter.in.api;

import jakarta.transaction.Transactional;
import my.system.management.domain.cashRegister.dto.DetailsCashRegisterDto;
import my.system.management.domain.cashRegister.dto.EntranceCashRegisterDto;
import my.system.management.domain.cashRegister.dto.PulloutCashRegisterDto;
import my.system.management.domain.cashRegister.model.CashRegister;
import my.system.management.domain.cashRegister.service.CashRegisterService;
import my.system.management.domain.purchase.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("cash-register")
public class CashRegisterController {

    @Autowired
    private CashRegisterService cashRegisterService;

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> balance() {
        final BigDecimal totalBalance = cashRegisterService.getCashRegister().getTotal();
        return ResponseEntity.status(HttpStatus.OK).body(totalBalance);
    }

    @GetMapping("/all-activities")
    public ResponseEntity<List<DetailsCashRegisterDto>> activities(
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        final List<DetailsCashRegisterDto> registerDtos = cashRegisterService.findAll(sort);
        return ResponseEntity.status(HttpStatus.OK).body(registerDtos);
    }

    @PostMapping("/entrance")
    @Transactional
    public ResponseEntity<Boolean> entrance(@RequestBody EntranceCashRegisterDto data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cashRegisterService.doEntrance(data.value()));
    }

    @PostMapping("/pullout")
    @Transactional
    public ResponseEntity<Boolean> pullout(@RequestBody PulloutCashRegisterDto data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cashRegisterService.doPullout(data.value()));
    }

    @GetMapping("/report/{month}")
    public ResponseEntity<List<Purchase>> report(@PathVariable("month") String month) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cashRegisterService.report(Integer.parseInt(month)));
    }
}
