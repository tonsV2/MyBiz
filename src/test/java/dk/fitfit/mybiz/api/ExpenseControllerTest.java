package dk.fitfit.mybiz.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import dk.fitfit.mybiz.Application;
import dk.fitfit.mybiz.entities.Expense;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExpenseControllerTest {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private ExpenseController controller;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	public void testNotFoundExpense() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/expense/666").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void postExpense() throws Exception {
		final Expense expense = createExpense();
		final String json = objectMapper.writeValueAsString(expense);

		mvc.perform(post("/api/expense").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(json)));
	}

	@Test
	public void getExpense() throws Exception {
		// Given
		final Expense expense = createExpense();
		final String json = objectMapper.writeValueAsString(expense);

		mvc.perform(MockMvcRequestBuilders.get("/api/expense/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(json)));
	}

	@Test
	public void updateExpense() throws Exception {
		// Given
		final Expense expense = createExpense();
		expense.setName("Updated " + expense.getName());
		final String json = objectMapper.writeValueAsString(expense);

		mvc.perform(MockMvcRequestBuilders.put("/api/expense").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(json)));

//		mvc.perform(MockMvcRequestBuilders.get("/api/expense/1").accept(MediaType.APPLICATION_JSON))
////				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo(json)));
	}

	@Test
	public void getExpenses() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/expense").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
// TODO: Assert content
				.andExpect(content().string(equalTo("[]")));
	}

	@Test
	public void deleteExpense() throws Exception {
		postExpense();
		mvc.perform(MockMvcRequestBuilders.delete("/api/expense/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private Expense createExpense() {
		final long id = 1L;
		final String name = "expense name";
		final String description = "expense description";
		final double price = 1200.0;
		final int amount = 2;
		final Date date = new Date();

		return new Expense(id, name, description, price, amount, date);
	}

}
