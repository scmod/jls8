package c15.s9.p2;

public class DeterminingEnclosingInstances {

	// 在static context里面是has no immediately enclosing instance的
	void method() {
		Object o = this;
		new Object() {
			public void show() {
				System.out.println(o.getClass());
			}
		}.show();
	}

	public static void main(String[] args) {
		new DeterminingEnclosingInstances().method();

	}

	// If the class instance creation expression is unqualified
	// 这个如果是在static context中创建实例,是报错的,因为这里其实要this引用
	void createInnerA1Unqualified() {
		(this).new InnerA1();
	}

	// If C is an inner member class,
	class InnerA1 {
		class InnerA2 {
			class InnerA3 {

			}

			// The immediately enclosing instance of i is the n'th lexically
			// enclosing instance of this
			// InnerA3实例的immediately enclosing instance是this,也就是InnerA2实例
			void createInnerA1Unqualified() {
				(this).new InnerA3();
			}
		}
	}

	/*
	 * loop: If the class instance creation expression is qualified,
	 * 
	 * then the immediately enclosing instance of i is the object that is the
	 * value of the Primary expression or the ExpressionName
	 */
	/*
	 * If C is an anonymous class, and its direct superclass S is an inner
	 * class, then i may have an immediately enclosing instance with respect to
	 * S, determined as follows:
	 */
	// If S is a local class
	// static context里面并不会报错啊...
	static void createInnerB1Qualified() {
		class InnerB1 {

		}
		new InnerB1() {

		};
	}

	// If S is an inner member class
	class InnerB2 {

	}

	void createInnerB2Unqualified() {
		new InnerB2() {

		};
	}

	// If the class instance creation expression is qualified
	// 就循环一遍...goto loop,但是规范里面只有anonymous class加qualified这块

	void createInnerB4Qualified() {
		class InnerB3 {
			class InnerB4 {

			}
		}
		// 其他情况的话反正就是new InnerB4()之前那段表达式出来的实例
		// 算做InnerB4的immediately enclosing instance
		// 大概就这样吧...
		new InnerB3().new InnerB4();
	}

}