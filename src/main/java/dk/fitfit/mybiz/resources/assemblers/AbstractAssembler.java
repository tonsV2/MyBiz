package dk.fitfit.mybiz.resources.assemblers;

import dk.fitfit.mybiz.entities.Identifiable;
import org.springframework.hateoas.ResourceSupport;

public abstract class AbstractAssembler<T extends ResourceSupport, R extends Identifiable> {
	public abstract Class<T> getSupportedClass();
	public abstract T toResource(R entity);
//	public abstract R fromResource(T resource);
}
