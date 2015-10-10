package dk.fitfit.mybiz.resources;

import com.google.common.collect.Lists;
import org.springframework.hateoas.Link;

import java.util.List;

// TODO: Set access rights here?
public class ExpenseResource extends PowerResource {
	public ExpenseResource() {
		add(new Link("snot", "snog"));
	}

	@Override
	public List<String> propertyKeys() {
		return Lists.newArrayList("name",
				"description",
				"price",
				"amount",
				"date",
				"totalPrice",
				"totalPriceIncludingVat");
	}
}
