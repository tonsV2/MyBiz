package dk.fitfit.mybiz.resources.assemblers;


import dk.fitfit.mybiz.api.ExpenseController;
import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.resources.ExpenseResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ExpenseResourceAssembler extends AbstractAssembler<ExpenseResource, Expense> {
	@Override
	public ExpenseResource toResource(final Expense expense) {
		final ExpenseResource resource = new ExpenseResource();
		resource.from(expense);

		// TODO: add links here or on the resource itself
		final Link link = linkTo(methodOn(ExpenseController.class).findOne(expense.getId())).withSelfRel();
		resource.add(link);

		return resource;
	}

	@Override
	public Class getSupportedClass() {
		return Expense.class;
	}

}
