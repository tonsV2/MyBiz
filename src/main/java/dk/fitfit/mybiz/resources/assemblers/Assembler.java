package dk.fitfit.mybiz.resources.assemblers;

import com.google.common.collect.Maps;
import dk.fitfit.mybiz.entities.Identifiable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class Assembler implements InitializingBean {

	// TODO: Does it scale? Does it perform?
	@Autowired
	private List<AbstractAssembler> assemblers;

//	@Autowired
//	private List<AbstractLinksAssembler> assemblers;

	private Map<Class<? extends Identifiable>, AbstractAssembler> classToAssemblerMap;

	@Override
	public void afterPropertiesSet() throws Exception {
		classToAssemblerMap = Maps.uniqueIndex(assemblers, abstractAssembler -> {
			return abstractAssembler.getSupportedClass();
		});
	}

	public ResourceSupport getAssembledResource(Identifiable entity) {
		final AbstractAssembler abstractAssembler = getAssembler(entity.getClass());
		return abstractAssembler.toResource(entity);
	}

	public AbstractAssembler getAssembler(Class<? extends Identifiable> clazz) {
		return classToAssemblerMap.get(clazz);
	}

//	public Set<Class<? extends Identifiable>> getSupportedClasses() {
//		return classToAssemblerMap.keySet();
//	}
}
