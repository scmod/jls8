package c8.s9;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EnumTypes {

	public static void main(String[] args) {
		System.out.println(Modifier.isFinal(E1.class.getModifiers()));
		System.out.println(Modifier.isFinal(E2.class.getModifiers()));
		System.out.println(Modifier.isFinal(E3.class.getModifiers()));
		System.out.println(E3.CLASSBODY.method());
		// 会有个默认的String,int参数的构造方法.如果还有别的自定义参数就加在这俩后面
		System.out.println(Arrays.toString(Color.class
				.getDeclaredConstructors()));
		// RED就类似像一个Color RED = new Color("RED",0)这样的
		System.out.println(Color.RED.getClass());
		new Colorx();
	}

	class Inner {
		// A nested enum type is implicitly static
		// 内部类不能声明static的东西,所以...
		// enum E1 {}
	}
}

enum E1 {

}

enum E2 {
	// 枚举值默认都是隐式public static final的
	@Deprecated
	ELEMENT1
}

enum E3 {
	// 相当于一个匿名内部类
	CLASSBODY {
		int i = 9;

		// 一般的方法是别想在外部调用了
		public String method() {
			System.out.println(this.getClass());
			System.out.println(this.getDeclaringClass());
			System.out.println(i + "666");
			return "method";
		}

		// Cannot override the final method from Enum
		// 还有隐式的valueOf和values方法都是不能重写的
		// public boolean equals(Object o) {}

		// 只有通过重写父类可以重写的方法来调用了..比如toString...
		public String toString() {
			method();
			return super.toString();
		}

	};
	
	//或者这里加上要让外部调用的方法
	public abstract String method();
}

enum Color {
	// 枚举变量总是最先按顺序初始化
	// 变成类似public static final Color RED = new Color("RED", ordinal);
	// 所以在构造方法里调用colorMap会发现colorMap是个null..,只能这样在static块里初始化
	RED, GREEN, BLUE;
	static final Map<String, Color> colorMap = new HashMap<String, Color>();
	static {
		for (Color c : Color.values())
			colorMap.put(c.toString(), c);
	}

	private Color() {
		System.out.println("!?");
	}
}

// 变成类似这样的东西
// 枚举值都在最前面,所以在初始化时候会先调用枚举值得构造方法,然后调用后面的static{}
class Colorx {

	public static final Colorx RED = new Colorx("RED", 0);
	public static final Colorx GREEN = new Colorx("RED", 1);
	public static final Colorx BLUE = new Colorx("RED", 2);

	private String str;
	private int i;

	public Colorx() {
		System.out.println("ctor");
	}

	public Colorx(String str, int i) {
		this.str = str;
		this.i = i;
	}

	static final Map<String, Colorx> colorMap = new HashMap<String, Colorx>();
	static {
		colorMap.put("STR", null);
	}

}

//书上的例子...好溜!书上还有扑克牌例子...
enum Operation {
	PLUS {
		double eval(double x, double y) {
			return x + y;
		}
	},
	MINUS {
		double eval(double x, double y) {
			return x - y;
		}
	},
	TIMES {
		double eval(double x, double y) {
			return x * y;
		}
	},
	DIVIDED_BY {
		double eval(double x, double y) {
			return x / y;
		}
	};
	// Each constant supports an arithmetic operation
	abstract double eval(double x, double y);

}