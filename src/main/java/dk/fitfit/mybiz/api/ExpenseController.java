package dk.fitfit.mybiz.api;

import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ExpenseController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExpenseService service;

	@RequestMapping("/expense")
	public Iterable<Expense> findAll() {
		log.info("findAll()");
		return service.findAll();
	}

	@RequestMapping(value = "/expense", method = RequestMethod.POST)
	public Expense save(@RequestBody Expense expense) {
		log.info("save({})", expense.toString());
		return service.save(expense);
	}

	@RequestMapping("/expense/{id}")
	public ResponseEntity<?> findOne(@PathVariable long id) {
		log.info("findOne({})", id);
		final Expense expense = service.findOne(id);
		if(expense != null) {
			return new ResponseEntity<>(expense, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.PUT)
	public Expense update(@RequestBody Expense expense, @PathVariable long id) {
		log.info("update({})", expense.toString());
		expense.setId(id);
		return service.save(expense);
	}

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id) {
		log.info("delete({})", id);
		if(service.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/expense/quarter/{q}")
	public double totalByQuarter(@PathVariable int q) {
		log.info("totalByQuarter({})", q);
		return service.totalPrice(q);
	}
}
