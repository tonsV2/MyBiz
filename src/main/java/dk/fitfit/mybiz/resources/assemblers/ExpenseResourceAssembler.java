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
	public ExpenseResource toResource(final Expense expense) {
		final ExpenseResource resource = new ExpenseResource();
		resource.from(expense);
		addLinks(resource, expense);
		return resource;
	}

	private void addLinks(final ExpenseResource resource, final Expense expense) {
		// TODO: A links assembler should be implemented
		final Link link = linkTo(methodOn(ExpenseController.class).findOne(expense.getId())).withSelfRel();
		resource.add(link);
	}

	@Override
	public Class getSupportedClass() {
		return Expense.class;
	}

}
