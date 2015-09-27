package dk.fitfit.mybiz.api;

import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
public class DataInitializerController {
	@Autowired
	private ExpenseService service;

	@RequestMapping("/initialize")
	public List<Expense> initialize() {
		final long id = 1L;
		final String name = "expense name";
		final String description = "expense description";
		final double price = 1200.0;
		final int amount = 2;
		final Date date = new Date();

		final Expense expense = new Expense(id, name, description, price, amount, date);
		service.save(expense);

		final Expense expense2 = new Expense(id + 1, "Other " + name, description, price * 2, amount * 2, date);
		service.save(expense2);

		return service.findAll();
	}

}
