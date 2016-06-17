package c8.s8.p7._1;


public class ExplicitConstructorInvocation {

}

//ExplicitConstructorInvocation
//构造方法的显式调用包括俩:
//一种是用this引用自己的其他构造方法,一种是通过super调用父类的,
//调用父类的又分为俩:
//一种是Unqualified superclass constructor invocations,
//也就是直接通过this调用,可能还会带上泛型声明,
//另外种是Qualified superclass constructor invocations begin with a Primary
//expression or an ExpressionName,在super()前面会有个前缀

class Outer {
	
	class Inner {
		
	}
	
}

class AA extends Outer.Inner {
	
	public AA() {
		new Outer().super();
	}
	
}