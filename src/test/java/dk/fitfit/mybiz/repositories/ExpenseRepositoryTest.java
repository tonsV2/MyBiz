package dk.fitfit.mybiz.repositories;

import dk.fitfit.mybiz.Application;
import dk.fitfit.mybiz.entities.Expense;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExpenseRepositoryTest {
	@Autowired
	private ExpenseRepository repository;

	@Before
	public void setUp() {
		final Expense expense = new Expense();
		repository.save(expense);
	}

	@Test
	public void save() {
		// Given
		final String name = "expense name";
		final String description = "expense description";
		final double price = 1200.0;
		final int amount = 2;

		final Expense expense = new Expense();
		expense.setName(name);
		expense.setDescription(description);
		expense.setPrice(price);
		expense.setAmount(amount);

		// When
		final Expense savedExpense = repository.save(expense);

		// Then
		assertThat(savedExpense.getId(), is(notNullValue()));
		assertThat(savedExpense.getName(), is(name));
		assertThat(savedExpense.getDescription(), is(description));
		assertThat(savedExpense.getPrice(), is(price));
	}

	@Test
	public void findOne() {
		// Given
		final long id = 1L;

		// When
		final Expense expense = repository.findOne(id);

		// Then
		assertThat(expense, notNullValue());
		assertThat(expense.getId(), notNullValue());
	}

	@Test
	public void findAll() {
		fail();
	}

	@Test
	public void delete() {
		fail();
	}

}
