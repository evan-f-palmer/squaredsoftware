package com.squaredsoftware.reflection;
/**
 * Currently there is nothing safe about this class.
 * 
 * My hope is that i can create a MethodPtr with type safety.
 * 
 * At the very least this allows it so you do not have to cast the result after calling the method.
 */
public class SafeMethodPtr<ReturnType> extends MethodPtr {
	public SafeMethodPtr(Class<?> xClassType, String xMethodName) {
		super(xClassType, xMethodName);
	}
	
	public SafeMethodPtr(Class<?> xClassType, String xMethodName, Class<?>[] xParameterTypes) {
		super(xClassType, xMethodName, xParameterTypes);
	}
	
	public SafeMethodPtr(Object xObject, String xMethodName) {
		super(xObject, xMethodName);
	}
	
	public SafeMethodPtr(Object xObject, String xMethodName, Class<?>[] xParameterTypes) {
		super(xObject, xMethodName, xParameterTypes);
	}

	@SuppressWarnings("unchecked")
	public ReturnType call(Object... xArgs) {
		return (ReturnType) super.call(xArgs);
	}
	
	public ReturnType invoke(Object... xArgs) {
		return call(xArgs);
	}
}
