package cc.method;

/**
 * 书上的例子真销魂...
 * 
 * @author John Smith
 */
public class Test {

	// 原来这也行啊...虽然没什么用好像
	void m(Test this) {
	}

	// OK: receiver parameter in an instance method
	// static void n(Test this) {
	// }

	// 内部类的初始化本来就会把父类实例传进来
	// Illegal: receiver parameter in a static method
	class A {
		A(Test Test.this) {
		}

		// OK: the receiver parameter represents the instance
		// of Test which immediately encloses the instance
		// of A being constructed.
		void m(A this) {
		}

		// OK: the receiver parameter represents the instance
		// of A for which A.m() is invoked.
		class B {
			B(Test.A A.this) {
			}

			// OK: the receiver parameter represents the instance
			// of A which immediately encloses the instance of B
			// being constructed.
			void m(Test.A.B this) {
			}
			// OK: the receiver parameter represents the instance
			// of B for which B.m() is invoked.
		}
	}
}