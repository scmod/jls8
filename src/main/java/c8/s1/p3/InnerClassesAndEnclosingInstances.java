package c8.s1.p3;

public class InnerClassesAndEnclosingInstances {

	// An inner class is a nested class that is not
	// explicitly or implicitly declared static
	// 平常说的静态内部类并不是真正的内部类
	// 英文一个nested class一个inner class...好像都差不多的样子
	/**
	 * 真.内部类包括 non-static member class, 非静态成员类 local class @see
	 * LocalClassDeclarations anonymous class 匿名 @see AnonymousClassDeclarations
	 */
	class Inner0 {

	}

	private int i = 0;

	// 带static的不算,接口是隐式的static的也不算...
	static class NInner0 {

	}

	interface NInf0 {
		// 接口里面的也是隐式的static的
		class NInner1 {
			// Cannot make a static reference to the non-static field i
			// 这样就能看出来了~
			// private int j = i;
		}
	}

	class Inner1 {
		// Cannot define static initializer in inner type
		// InnerClassesAndEnclosingInstances.Inner1
		// 内部类是不能定义static initializer的
		// static {
		//
		// }

		// 同样也不能定义任何显式或隐式static的成员,除非他是个常亮
		// static int i = 0;
		// static class A1 {
		//
		// }
		// static void method() {
		//
		// }
		static final int j = 0;
	}

	// 尽管不能定义,但是可以继承
	class Inner2 extends Inner2Ex {
		void method() {
			System.out.println(Inner2.k);
			Inner2.StaticMethod();
			System.out.println(Inner2.StaticClass.class);
		}
	}

}

class Inner2Ex {
	static int k = 0;

	static void StaticMethod() {

	}

	static class StaticClass {
	}
}

// A class C is an inner class of class or interface O if it is either a direct
// inner class of O or an inner class of an inner class of O.
// 啥是一个类的direct inner class呢...
// 8开始多出来个or interface O,因为有了default方法,在这里面定义的就也变成了direct inner class
interface Inf0 {
	
}

interface OInf0 {
	default void method() {
		//anonymous class Direct Inner Class 1?
		new Inf0() {
			//member class of a anonymous class
			class DInnerC2 {
			}
		};
		class DInnerC3 {
		}
	}
}