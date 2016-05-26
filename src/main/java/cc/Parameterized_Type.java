package cc;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Parameterized_Type {

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
		if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
			System.out.println(Arrays.toString(((ParameterizedType) this
					.getClass().getGenericSuperclass())
					.getActualTypeArguments()));
		}
		System.out.println("bridge method:");
		for (Method m : this.getClass().getDeclaredMethods()) {
			System.out.println(m.getName() + "  "
					+ (m.isBridge() ? "is bridge" : "non"));
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
class Neo<Double, String> extends Ceo {
	@Override
	Number getT() {
		return super.getT();
	}
}

