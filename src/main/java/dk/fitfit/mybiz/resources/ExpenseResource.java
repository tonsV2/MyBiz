package dk.fitfit.mybiz.resources;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import dk.fitfit.mybiz.entities.Identifiable;
import dk.fitfit.mybiz.entities.utils.JsonResourceSerializer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize(using = JsonResourceSerializer.class)
public class ExpenseResource extends ResourceSupport {
	private final Map<String, Object> properties = new HashMap<>();

	// TODO: Everything below this is boilerplate... it's annoying to add a property!
	// What if this class could return a map and then in the ExpenseResourceAssembler you could use the keys of that properties to pick from the entity?
	// Write a JsonSerializer for AbstractResourceSomething

	public ExpenseResource() {
	}

	public Object addProperty(final String key, final Object value) {
		return properties.put(key, value);
	}

	public Map<String, Object> getProperties() {
		properties.put("links", getLinks());
		return properties;
	}

	public List<String> propertyKeys() {
		return Lists.newArrayList("name",
				"description",
				"price",
				"amount",
				"date",
				"totalPrice",
				"totalPriceIncludingVat");
	}

	public boolean from(final Identifiable expense) {
		// TODO: Does it scale? Does it perform?
		final List<String> keySet = propertyKeys();
		boolean success = true;
		for (final String key : keySet) {
			try {
				final Object value = new PropertyDescriptor(key, expense.getClass()).getReadMethod().invoke(expense);
				addProperty(key, value);
				success = true;
			} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
				success = false;
				System.out.println("No: " + key);
//				e.printStackTrace();
			}
		}
		return success;
	}

	@Override
	public List<Link> getLinks() {
		return super.getLinks();
	}
}
