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
		final Expense expense = new Expense();

		// When
		expense.setId(1L);
		expense.setName("expense name");
		expense.setDescription("expense description");
		expense.setPrice(1200.0);
		expense.setAmount(2);

		// Then
		assertThat(expense.getId(), is(1L));
		assertThat(expense.getTotalPrice(), is(3000.0));
	}
}
