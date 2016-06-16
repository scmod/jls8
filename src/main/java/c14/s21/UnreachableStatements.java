package c14.s21;

public class UnreachableStatements {

	// continue和break类似
	void method() {
		for (int i = 0; i < 100; i++) {
			if (i > 50) {
				try {
					// 只要是编译器觉得break是有条件的,外面的break就可达
					if (true)
						break;
				} finally {

				}
				// 只给出warn:Dead code
				break;
			}
		}
	}

	void method1() {
		for (int i = 0; i < 100; i++) {
			if (i > 50) {
				try {
					break;
				} catch (Exception e) {
					// 如果有catch,那说明有可能在执行try的时候有机会抛出异常导致没有执行try里的break
					// 所以下面的break是reachable
				}
				break;
			}
		}
	}

	void method2() {
		for (int i = 0; i < 100; i++) {
			if (i > 50) {
				try {
					break;
				} finally {

				}
				// break;//Unreachable code
			}
		}
	}

	//这个可以看while块的说明,表达式不为true,或者存在一个reachable的break
	//不然就不能complete normally,后面代码就跪了
	void method4() {
		while (true) {
			try {
				return;
			} finally {
				continue;
			}
		}//finally block does not complete normally
//		System.out.println();// Unreachable code
	}

	// 还有各种reachable的规则,if,while,switch什么的...尽在14.21...
	static final boolean DEBUG = false;

	void method5() {
		if (DEBUG) {
			int x = 3;
		}
	}

	void method6() {
		int x;
		// while (DEBUG) { x=3;}//Unreachable code
	}

}
