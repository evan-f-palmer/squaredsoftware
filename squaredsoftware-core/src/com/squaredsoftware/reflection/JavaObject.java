package com.squaredsoftware.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class JavaObject {
	private Object object;
	private HashMap<String, LinkedList<Method> > methods;
	private HashMap<String, Field>  fields;
	
	public String className;
	
	public List<String> publicMethods;
	public List<String> protectedMethods;
	public List<String> privateMethods;
	
	public List<String> publicFields;
	public List<String> protectedFields;
	public List<String> privateFields;

	public class NoSuchFieldException       extends RuntimeException{ private static final long serialVersionUID = 6238759081143290351L;}
	public class FieldIsNotPublicException  extends RuntimeException{ private static final long serialVersionUID = -7454480483989578407L;}
	public class MethodIsNotPublicException extends RuntimeException{ private static final long serialVersionUID = 3338113639563448933L;}
	
	public JavaObject(Object object) {
		this.object = object;
		className   = object.getClass().getCanonicalName();
		
		methods          = new HashMap<String, LinkedList<Method> >();
		fields           = new HashMap<String, Field>();
		
		publicMethods    = new LinkedList<String>();
		protectedMethods = new LinkedList<String>();
		privateMethods   = new LinkedList<String>();
		
		publicFields     = new LinkedList<String>();
		protectedFields  = new LinkedList<String>();
		privateFields    = new LinkedList<String>();
		
		sortMethods();
		sortFields();
	}
	
	public Object get(String xField) {
		if(!fields.containsKey(xField)) throw new NoSuchFieldException();
		Object value = null;
		try {
			value = fields.get(xField).get(object);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);}
		  catch (IllegalAccessException e)   {throw new FieldIsNotPublicException();}
		return value;
	}
	
	public void set(String xField, Object xNewValue) {
		if(!fields.containsKey(xField)) throw new NoSuchFieldException();
		try {
			fields.get(xField).set(object, xNewValue);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);}
		  catch (IllegalAccessException e)   {throw new FieldIsNotPublicException();}
	}
	
	public Object call(String xMethodName, Object... xArgs) throws InvocationTargetException {
		if(!methods.containsKey(xMethodName)) throw new MethodDoesNotExistException(object.getClass(), xMethodName);
		Object returnValue = null;
		try {
			returnValue = findSignitureMatch(xMethodName, xArgs).invoke(object, xArgs);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);}
		  catch (IllegalAccessException e)   {throw new MethodIsNotPublicException();} 
		return returnValue;
	}
	
	public Object getIgnoreAccess(String xField) {
		if(!fields.containsKey(xField)) throw new NoSuchFieldException();
		fields.get(xField).setAccessible(true);
		Object value = null;
		try {
			value = fields.get(xField).get(object);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);}
		  catch (IllegalAccessException e)   {throw new RuntimeException(e);}
		fields.get(xField).setAccessible(false);
		return value;
	}
	
	public void setIgnoreAccess(String xField, Object xNewValue) {
		if(!fields.containsKey(xField)) throw new NoSuchFieldException();
		fields.get(xField).setAccessible(true);
		try {
			fields.get(xField).set(object, xNewValue);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);}
		  catch (IllegalAccessException e)   {throw new RuntimeException(e);}
		fields.get(xField).setAccessible(false);	
	}
	
	public Object callIgnoreAccess(String xMethodName, Object... xArgs) throws InvocationTargetException {
		if(!methods.containsKey(xMethodName)) throw new MethodDoesNotExistException(object.getClass(), xMethodName);
		Object returnValue = null;
		Method method = findSignitureMatch(xMethodName, xArgs);
		method.setAccessible(true);
		try {
			method.invoke(object, xArgs);
		} catch (IllegalArgumentException e) {throw new RuntimeException(e);}
		  catch (IllegalAccessException e)   {throw new RuntimeException(e);} 
		method.setAccessible(false);
		return returnValue;
	}
	
	public String toString() {
		return object.toString();
	}

	public void printPublicFieldsNames() {
		System.out.print("Public fields: [");
		for(String field : publicFields)
			System.out.print(field + " ");
		System.out.println("]");
	}
	
	public void printProtectedFieldsNames() {
		System.out.print("Protected fields: [");
		for(String field : protectedFields)
			System.out.print(field + " ");
		System.out.println("]");
	}
	
	public void printPrivateFieldsNames() {
		System.out.print("Private fields: [");
		for(String field : privateFields)
			System.out.print(field + " ");
		System.out.println("]");
	}
	
	public void printPublicMethodsNames() {
		System.out.print("Public methods: [");
		for(String field : publicMethods)
			System.out.print(field + " ");
		System.out.println("]");
	}
	
	public void printProtectedMethodsNames() {
		System.out.print("Protected methods: [");
		for(String field : protectedMethods)
			System.out.print(field + " ");
		System.out.println("]");
	}
	
	public void printPrivateMethodsNames() {
		System.out.print("Private methods: [");
		for(String field : privateMethods)
			System.out.print(field + " ");
		System.out.println("]");
	}
	
	public void printPublicFieldsValues() {
		System.out.println("Public fields: [");
		for(String field : publicFields)
			System.out.println(field + ": " + get(field));
		System.out.println("]");
	}
	
	public void printProtectedFieldsValues() {
		System.out.println("Protected fields: [");
		for(String field : protectedFields)
			System.out.println(field + ": " + getIgnoreAccess(field));
		System.out.println("]");
	}
	
	public void printPrivateFieldsValues() {
		System.out.println("Private fields: [");
		for(String field : privateFields)
			System.out.println(field + ": " + getIgnoreAccess(field));
		System.out.println("]");
	}
	
	private void sortMethods() {
		System.out.println("sort");
		for(Method method : object.getClass().getDeclaredMethods()) {
			sortMethod(method);
		}
		for(Method method : object.getClass().getMethods()) {
			sortMethod(method);
		}
	}
	
	private void sortMethod(Method xMethod) {
		if(!methods.containsKey(xMethod.getName())) {
			methods.put(xMethod.getName(), new LinkedList<Method>());
		}
		methods.get(xMethod.getName()).add(xMethod);

		if ( Modifier.isPublic( xMethod.getModifiers()) ) {
			publicMethods.add(xMethod.getName());
		} else if(Modifier.isProtected(xMethod.getModifiers())) {
			protectedMethods.add(xMethod.getName());
		} else {
			privateMethods.add(xMethod.getName());
		}
	}
	
	private void sortFields() {
		for(Field field : object.getClass().getDeclaredFields()) {
			fields.put(field.getName(), field);
			if(Modifier.isPublic(field.getModifiers())) {
				publicFields.add(field.getName());
			} else if(Modifier.isProtected(field.getModifiers())) {
				protectedFields.add(field.getName());
			} else if(Modifier.isPrivate(field.getModifiers())){
				privateFields.add(field.getName());
			}
		}
	}
	
	private Method findSignitureMatch(String xMethodName, Object[] xSigniture) {
		Method method = null;
		LinkedList<Method> methodList = methods.get(xMethodName);
		Class<?>[] classSigniture = new Class<?>[xSigniture.length];
		for(int i = 0; i < xSigniture.length; ++i) {
			classSigniture[i] = xSigniture[i].getClass();
		}
		for(Method m : methodList) {
			boolean equal = true;
			for(Class<?> c : m.getParameterTypes()) {
				if(!c.isPrimitive()) {
					boolean exist = false;
					for(Class<?> c2 : classSigniture) 
						if(c2.equals(c)) 
							exist = true;
					if(!exist) {
						equal = false;
						break;
					}
				}
			}
			if(equal) {
				method = m;
				break;
			}
		}
		
		if(method == null) throw new MethodDoesNotExistException(object.getClass(), xMethodName);
		
		return method;
	}
}