package dk.fitfit.mybiz.api;

import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ExpenseController {
	@Autowired
	private ExpenseService service;

	@RequestMapping("/expense")
	public List<Expense> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/expense", method = RequestMethod.POST)
	public Expense save(@RequestBody Expense expense) {
		return service.save(expense);
	}

	@RequestMapping("/expense/{id}")
	public ResponseEntity<?> findOne(@PathVariable long id) {
		final Expense expense = service.findOne(id);
		if(expense != null) {
			return new ResponseEntity<>(expense, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/expense", method = RequestMethod.PUT)
	public Expense update(@RequestBody Expense expense) {
		return service.save(expense);
	}

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {
		service.delete(id);
	}
}
