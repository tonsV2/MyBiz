package dk.fitfit.mybiz.entities;

import dk.fitfit.mybiz.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExpenseTest {
	@Test
	public void testExpense() {
		// Given
		final long id = 1L;
		final String name = "expense name";
		final String description = "expense description";
		final double price = 1200.0;
		final int amount = 2;

		final Expense expense = new Expense();

		// When
		expense.setId(id);
		expense.setName(name);
		expense.setDescription(description);
		expense.setPrice(price);
		expense.setAmount(amount);

		// Then
		assertThat(expense.getId(), is(1L));
		assertThat(expense.getName(), is(name));
		assertThat(expense.getDescription(), is(description));
		assertThat(expense.getPrice(), is(price));
		assertThat(expense.getAmount(), is(2));
		assertThat(expense.getTotalPrice(), is(3000.0));
	}
}
