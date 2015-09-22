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

	@RequestMapping(value = "/expense", method = RequestMethod.POST)
	public Expense save(@RequestBody Expense expense) {
		return service.save(expense);
	}

	@RequestMapping("/expense/{id}")
	public ResponseEntity<?> findOne(@PathVariable long id) {
		final Expense one = service.findOne(id);
		if(one != null) {
			return new ResponseEntity<>(one, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/expense")
	public List<Expense> findAll() {
		return service.findAll();
	}
}
