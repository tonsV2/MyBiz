package dk.fitfit.mybiz.api;

import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.resources.ExpenseResource;
import dk.fitfit.mybiz.resources.assemblers.AssemblersRegistry;
import dk.fitfit.mybiz.resources.assemblers.ExpenseResourceAssembler;
import dk.fitfit.mybiz.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ExpenseController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExpenseService service;

	@Autowired
	AssemblersRegistry assemblersRegistry;

	@RequestMapping("/v2/expenses/{id}")
	public ResponseEntity<?> findOnev2(@PathVariable long id) throws Exception {
		log.info("findOnev2({})", id);
		assemblersRegistry.afterPropertiesSet();
		final Expense expense = service.findOne(id);
//		final AbstractAssembler assembler = assemblersRegistry.getAssembler(expense.getClass());
//		final ResourceSupport resource = assemblers.toResource(expense);
		final ResourceSupport resource = assemblersRegistry.getAssembledResource(expense);
		if(resource != null) {
			System.out.println(resource.getClass());
			return new ResponseEntity<>(expense, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/expenses/{id}")
	public ResponseEntity<?> findOne(@PathVariable long id) {
		log.info("findOne({})", id);
		final Expense expense = service.findOne(id);
		if(expense != null) {
			final ExpenseResourceAssembler resourceAssembler = new ExpenseResourceAssembler();
			final ExpenseResource resource = resourceAssembler.toResource(expense);
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/expenses")
	public Iterable<Expense> findAll() {
		log.info("findAll()");
		return service.findAll();
	}

	@RequestMapping(value = "/expenses", method = RequestMethod.POST)
	public Expense save(@RequestBody Expense expense) {
		log.info("save({})", expense.toString());
		return service.save(expense);
	}

	@RequestMapping(value = "/expenses/{id}", method = RequestMethod.PUT)
	public Expense update(@RequestBody Expense expense, @PathVariable long id) {
		log.info("update({})", expense.toString());
		expense.setId(id);
		return service.save(expense);
	}

	@RequestMapping(value = "/expenses/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable long id) {
		log.info("delete({})", id);
		if(service.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/expenses/quarter/{q}")
	public double totalByQuarter(@PathVariable int q) {
		log.info("totalByQuarter({})", q);
		return service.totalPrice(q);
	}

	@RequestMapping(value = "/expense/year/{year}/quarter/{quarter}")
	public double totalByQuarter(@PathVariable int year, @PathVariable int quarter) {
		log.info("totalByQuarter({}, {})", year, quarter);
		return service.totalPrice(year, quarter);
	}
}
