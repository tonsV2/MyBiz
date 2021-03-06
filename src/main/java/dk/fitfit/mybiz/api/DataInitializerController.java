package dk.fitfit.mybiz.api;

import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;


@Controller
public class DataInitializerController {
	@Autowired
	private ExpenseService service;

	@RequestMapping("/initialize")
	public String initialize() {
		final long id = 1L;
		final String name = "expense name";
		final String description = "expense description";
		final double price = 1200.0;
		final int amount = 2;
		final LocalDate date = LocalDate.now();

		final Expense expense = new Expense(id, name, description, price, amount, date);
		service.save(expense);

		final Expense expense2 = new Expense(id + 1, "Other " + name, description, price * 2, amount * 2, date);
		service.save(expense2);

		return "redirect:#/expenses";
	}

}
