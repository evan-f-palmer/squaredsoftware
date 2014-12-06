package com.squaredsoftware.reflection;
import java.lang.reflect.Method;
import java.util.List;

public class MethodsOfSameNameException extends RuntimeException {
		private static final long serialVersionUID = 4872980250974130880L;
		public Class<?> classType;
		public List<Method> methods;
		MethodsOfSameNameException(Class<?> xClassType, List<Method> xMethods) {
			classType = xClassType;
			methods = xMethods; 
		}
		
		public String getMessage() {
			return "There are " + methods.size() + " instances of the method " + methods.get(0).getName() + 
				   " in "+ classType.getCanonicalName() + "\n" +
				   "Use a methodPtr constructor that uses Class<?>[] xParameterTypes to specify which one you want.\n";
		}
	}