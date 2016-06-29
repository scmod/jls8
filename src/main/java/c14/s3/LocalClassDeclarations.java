package c14.s3;

public class LocalClassDeclarations {

	/*
	 * A local class is a nested class (§8 (Classes)) that is not a member of
	 * any class and that has a name (§6.2, §6.7).
	 */
	void method() {
		class OMG {

		}
		OMG omg = new OMG();
	}

}

class Global {
	class Cyclic {
	}
	int i;
	void foo() {
		new Cyclic(); // create a Global.Cyclic
		// class Cyclic extends Cyclic {} // circular definition
		// 声明之后会hiding掉Global.Cyclic,再new就是这个而不是Global.Cyclic了
		class Cyclic extends Global.Cyclic {
		}
		/*label*/
		{
			class Local {
			}
			{
				//label这个作用域里面有这个class了,不能重复声明
//				class Local {} // compile-time error
			}
//			class Local {} // compile-time error
			class AnotherLocal {
				void bar() {
					int i = Global.this.i;
					class Local {
					} // ok
				}
			}
		}
		class Local {
		} // ok, not in scope of prior Local
	}
}
