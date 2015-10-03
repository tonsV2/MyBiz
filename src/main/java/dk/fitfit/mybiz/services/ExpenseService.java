package dk.fitfit.mybiz.services;


import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.ToDoubleFunction;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository repository;

	public Expense save(final Expense expense) {
		return repository.save(expense);
	}

	public Expense findOne(final long id) {
		return repository.findOne(id);
	}

	public Iterable<Expense> findAll() {
		return repository.findAll();
	}

	public boolean delete(final long id) {
		try {
			repository.delete(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	double totalPrice(final LocalDate fromDate, final LocalDate toDate) {
		return totalPrice(fromDate, toDate, Expense::getTotalPrice);
	}

	double totalPriceIncludingVat(final LocalDate fromDate, final LocalDate toDate) {
		return totalPrice(fromDate, toDate, Expense::getTotalPriceIncludingVat);
	}

	double totalPrice(final LocalDate fromDate, final LocalDate toDate, final ToDoubleFunction<? super Expense> method) {
		final List<Expense> expenses = repository.findByDateBetween(fromDate, toDate);
		return expenses
				.stream()
				.mapToDouble(method)
				.sum();
	}
}
