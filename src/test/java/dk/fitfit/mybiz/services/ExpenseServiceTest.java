package dk.fitfit.mybiz.services;

import com.google.common.collect.Lists;
import dk.fitfit.mybiz.Application;
import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.repositories.ExpenseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExpenseServiceTest {

	@Mock
	private ExpenseRepository repository;

	@InjectMocks
	private ExpenseService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void sumTotalPrice() {
		// Given
		final Expense expense1 = new Expense();
		expense1.setPrice(400);

		final Expense expense2 = new Expense();
		expense2.setPrice(400);
		expense2.setAmount(2);

		final ArrayList<Expense> expenses = Lists.newArrayList(expense1, expense2);

		when(repository.findByDateBetween(isA(LocalDate.class), isA(LocalDate.class))).thenReturn(expenses);

		// Then
		final LocalDate toDate = LocalDate.now();
		final LocalDate fromDate = toDate.minusMonths(1);
		final double totalPrice = service.totalPrice(fromDate, toDate);

		// When
		assertThat(totalPrice, is(1200.0));
	}

	@Test
	public void save() {
		// Given
		final Expense expense = new Expense();

		// When
		when(repository.save(isA(Expense.class))).thenReturn(expense);

		// Then
		assertThat(service.save(expense), is(expense));
	}

	@Test
	public void findOne() {
		// Given
		final long id = 1L;
		final Expense expense = new Expense();

		// When
		when(repository.findOne(id)).thenReturn(expense);

		// Then
		assertThat(service.findOne(id), is(expense));
	}

	@Test
	public void findAll() {
		// Given
		final Expense expense1 = new Expense();
		final Expense expense2 = new Expense();
		final ArrayList<Expense> expenses = Lists.newArrayList(expense1, expense2);

		// When
		when(repository.findAll()).thenReturn(expenses);

		// Then
		assertThat(service.findAll(), hasItems(expense1, expense2));
	}

// TODO: Expect and then mock the repository to throw the same exception... bad idea?
	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteNonExisting() {
		// Given
		final long id = 1L;

		// When
		doThrow(EmptyResultDataAccessException.class).when(repository).delete(isA(Long.class));

		// Then
		assertThat(service.delete(id), is(false));
	}

	@Test
	public void delete() {
		// Given
		final long id = 1L;

		// When
		doNothing().when(repository).delete(id);

		// Then
		assertThat(service.delete(id), is(true));
	}

//// TODO: this is an integration test and shouldn't be here!
//// Integration tests should also be used to test performance

//	@Autowired
//	private ExpenseService service;

//	@Test
//	public void testExpenseService() {
//		// Given
//		final String name = "expense name";
//		final String description = "expense description";
//		final double price = 1200.0;
//		final int amount = 2;
//		final LocalDate date = LocalDate.now();
//
//		final Expense expense = new Expense();
//		expense.setName(name);
//		expense.setDescription(description);
//		expense.setPrice(price);
//		expense.setAmount(amount);
//		expense.setDate(date);
//
//		// When
//		final Expense savedExpense = service.save(expense);
//
//		// Then
//		assertThat(savedExpense.getId(), is(notNullValue()));
//		assertThat(savedExpense.getName(), is(name));
//		assertThat(savedExpense.getDescription(), is(description));
//		assertThat(savedExpense.getPrice(), is(price));
//		assertThat(savedExpense.getTotalPrice(), is(3000.0));
//		assertThat(savedExpense.getDate(), is(date));
//	}
}
