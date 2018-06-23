package br.edu.ifrs.alvorada.check.service;

import br.edu.ifrs.alvorada.check.config.Messages;
import br.edu.ifrs.alvorada.check.domain.*;
import br.edu.ifrs.alvorada.check.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
@AllArgsConstructor
public class LoanService {


    private final LoanRepository loanRepository;
    private final ItemService itemService;
    private final Messages messages;


    public Iterable<Loan> getLoans(User user) {
        final List<Loan> list = user != null ?
                loanRepository.getLoans(user.getId()) : new ArrayList();
        return list;
    }

    public Long save(User user, Long id, StatusLoan statusLoan) {
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setItem(itemService.getOneById(id));
        loan.setDateTimeLoan(LocalDateTime.now());
        loan.setStatusLoan(statusLoan);
        return loanRepository.save(loan).getId();
    }

    public Loan getOne(Loan loan) {
        if (loan == null || loan.getId() == null)
            return null;
        Optional<Loan> optionalLoan = loanRepository.findById(loan.getId());
        return optionalLoan.isPresent() ? optionalLoan.get() : null;
    }

    public Item checkLoaned(Item item, BindingResult bindingResult, User user) {
        if(item == null)
            return null;
        Optional<Loan> optionalLoan = loanRepository.getLoanByItem(item.getId());
        if (optionalLoan.isPresent()) {
            if (optionalLoan.get().getUser().getId().equals(user.getId())) {
                bindingResult.addError(new FieldError("search", "criteria", messages.get("field.you.alread.loaned")));
            } else {
                bindingResult.addError(new FieldError("search", "criteria", messages.get("field.already.loaned")));
            }
            return null;
        }
        return item;
    }


    //Counters
    public List<?> findTotalLoanedCountersByUser(User user) {
        return loanRepository.findTotalLoanedCountersByUser(user.getId());
    }

    public List<?> findTotalLoansCountersByUser(User user) {
        return loanRepository.findTotalLoansCountersByUser(user.getId());
    }

    public List<?> findTotalLoansCounters() {
        return loanRepository.findTotalLoansCounters();
    }

    public List<?> findTotalLoanedCounters() {
        return loanRepository.findTotalLoanedCounters();
    }


}
