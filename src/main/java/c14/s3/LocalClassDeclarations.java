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
