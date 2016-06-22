package c9.s6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

public class AnnotationTypes {

	// 看不出来有啥用..可能就是像struts2里面注解配置@Actions(@Action @Action)那样用
	@FooContainer({ @Foo, @Foo, @Foo })
	int i;

	@Foo
	@Foo
	@Foo
	int j;

	public static void main(String[] args) {
		System.out.println(Modifier.toString(Override.class.getModifiers()));
		// 接口...没构造方法...
		System.out.println(Arrays.toString(Override.class
				.getDeclaredConstructors()));
		System.out.println(Arrays.toString(SuppressWarnings.class
				.getDeclaredMethods()));

	}

}

@interface Named {
	// default void method() {}
}

@interface Include {

	/*
	 * The return type of a method declared in an annotation type must be one of
	 * the following, or a compile-time error occurs
	 */
	// • A primitive type
	int KICK = 0;

	int id() default 0;

	// • String
	String STR = "!@#";

	String value() default "what";

	// • Class or an invocation of Class (§4.5)
	Class CLAZZ1 = Include.class;

	// invocation of Class....似乎意思就是带泛型的Class
	// http://stackoverflow.com/questions/14852765/what-is-parameterized-invocation-of-class
	// jls7的时候是any parameterized invocation of Class
	// jls8怎么改了,但是还是指向4.5 Parameterized Types这节..
	Class<? extends Object> clazz() default Number.class;

	// • An enum type
	enum E {
		WHAT, HANPPEN
	}

	E e() default E.WHAT;

	// • An annotation type
	@interface Named {

	}

	Named named();

	// • An array type whose component type is one of the preceding types
	// (§10.1)
	E[] ES = new E[10];

	E[] es() default { E.WHAT, E.HANPPEN };

}

@Target(ElementType.FIELD)
@Repeatable(FooContainer.class)
@interface Foo {
}

@Target(ElementType.FIELD)
@interface FooContainer {
	Foo[] value();
}

