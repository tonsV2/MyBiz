package dk.fitfit.mybiz.services;


import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.repositories.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.ToDoubleFunction;

@Service
public class ExpenseService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExpenseRepository repository;

	public Expense save(final Expense expense) {
		log.info("save({})", expense.toString());
		return repository.save(expense);
	}

	public Expense findOne(final long id) {
		log.info("findOne({})", id);
		return repository.findOne(id);
	}

	public Iterable<Expense> findAll() {
		log.info("findAll()");
		return repository.findAll();
	}

	public boolean delete(final long id) {
		log.info("delete({})", id);
		try {
			repository.delete(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	public double totalPrice(final int quarter) {
		log.info("totalPrice({})", quarter);
		final PairOfDates dates = quarter2dates(quarter);
		return totalPrice(dates.getFirst(), dates.getSecond(), Expense::getTotalPrice);
	}

	private PairOfDates quarter2dates(final int quarter) {
		switch (quarter) {
			case 1:
				final LocalDate firstOfJanuar = LocalDate.of(2015, 1, 1);
				final LocalDate lastOfMarch = LocalDate.of(2015, 3, 31);
				return new PairOfDates(firstOfJanuar, lastOfMarch);
			case 2:
				final LocalDate firstOfApril = LocalDate.of(2015, 4, 1);
				final LocalDate lastOfJune = LocalDate.of(2015, 6, 30);
				return new PairOfDates(firstOfApril, lastOfJune);
			case 3:
				final LocalDate firstOfJuly = LocalDate.of(2015, 7, 1);
				final LocalDate lastOfSeptember = LocalDate.of(2015, 9, 30);
				return new PairOfDates(firstOfJuly, lastOfSeptember);
			case 4:
				final LocalDate firstOfOctober = LocalDate.of(2015, 10, 1);
				final LocalDate lastOfDecember = LocalDate.of(2015, 12, 31);
				return new PairOfDates(firstOfOctober, lastOfDecember);
			default:
				throw new IllegalArgumentException();
		}
	}

	private class PairOfDates {
		private final LocalDate first;
		private final LocalDate second;

		public PairOfDates(final LocalDate first, final LocalDate second) {
			this.first = first;
			this.second = second;
		}

		public LocalDate getFirst() {
			return first;
		}

		public LocalDate getSecond() {
			return second;
		}
	}

	public double totalPrice(final LocalDate fromDate, final LocalDate toDate) {
		return totalPrice(fromDate, toDate, Expense::getTotalPrice);
	}

	public double totalPriceIncludingVat(final LocalDate fromDate, final LocalDate toDate) {
		return totalPrice(fromDate, toDate, Expense::getTotalPriceIncludingVat);
	}

	private double totalPrice(final LocalDate fromDate, final LocalDate toDate, final ToDoubleFunction<? super Expense> method) {
		final List<Expense> expenses = repository.findByDateBetween(fromDate, toDate);
		return expenses
				.stream()
				.mapToDouble(method)
				.sum();
	}
}
