package c9.s2;

public class InterfaceMembers {

	public static void main(String[] args) {
		System.out.println(Children.FIELD);
		Parent.staticMethod();
		new Children(){}.defaultMethod();
	}
	
}

interface InfAA {
	
	//默认public abstract
	void method();
	
	Number method1();
}

class ClassAA implements InfAA {

	final public void method() {
		
	}

	public Integer method1() {
		return null;
	}
	
}

interface Parent {

	int FIELD = 0;
	class Clazz {
		
	}
	static void staticMethod() {
		
	}
	default void defaultMethod() {
		
	}
	
}

interface Children extends Parent {

}

class VChildren implements Children {
	
}