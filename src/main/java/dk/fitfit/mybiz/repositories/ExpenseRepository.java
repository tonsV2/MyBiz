package dk.fitfit.mybiz.repositories;

import dk.fitfit.mybiz.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
