package cc.method;

import java.rmi.AccessException;
import java.util.List;

import javax.naming.LinkException;

public class InheritanceOverridingHiding {

	public static void main(String[] args) throws Exception {
		Parent.method();
		Child.method();
		// Foo.staticMethod();//并没有- -
		new Foo().defaultMethod();// default方法是实例方法
		Inf1.staticMethod();
		
//		System.out.println(D.class.getDeclaredMethod("id", Object.class));
	}

}

class Parent {

	static void method() {
		System.out.println("parent");
	}

	Number number() {
		return null;
	}

}

class Child extends Parent {
	// No method declared in C has a signature that is a subsignature (§8.4.2)
	// of the signature of m.
	// 这样就算重写~不算继承父类number方法
	@Override
	Integer number() {
		return 1;
	}

	// This instance method cannot override the static method from Parent
	// 从父类继承了同名static方法,静态方法不能被重写
	// void method() {
	//
	// }

	// The method method() of type Child must override or implement a supertype
	// method
	// 这个也不算重写啊..不能加@Override,重写这个说法是针对的实例方法
	// 8.4.8.1 Overriding (by Instance Methods)
	// @Override
	static void method() {
		System.out.println("child");
	}
}

interface Inf1 {

	static void staticMethod() {

	}

	default void defaultMethod() {

	}
	
	void method(List<Integer> list);

}

interface Inf2 {

	static void staticMethod() {

	}

	// default void defaultMethod() {
	//
	// }

	void method(List<String> list);

}

// default方法是不能同名的,static因为并不会被继承所以都没关系,
// 抽象同名方法带泛型的话就呵呵了...
//
class Foo implements Inf1, Inf2 {

	// 这个就没事,因为并没有从接口中继承静态方法
	void staticMethod() {

	}

	@Override
	public void method(List list) {

	}

}

class C<T> {
	T id(T x) {
		return null;
	}
}

interface I<T> {
	T id(T x);
}

//这个报错说是说C跟I的id方法擦除类型相同(has the same erasure),
//但是实际上这俩的泛型在运行时是可以拿到的,并没有被擦除
//感觉是不是不好生成桥接方法什么的缘故...sun偷懒...
//class D extends C<String> implements I<Integer> {
//	public String id(String x) {
//		return null;
//	}
//
//	public Integer id(Integer x) {
//		return null;
//	}
//}

interface A1{
	A1 method() throws LinkException;
}
interface A2 extends A1{
	A2 method() throws NullPointerException;
}
interface A3 extends A2{
	A3 method() throws BootstrapMethodError/*, AccessException //加上这个非运行时异常就不行了~这样才对*/;
}
interface A4 extends A3{
	A4 method() throws IncompatibleClassChangeError;
}
class Ax implements A1,A2,A3,A4 {

	//这真是....而且也不符合重写方法抛出异常要是父类异常的子类的原则啊,
	//但是抛出的是异常得父类异常又报错,不同类的又可以..
	//哦..原来还得加上抛的是非运行时异常..(检查异常,Error什么的不算)
	@Override
	public A4 method() {
		return null;
	}

}
