package c12.s4.p1;

public class WhenInitializationOccurs {

	public static void main(String[] args) {
		//调用constant不会初始化接口里其他东西
		System.out.println(J.i);
		//这样调用的不是直接的常亮就会初始化别的值
//		System.out.println(J.ii);
		System.out.println(K.j);
		
		System.out.println("======================");
		System.out.println(KK.i);
	}

}

class Test {
	//这样syso下就知道接口初始化了,貌似断点也行
	static int out(String s, int i) {
		System.out.println(s + "=" + i);
		return i;
	}
}

interface I {
	int i = 1, ii = Test.out("ii", 2), iii = Test.out("iii", 3);
}

interface J extends I {
	int j = Test.out("j", 3), jj = Test.out("jj", 4);
}

interface K extends J {
	int k = Test.out("k", 5);
}

class KK implements K {
	static int i = 0;
}

interface Strange {
	//断点不能打在接口里..但是用","分割的常亮前面就能打..这是为啥
	int i = 9;
	int ii = Test.out("ii", 2);
	int j = 0, jj = 1;
}

interface A<T> {
	default T method() {
		System.out.println("A<T>");
		return null;
	}
}
interface B extends A<String> {
	int i = Test.out("coming!", 111);
	
}
class BB implements B {
	static int i = 0;
}