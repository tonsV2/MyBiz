package dk.fitfit.mybiz.resources.assemblers;

import dk.fitfit.mybiz.entities.MyEntity;
import org.springframework.hateoas.ResourceSupport;

public abstract class AbstractAssembler<T extends ResourceSupport, R extends MyEntity> {
	public abstract Class<T> getSupportedClass();
	public abstract T toResource(R entity);
}
