package br.edu.ifrs.alvorada.check.repository;

import br.edu.ifrs.alvorada.check.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query(value = "SELECT * FROM LOAN WHERE DATE_TIME_RETURN IS NULL AND STATUS_LOAN = 1 AND USER_ID = (?1);", nativeQuery = true)
    List<Loan> getLoans(Long userId);

    @Query(value = "SELECT * FROM LOAN WHERE DATE_TIME_RETURN IS NULL AND STATUS_LOAN = 1 AND ITEM_ID = (?1) LIMIT 1;", nativeQuery = true)
    Optional<Loan> getLoanByItem(Long itemId);

    @Query(value="SELECT  COUNT(id) FROM Loan WHERE user_id = (?1);",nativeQuery = true)
    List<Object[]> findTotalLoansCountersByUser(Long userId);

    @Query(value="SELECT  COUNT(id) FROM Loan WHERE user_id = (?1) AND date_time_return is null;",nativeQuery = true)
    List<Object[]> findTotalLoanedCountersByUser(Long userId);

    @Query(value="SELECT  COUNT(id) FROM Loan WHERE date_time_return is null;",nativeQuery = true)
    List<Object[]> findTotalLoanedCounters();


    @Query(value="SELECT  COUNT(id) FROM Loan;",nativeQuery = true)
    List<Object[]> findTotalLoansCounters();

    @Query(value="SELECT COUNT(id) FROM LOAN WHERE date_time_loan BETWEEN CURRENT_DATE() - (?1) AND CURRENT_DATE();",nativeQuery = true)
    List<Object[]> findLoansCountersByLastDay(int days);

}