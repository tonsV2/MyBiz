package dk.fitfit.mybiz.resources.assemblers;

import com.google.common.collect.Maps;
import dk.fitfit.mybiz.entities.MyEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class AssemblersRegistry implements InitializingBean {

	@Autowired
	private List<AbstractAssembler> assemblers;

//	@Autowired
//	private List<AbstractLinksAssembler> assemblers;

	private Map<Class<? extends MyEntity>, AbstractAssembler> classToAssemblerMap;

	@Override
	public void afterPropertiesSet() throws Exception {
		classToAssemblerMap = Maps.uniqueIndex(assemblers, abstractAssembler -> {
			return abstractAssembler.getSupportedClass();
		});
	}

	public ResourceSupport getAssembledResource(MyEntity entity) {
		final AbstractAssembler abstractAssembler = getAssembler(entity.getClass());
		return abstractAssembler.toResource(entity);
	}

	public AbstractAssembler getAssembler(Class<? extends MyEntity> clazz) {
		return classToAssemblerMap.get(clazz);
	}

//	public Set<Class<? extends MyEntity>> getSupportedClasses() {
//		return classToAssemblerMap.keySet();
//	}
}
