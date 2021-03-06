package br.edu.ifrs.alvorada.check.service;

import br.edu.ifrs.alvorada.check.config.Messages;
import br.edu.ifrs.alvorada.check.domain.*;
import br.edu.ifrs.alvorada.check.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class LoanService {


    private final LoanRepository loanRepository;
    private final ItemService itemService;
    private final Messages messages;


    public Iterable<Loan> getLoans(User user) {
        final List<Loan> list = user != null ?
                loanRepository.getLoansByUser(user.getId()) : new ArrayList();
        return list;
    }

    public Iterable<Loan> getAllLoans() {
        final List<Loan> list = loanRepository.getAllLoans();
        return list == null || list.isEmpty()?  new ArrayList() : list ;
    }

    public Iterable<Loan> getAllLoansAndReturns() {
        final List<Loan> list = loanRepository.findAll();
        return list == null || list.isEmpty()?  new ArrayList() : list ;
    }

    public Long save(User user, Long id, StatusLoan statusLoan) {
        Loan loan = new Loan();

        loan.setItem(itemService.getOneById(id));
        loan.setUser(user);
        loan.setDateTimeLoan(LocalDateTime.now());
        loan.setStatusLoan(statusLoan);
        return loanRepository.save(loan).getId();
    }

    public Long saveReturn(User user, Long loanId, StatusLoan statusLoan) {

        if (loanId == null)
            return null;

        Loan loan = loanRepository.getOne(loanId);
        loan.setUserReturn(user);
        loan.setDateTimeReturn(LocalDateTime.now());
        loan.setStatusLoan(statusLoan);
        return loanRepository.save(loan).getId();
    }


    // TODO RNG003, RNG004 Cassiano
    public Item checkLoaned(Item item, BindingResult bindingResult, User user) {
        if (item == null)
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

    // TODO RNG002 Cassiano
    public Loan checkReturn(Item item, BindingResult bindingResult, User user, boolean isAdmin) {
        if (item == null)
            return null;
        Optional<Loan> optionalLoan = loanRepository.getLoanByItem(item.getId());
        if (optionalLoan.isPresent()) {
            if (optionalLoan.get().getUser().getId().equals(user.getId())) {
                return optionalLoan.get();
            }
            if (isAdmin)
                return optionalLoan.get();
            else {
                bindingResult.addError(new FieldError("search", "criteria", messages.get("field.no.permission.return")));
                return null;
            }

        } else {

            bindingResult.addError(new FieldError("search", "criteria", messages.get("field.item.not.loaned")));

            return null;
        }

    }

    public boolean isAdmin(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
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


    public List<?> findLoansByDays(int days) {
        return loanRepository.findLoansCountersByLastDay(days);
    }

}
