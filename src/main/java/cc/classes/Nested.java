package cc.classes;


/**
 * An inner class is a nested class that is not explicitly or implicitly
 * declared static. 原来静态的不算....
 * 
 * @author John Smith
 *
 */
public interface Nested {
	
	public static void main(String[] args) {
		new Nested.Nested1();
		new Nested.Nested2.Nested1();
	}

	/**
	 * A member class of an interface is implicitly static (§9.5) so is never
	 * considered to be an inner class
	 *
	 */
	class Nested1 {
		// no problem
		static {

		}
		static {

		}
	}

	//内部接口是隐式的static的
	interface Nested2 {

		class Nested1 {
			public native void method();
		}

	}

}

class Outer {

	class Inner1 {
		// Cannot define static initializer in inner type Outer.Inner
		// static {
		//
		// }

		// The method method cannot be declared static; static methods can only
		// be declared in a static or top level type
		// static void method() {
		//
		// }
	}

	static class Inner2 {
		static {

		}

		static void method() {

		}
	}

	class Inner3 {
		/*
		 * The field o(i) cannot be declared static in a non-static inner type,
		 * unless initialized with a constant expression
		 */
		// static Object o;
		// static int i;
	}
	
}
