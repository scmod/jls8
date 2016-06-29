package c8.s8;


public class ConstructorDeclarations {

	// 必须是simple name
	// public c8.s8.ConstructorDeclarations() {}
	public static void main(String[] args) {
		new C("123");
		new D<Double, Float>(1, 1l, 1.1, 1.1f);
		/*
		 * The constructor D<Integer,Long>(int, long, double, float) is
		 * undefined 构造上好像一般不声明泛型类型... 比如这里的<A, B>并没有什么卵用, 还是以class上的声明<T,
		 * U>为准,还让人看着绕,只是可以用来多传几个别的类型的参数...
		 */
		// new D<Integer, Long>(1, 1l, 1.1, 1.1f);
	}

	public ConstructorDeclarations(String str) {
	}

}

class C extends ConstructorDeclarations {

	/*
	 * Constructor declarations are not members. They are never inherited and
	 * therefore are not subject to hiding or overriding.
	 * 但是如果父类没有默认的无参构造,那子类也必须要实现一个构造方法并且调用父类构造
	 */
	public C(String str) {
		super(str);
	}

	/*
	 * Implicit super constructor ConstructorDeclarations() is undefined. Must
	 * explicitly invoke another constructor
	 */
	public C(String str, int i) {
		this(str);// 这样或者super显式的调用就好了
	}

}

class D<T, U> {
	T t;
	U u;

	// 构造方法一般不用加上泛型声明
	public <A, B> D(A a, B b, T t, U u) {
		//
	}

}

class Outer {
	class Inner {
	}
}

// 只有Outer实例化了,A才能调用到父类Inner的构造方法
class QA extends Outer.Inner {
	QA() {
		// 默认的话调用super()类似于调用new Inner().constructor()
		// 这样就类似于变成new Outer().new Inner().constructor()..
		new Outer().super();
	}
}

class QB extends Outer.Inner {
	// 如果要对Outer里面什么东西设置下,大概就用这样实例化完弄好Outer再传进来
	QB(Outer ref) {
		ref.super();
	}
}
