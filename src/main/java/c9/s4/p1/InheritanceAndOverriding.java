package c9.s4.p1;

import javax.naming.LinkException;

public class InheritanceAndOverriding {
	public static void main(String[] args) throws Exception {
		new ClassB().method();
	}
}

interface InfA {
	default void method() throws ReflectiveOperationException {
		System.out.println("InfA");
	}
	
}

interface InfB {
	default void method() throws LinkException {
		System.out.println("InfB");
	}
}

interface InfC {
	void method();
}

interface InfD {
	void method();
}
/*
 * Duplicate default methods named method with the parameters () and () are
 * inherited from the types InfB and InfA
 */
// class ClassA implements InfA, InfB {}

class ClassB implements InfA,InfC {
	// 重写default方法跟一般的差不多..
	@Override
	public void method() {
		System.out.println("ClassB");
	}
}

//这样的话似乎是相当于只实现了第一个接口的method方法,规范说这样不太好,他们还在研究啥的...
class ClassC implements InfD, InfC {

	@Override
	public void method() {
		
	}
	
}
