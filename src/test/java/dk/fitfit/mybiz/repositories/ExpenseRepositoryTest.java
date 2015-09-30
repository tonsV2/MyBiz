package dk.fitfit.mybiz.repositories;

import dk.fitfit.mybiz.Application;
import dk.fitfit.mybiz.entities.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExpenseRepositoryTest {
	@Autowired
	private ExpenseRepository repository;

	@Test
	public void testExpenseRepositorySave() {
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
		assertThat(expense.getPrice(), is(price));
		assertThat(expense.getTotalPrice(), is(3000.0));
	}
}
