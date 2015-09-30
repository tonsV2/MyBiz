package dk.fitfit.mybiz.repositories;

import dk.fitfit.mybiz.entities.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
}
