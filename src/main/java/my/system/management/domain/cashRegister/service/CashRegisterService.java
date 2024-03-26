package my.system.management.domain.cashRegister.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import my.system.management.domain.cashRegister.dto.DetailsCashRegisterDto;
import my.system.management.domain.cashRegister.model.CashRegister;
import my.system.management.domain.cashRegister.repository.CashRegisterRepository;
import my.system.management.domain.purchase.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CashRegisterService {

    @Autowired
    private CashRegisterRepository cashRegisterRepository;

    public List<DetailsCashRegisterDto> findAll(Sort sort) {
        final List<DetailsCashRegisterDto> registers = cashRegisterRepository
                .findAll(sort)
                .stream()
                .map(DetailsCashRegisterDto::new)
                .toList();
        return registers;
    }

    @Transactional
    public Boolean doEntrance(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Valor inválido. Deve ser maior que 0.");
        }
        final CashRegister cashRegister = getCashRegister();
        final CashRegister newEntranceOp = new CashRegister(cashRegister.getTotal());
        newEntranceOp.entrance(value);
        cashRegisterRepository.save(newEntranceOp);
        return true;
    }

    @Transactional
    public Boolean doPullout(BigDecimal value) {
        final CashRegister cashRegister = getCashRegister();
        if (value.compareTo(cashRegister.getTotal()) > 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Saldo em caixa insuficiente ou inválido.");
        }
        final CashRegister newPulloutOp = new CashRegister(cashRegister.getTotal());
        newPulloutOp.pullout(value);
        cashRegisterRepository.save(newPulloutOp);
        return true;
    }

    public List<Purchase> report(int date) {
        return cashRegisterRepository.getPurchaseByDate(date)
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível gerar o relatório."));
    }

    public CashRegister getCashRegister() {
        return cashRegisterRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new EntityNotFoundException("Erro ao recuperar os dados do caixa."));
    }
}
