package c8.s1.p3;

public class InnerClassesAndEnclosingInstances {

	// An inner class is a nested class that is not
	// explicitly or implicitly declared static
	// 平常说的静态内部类并不是真正的内部类
	// 英文一个nested class一个inner class...好像都差不多的样子
	private static int j = 0;
	public static void main(String[] args) {
		System.out.println(Inner0.class.getDeclaredConstructors()[0]);
		System.out.println(NInner0.class.getDeclaredConstructors()[0]);
		new InnerClassesAndEnclosingInstances.NInner0();
	}
	
	/**
	 * 真.内部类包括 non-static member class
	 * 
	 * 非静态成员类 local class @see LocalClassDeclarations
	 * 
	 * anonymous class 匿名 @see AnonymousClassDeclarations
	 */
	class Inner0 {

	}

	private int i = 0;

	// 带static的不算,接口是隐式的static的也不算...
	static class NInner0 {
		static int i = j;
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
		// anonymous class Direct Inner Class 1?
		new Inf0() {
			// member class of a anonymous class
			class DInnerC2 {
			}
		};
	}
}

// 书上老说[immediately] enclosing type declaration 和 [direct] inner class...
// 具体啥意思感觉也还不是很明白,大致是这么个感觉
// Outt是他自己的zeroth lexically enclosing type declaration
class Outt {
	// Outt是Inne的immediately enclosing type declaration,
	// 并且Inne不是出现在一个static context中,那Inne就是Outt的direct inner class
	class Inne {
		// 对Inne来说,他自己是他自己的第0个enclosing type declaration
		// 而Outt就是他的第0个immediately enclosing type declaration
		// 也就是他的第1个lexically enclosing type declaration
	}

}

//跟上面的类似,Outtt的实例是他自己zeroth lexically enclosing instance
class Outtt {
	//对Innne来说,Outtt的实例就是他的第0个immediately enclosing instance
	//也就是他的1'th lexically enclosing instance
	class Innne {
		// Innne的实例i跟Outtt是有关系的,就称Outtt是i的immediately enclosing instance
		/*
		 * The immediately enclosing instance of an object, if any, is
		 * determined when the object is created(§15.9.2)
		 */
		//这个意思大致就是在创建Innne实例时候,需要确定Outtt已经创建了,
		//类似就是new Outtt().new Innne()这样个意思
	}
}
