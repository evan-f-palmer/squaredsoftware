package com.squaredsoftware.reflection;
public class MethodDoesNotExistException extends RuntimeException { 
	private static final long serialVersionUID = 5875599057700310050L;
	public Class<?> classType;
	public String methodName;
	MethodDoesNotExistException(Class<?> xClassType, String xMethodName) {
		classType = xClassType;
		methodName = xMethodName;
	}
	
	public String getMessage() {
		return "Method " + methodName + " does not exist in class " + classType.getCanonicalName() + "\n";
	}
}