package c8.s8;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstructorDeclarations {

	// 必须是simple name
	// public c8.s8.ConstructorDeclarations() {}
	public static void main(String[] args) {
		new C("123");
		new D<Double, Float>(1, 1l, 1.1, 1.1f);
		/*
		 * The constructor D<Integer,Long>(int, long, double, float) is
		 * undefined 构造上一般不声明泛型类型,声明了也是被类上的覆盖... 比如这里的<A, B>并没有什么卵用,还是<T,
		 * U>,还让人看着绕...
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

