package br.com.rumo.poc.sharedkernel.command;

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
public class CommandStub implements ApplicationContextAware {

	private static Map<Class<?>, Map<Object, List<Method>>> commandList = new HashMap<>();	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		System.out.println("COMMAND RUM ::::");
		for(String beanName : applicationContext.getBeanDefinitionNames()) {
			Object obj = applicationContext.getBean(beanName);
			
			Class<?> objClazz = obj.getClass();
			if(AopUtils.isAopProxy(obj)) {
				objClazz = AopUtils.getTargetClass(obj);
			}
			
			Stream<Method> methodStream = Arrays.asList(objClazz.getDeclaredMethods())
			.stream();
			
			methodStream.filter(m -> m.isAnnotationPresent(CommandHandle.class))
			.filter(m -> m.getParameterTypes() != null)
			.forEach(m -> {
				Class<?> parameterType = m.getParameterTypes()[0];
				Arrays.asList(parameterType.getInterfaces())
				.stream()
				.forEach(i -> {
					if(!i.equals(Command.class)) {
						throw new CommandHandlerException();
					}
				});
				
				if(commandList.get(parameterType) == null) {
					Map<Object, List<Method>> methodsMap = new HashMap<>();
					methodsMap.put(obj, Arrays.asList(m));
					CommandStub.commandList.put(parameterType, methodsMap);
				}else if(commandList.get(parameterType).get(obj) == null){
					CommandStub.commandList.get(parameterType).put(obj, Arrays.asList(m));
				}else {
					CommandStub.commandList.get(parameterType).get(obj).add(m);
				}
			});
			
		}
	}
	
	public static void print() {
		CommandStub.commandList.forEach((k,v) -> System.out.println("KEY : " + k + " VALUE : " + v));
	}
	
	public static <T extends Event>void publisher(T event) {
		
	}
	
	public static <T extends Command>void publisher(T command) {
		System.out.println("COMMAND : " + command.getClass().getSimpleName());
		System.out.println(CommandStub.commandList);
		CommandStub.commandList.get(command.getClass())
		.entrySet()
		.stream()
		.forEach(m -> 
			m.getValue().stream().forEach(methods -> {
				try {
					methods.invoke(m.getKey(), command);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}));
		
	}
}
