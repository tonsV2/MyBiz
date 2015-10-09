package dk.fitfit.mybiz.assembler;


import dk.fitfit.mybiz.api.ExpenseController;
import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.resource.ExpenseResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ExpenseResourceAssembler {
	// TODO: don't depend on Expense
	public static ExpenseResource from(final Expense expense) {
		final ExpenseResource.Builder builder = new ExpenseResource.Builder();
		final long id = expense.getId();
		final ExpenseResource resource = builder.from(expense).build();
		resource.add(linkTo(methodOn(ExpenseController.class).findOne(id)).withSelfRel());
		return resource;
	}
}
