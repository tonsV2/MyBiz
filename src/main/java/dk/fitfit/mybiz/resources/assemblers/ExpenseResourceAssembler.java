package dk.fitfit.mybiz.resources.assemblers;


import dk.fitfit.mybiz.api.ExpenseController;
import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.resources.ExpenseResource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ExpenseResourceAssembler extends AbstractAssembler<ExpenseResource, Expense> {
	public ExpenseResource toResource(final Expense expense) {
		final ExpenseResource.Builder builder = new ExpenseResource.Builder();
		final long id = expense.getId();
		final ExpenseResource resource = builder.from(expense).build();
		//resources.add(linkTo(methodOn(ExpenseController.class).findOne(id)).withSelfRel());
		resource.add(linkTo(ExpenseController.class).slash(id).withSelfRel());
		return resource;
	}

	@Override
	public Class getSupportedClass() {
		return Expense.class;
	}

}
