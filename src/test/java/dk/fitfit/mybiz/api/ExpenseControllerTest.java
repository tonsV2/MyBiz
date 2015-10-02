package dk.fitfit.mybiz.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import dk.fitfit.mybiz.Application;
import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.services.ExpenseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExpenseControllerTest {

	@Mock
	private ExpenseService service;

	@InjectMocks
	private ExpenseController controller;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testNotFoundExpense() throws Exception {
		// Given
		when(service.findOne(isA(Long.class))).thenReturn(null);

		// When
		final ResultActions result = mvc.perform(get("/api/expense/666").
				accept(MediaType.APPLICATION_JSON));

		// Then
		result.andExpect(status().isNotFound());
		verify(service).findOne(anyLong());
	}

	@Test
	public void postExpense() throws Exception {
		// Given
		final Expense expense = new Expense();

		// When
		when(service.save(isA(Expense.class))).thenReturn(expense);
		final ResultActions result = mvc.perform(post("/api/expense")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(expense)));

		// Then
		result.andExpect(status().isOk())
				.andExpect(content().string(toJson(expense)));
		verify(service).save(isA(Expense.class));
	}

	@Test
	public void getExpense() throws Exception {
		// Given
		final Expense expense = new Expense();

		// When
		when(service.findOne(isA(Long.class))).thenReturn(expense);
		final ResultActions result = mvc.perform(get("/api/expense/1")
				.accept(MediaType.APPLICATION_JSON));

		// Then
		result.andExpect(status().isOk())
				.andExpect(content().string(toJson(expense)));
		verify(service).findOne(anyLong());
	}

	@Test
	public void updateExpense() throws Exception {
		// Given
		final long id = 1L;
		final Expense inputExpense = new Expense();
		inputExpense.setId(id);

		final Expense outputExpense = new Expense();
		outputExpense.setId(id);
		outputExpense.setName("Updated " + inputExpense.getName());

		// When
		when(service.save(inputExpense)).thenReturn(outputExpense);

		final ResultActions result = mvc.perform(put("/api/expense")
				.content(toJson(inputExpense))
				.accept(MediaType.APPLICATION_JSON));

		// Then
//		result.andExpect(status().isOk())
		result.andExpect(content().string(toJson(outputExpense)));
		verify(service).save(isA(Expense.class));
	}

	@Test
	public void getExpenses() throws Exception {
		// Given
		final Expense expense1 = new Expense();
		final Expense expense2 = new Expense();
		final ArrayList<Expense> expenses = Lists.newArrayList(expense1, expense2);

		when(service.findAll()).thenReturn(expenses);

		// When
		final ResultActions result = mvc.perform(get("/api/expense").accept(MediaType.APPLICATION_JSON));

		// Then
		result.andExpect(status().isOk())
				.andExpect(content().string(toJson(expenses)));
		verify(service).findAll();
	}

	@Test
	public void deleteExpense() throws Exception {
		// Given
		final long id = 1L;
		when(service.delete(isA(Long.class))).thenReturn(true);

		// When
		final ResultActions result = mvc.perform(delete("/api/expense/1")
				.accept(MediaType.APPLICATION_JSON));

		// Then
		result.andExpect(status().isOk());
		verify(service).delete(anyLong());
	}

	private String toJson(final Object object) {
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
