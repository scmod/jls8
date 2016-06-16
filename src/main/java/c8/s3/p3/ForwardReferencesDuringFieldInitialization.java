package c8.s3.p3;

public class ForwardReferencesDuringFieldInitialization {

	//学书上例子...
	{
		i = 3;// 在赋值表达式左侧
//		System.out.println(i);//不能在定义前引用
		// 不使用simple name可以,不知道为啥规范要这么定...默认感觉this.i就是i啊...代码块又没有传参什么的..
		System.out.println(this.i);// 3,
		new Object() {
			void foo() {
				i++;
				System.out.println(i);// 4
			}
		}.foo();// 不在同一个类中访问 TODO:这儿也是..为啥这么规定..方便写编译器?
	}

	int i;

	public static void main(String[] args) {
		Test2 test2 = new Test2();
		System.out.println(test2.i);
		System.out.println(test2.j);
		System.out.println(Z3.i);

		System.out.println("=====================");
		new ForwardReferencesDuringFieldInitialization();
	}

}

class Test1 {

	// int i = j;//Cannot reference a field before it is defined
	int j = 0;

}

class Test2 {

	public Test2() {
		j = 10;
	}

	int j;
	int i = j;
}

class Z1 {
	// static int k = j;//Cannot reference a field before it is defined
	int i = j;
	static int j = 0;
}

class Z2 {
	static {
		// i = j + 2;//Cannot reference a field before it is defined
	}
	static int i, j;
	static {
		j = 4;
	}
}

class Z3 {
	static int peek() {
		return j;
	}

	static int i = peek();
	static int j = 1;
}

class UseBeforeDeclaration {
	static {
		x = 100;
		// ok - assignment
		// int y = x + 1;
		// error - read before declaration
		int v = x = 3;
		// ok - x at left hand side of assignment
		int z = UseBeforeDeclaration.x * 2;
		// ok - not accessed via simple name
		Object o = new Object() {
			void foo() {
				x++;
			}

			// ok - occurs in a different class
			{
				x++;
			}
			// ok - occurs in a different class
		};
	}
	{
		j = 200;
		// ok - assignment
		// j = j + 1;
		// error - right hand side reads before declaration
		// int k = j = j + 1;
		// error - illegal forward reference to j
		int n = j = 300;
		// ok - j at left hand side of assignment
		// int h = j++;
		// error - read before declaration
		int l = this.j * 3;
		// ok - not accessed via simple name
		Object o = new Object() {
			void foo() {
				j++;
			}

			// ok - occurs in a different class
			{
				j = j + 1;
			}
			// ok - occurs in a different class
		};
	}
	int w = x = 3;
	// ok - x at left hand side of assignment
	int p = x;
	// ok - instance initializers may access static fields
	static int u = (new Object() {
		int bar() {
			return x;
		}
	}).bar();
	// ok - occurs in a different class
	static int x;
	int m = j = 4;
	// ok - j at left hand side of assignment
	int o = (new Object() {
		int bar() {
			return j;
		}
	}).bar();
	// ok - occurs in a different class
	int j;
}
