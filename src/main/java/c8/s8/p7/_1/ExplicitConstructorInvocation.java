package c8.s8.p7._1;

public class ExplicitConstructorInvocation {

	public static void main(String[] args) {
		Generic g1 = new<String> Generic();
		Generic g2 = new<String> Generic("123");
		System.out.<Runnable> println();
		// 构造方法跟普通方法一样...
		// 但是这个类型参数一般好像并没有什么卵用..
		// 普通的和声明类型参数的的都能用上..编译完还是被擦除
		// http://stackoverflow.com/questions/22174022/why-does-giving-explicit-type-arguments-to-a-non-generic-method-or-constructor-c
		// JLS §15.12.2.1 - Identify Potentially Applicable Methods
		// JLS §15.9.3 - Choosing the Constructor and its Arguments
	}

	// Let C be the class being instantiated,
	// and let S be the direct superclass of C
	// If S is an inner member class, but S is not a member of a lexically
	// enclosing type declaration of C, then a compile-time error occurs.
	class SA1 {

	}

	//没报错啊??????????
	class CA1 extends SA1 {
		public CA1() {
			super();
		}
	}
}

// ExplicitConstructorInvocation
// 构造方法的显式调用包括俩:
// 一种是用this引用自己的其他构造方法,一种是通过super调用父类的,
// 调用父类的又分为俩:
// 一种是Unqualified superclass constructor invocations,
// 也就是直接通过this调用,可能还会带上泛型声明,
// 另外种是Qualified superclass constructor invocations begin with a Primary
// expression or an ExpressionName,在super()前面会有个前缀

class Outer {

	class Inner {

	}

}

class AA extends Outer.Inner {

	public AA() {
		new Outer().super();
	}
	
	public AA(Outer o) {
		o.super();
	}

}

class Generic {
	public <T> Generic() {
	}

	public <T> Generic(T t) {
	}
}

class GenEx extends Generic {

	public GenEx() {
		<String> super();
	}

}