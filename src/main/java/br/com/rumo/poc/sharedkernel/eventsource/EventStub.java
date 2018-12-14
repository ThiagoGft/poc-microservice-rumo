package br.com.rumo.poc.sharedkernel.eventsource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import br.com.rumo.poc.sharedkernel.Event;

@Component
public class EventStub implements ApplicationContextAware {

	private static Map<Class<?>, Map<Object, List<Method>>> eventList = new HashMap<>();	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		for(String beanName : applicationContext.getBeanDefinitionNames()) {
			Object obj = applicationContext.getBean(beanName);
			
			Class<?> objClazz = obj.getClass();
			if(AopUtils.isAopProxy(obj)) {
				objClazz = AopUtils.getTargetClass(obj);
			}
			
			Stream<Method> methodStream = Arrays.asList(objClazz.getDeclaredMethods())
			.stream();
			
			methodStream.filter(m -> m.isAnnotationPresent(EventHandle.class))
			.filter(m -> m.getParameterTypes() != null)
			.forEach(m -> {
				Class<?> parameterType = m.getParameterTypes()[0];
				Arrays.asList(parameterType.getInterfaces())
				.stream()
				.forEach(i -> {
					if(!i.equals(Event.class)) {
						throw new EventHandlerException();
					}
				});
				
				if(eventList.get(parameterType) == null) {
					Map<Object, List<Method>> methodsMap = new HashMap<>();
					methodsMap.put(obj, Arrays.asList(m));
					EventStub.eventList.put(parameterType, methodsMap);
				}else if(eventList.get(parameterType).get(obj) == null){
					EventStub.eventList.get(parameterType).put(obj, Arrays.asList(m));
				}else {
					EventStub.eventList.get(parameterType).get(obj).add(m);
				}
			});
			
		}
	}
	
	public static <T extends Event>void publisher(T event) {
		Map<Object, List<Method>> handlers = EventStub.eventList.get(event.getClass());
		
		if(handlers != null) {
			handlers.entrySet().stream()
			.forEach(m -> 
				m.getValue().stream().forEach(methods -> {
					try {
						methods.invoke(m.getKey(), event);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}));
		}
		
		
		
	}
}
