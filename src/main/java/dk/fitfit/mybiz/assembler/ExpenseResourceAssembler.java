package dk.fitfit.mybiz.assembler;


import dk.fitfit.mybiz.api.ExpenseController;
import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.resource.ExpenseResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ExpenseResourceAssembler {
	public static ExpenseResource from(final Expense expense) {
		final ExpenseResource.Builder builder = new ExpenseResource.Builder();
		final long id = expense.getId();
		final ExpenseResource resource = builder.from(expense).build();
		resource.add(linkTo(methodOn(ExpenseController.class).findOne(id)).withSelfRel());
		resource.add(linkTo(methodOn(ExpenseController.class).delete(id)).withRel("delete"));
		resource.add(linkTo(methodOn(ExpenseController.class).update(expense, id)).withRel("put"));
		resource.add(linkTo(methodOn(ExpenseController.class).findAll()).withRel("list"));
		return resource;
	}
}
