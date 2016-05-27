package cc;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ParameterizedType {

	// 没有界限的通配符
	// Collection<?> c和Collection<Object> c的区别
	static void printCollection(Collection<?> c) {
		for (Object o : c) {
			System.out.println(o);
		}
	}

	// 有界限的,似乎一般也用不到啊....
	static <T> void add(T element, List<? super T> list) {
		list.add(element);
	}

	// 额..
	static <T extends U, U> void add(T element, U u, List<U> list) {
		list.add(element);
		list.add(u);
	}

	public static void main(String[] args) {
		// Collection<Object> cs = new ArrayList<String>(); //不行..
		// Collection<?> cs = new ArrayList<String>(); //?比较随意,然而什么都别想往里面加了..
		// Collection<?> cs = new ArrayList<>(); //7的语法糖,同上
		// Collection<String> cs = new ArrayList<>(); //....正常....
		Collection<String> cs = new ArrayList<String>(); // 还是老实点..
		cs.add("hello");
		cs.add("world");
		printCollection(cs);


		new Foo();
		// 编译期没在类上声明的都被擦除
		new Foo<List, ArrayList>();
		// 编译期在类上声明的
		new Bar();
		new Ceo();
		new Duo();
		new Rio();
		new Neo();
		
	}

}

class Foo<T, U extends T> {

	T t;
	U u;

	public Foo() {
		System.out.println(this.getClass());
		System.out.println("typeParameters : ");
		for (TypeVariable tv : this.getClass().getTypeParameters()) {
			System.out.println(tv.getGenericDeclaration()
					+ Arrays.toString(tv.getBounds()));
		}
		System.out.println("genericType : ");
		if (this.getClass().getGenericSuperclass() instanceof java.lang.reflect.ParameterizedType) {
			System.out.println(Arrays
					.toString(((java.lang.reflect.ParameterizedType) this
							.getClass().getGenericSuperclass())
							.getActualTypeArguments()));
		}
		System.out.println("bridge method:");
		for (Method m : this.getClass().getDeclaredMethods()) {
			System.out.println(m.getName() + "  "
					+ (m.isBridge() ? "is bridge" : "non")
					+ (m.isSynthetic() ? "  is synthetic" : "non"));
			if (m.isBridge()) {
				System.out.println(m);
			}
		}
		System.out.println("===============================================");
	}

	T getT() {
		return t;
	}
}

class Bar extends Foo<String, String> {
}

class Ceo extends Foo<Number, Integer> {
	@Override
	Number getT() {
		return super.getT();
	}
}

class Duo<T, U extends T> extends Foo<Object, Number> {

}

class Rio<T, Y extends T> extends Foo<T, Y> {

}

// 这个声明出来也是被忽略的,并没有什么卵用
// 而且这个声明会产生误解,这个声明的只是个名字不是类,声明成Number跟T一个效果,结果就是这个类里面Number和String类都不能用了
class Neo<Number, String> extends Ceo {

	// Erasure of method getT(Number) is the same as another method in type
	// Neo<Number,String>
	// 这是因为相当于Neo<T, U> extends Ceo,T会被当做一个Object,然后就跟下面的方法冲突了
	Number getT(Number s) {
		return null;
	}

	// Object getT(Object o) {
	// return null;
	// }
}
