package com.squaredsoftware.reflection;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * VERY POWERFUL!  
 * 
 * VERY DANGEROUS!
 * 
 * USE WITH EXTREME CAUTION!
 * 
 * @author Evan
 */
public class MethodPtr {
	private Object object;
	Method method;
	
	/**
	 * Short hand constructor for when method name is not overloaded.
	 * 
	 * @throws MethodsOfSameNameException if the method name is overloaded
	 * @throws MethodDoesNotExistException if the method name does not exist in the object
	 * @param xClassType the class that contains the method
	 * @param xMethodName the name of the method to call
	 */
	public MethodPtr(Class<?> xClassType, String xMethodName) {
		LinkedList<Method> methods = new LinkedList<Method>();
		for(Method m : xClassType.getDeclaredMethods()) 
			if(m.getName().equalsIgnoreCase(xMethodName)) 
				methods.add(m);
		for(Method m : xClassType.getMethods()) 
			if(m.getName().equalsIgnoreCase(xMethodName)) 
				methods.add(m);
		
		if(methods.size() > 1) 
			throw new MethodsOfSameNameException(xClassType, methods);
		else if(methods.size() == 0) 
			throw new MethodDoesNotExistException(xClassType, xMethodName);
		
		method = methods.getFirst();
	}
	
	/**
	 * Specifies the specific method to call even when the function is overloaded.
	 * 
	 * @param xClassType the class that contains the method
	 * @param xMethodName the name of the method to call
	 * @param xParameterTypes the parameters that the method takes in
	 */
	public MethodPtr(Class<?> xClassType, String xMethodName, Class<?>[] xParameterTypes) {
		try {
			method = xClassType.getMethod(xMethodName, xParameterTypes);
		} catch (NoSuchMethodException e) {
			throw new MethodDoesNotExistException(xClassType, xMethodName);
		}
	}
	
	/**
	 * Short hand constructor for when method name is not overloaded.
	 * 
	 * @throws MethodsOfSameNameException if the method name is overloaded
	 * @throws MethodDoesNotExistException if the method name does not exist in the object
	 * @param xObject the object to be manipulated
	 * @param xMethodName the name of the method to call
	 */
	public MethodPtr(Object xObject, String xMethodName) {
		this(xObject.getClass(), xMethodName);
		object = xObject;
	}
	
	/**
	 * Specifies the specific method to call even when the function is overloaded.
	 *
	 * @param xObject the object to be manipulated
	 * @param xMethodName the name of the method to call
	 * @param xParameterTypes the parameters that the method takes in
	 */
	public MethodPtr(Object xObject, String xMethodName, Class<?>[] xParameterTypes) {
		this(xObject.getClass(), xMethodName, xParameterTypes);
		object = xObject;
	}

	/**
	 * Call the method
	 * 
	 * @throw RuntimeException if the call throws an exception or is passed in illegal arguments 
	 * @param xArgs the arguments to pass into the method
	 * @return the return of the call
	 */
	public Object call(Object... xArgs) {
		try {
			return method.invoke(object, xArgs);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) { 
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Call the method
	 * 
	 * @throw RuntimeException if the call throws an exception or is passed in illegal arguments 
	 * @param xArgs the arguments to pass into the method
	 * @return the return of the call
	 */
	public Object invoke(Object... args) {
		return call(args);
	}
}
