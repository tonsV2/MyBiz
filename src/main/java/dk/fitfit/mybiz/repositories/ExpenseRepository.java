package dk.fitfit.mybiz.repositories;

import dk.fitfit.mybiz.entities.Expense;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
	List<Expense> findByDateBetween(final LocalDate beginDate, final LocalDate endDate);
}

