package dk.fitfit.mybiz.resources;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public abstract class PowerResource extends ResourceSupport {
	// TODO: can it be optimized? Does it scale?
	private final Map<String, Object> properties = new HashMap<>();

	public Object addProperty(final String key, final Object value) {
		return properties.put(key, value);
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public boolean from(final Identifiable entity) {
		// TODO: set properties.size if < keySet.length?
		final List<String> keySet = propertyKeys();
		boolean success = false;
		for (final String key : keySet) {
			try {
				// TODO: Does it scale? Does it perform? Can it be optimized?
				final Object value = new PropertyDescriptor(key, entity.getClass()).getReadMethod().invoke(entity);
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

	abstract List<String> propertyKeys();

	@Override
	public void add(Link link) {
		super.add(link);
		addProperty("links", getLinks());
	}

	@Override
	public void add(final Link... links) {
		for (Link link : links) {
			add(link);
		}
	}
}
