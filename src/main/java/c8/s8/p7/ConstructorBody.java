package c8.s8.p7;

public class ConstructorBody {

	// Recursive constructor invocation ConstructorBody()
	// 循环引用是会被发现的!
	public ConstructorBody() {
		// this(new String[]{});
	}

	public ConstructorBody(String str) {
		this();
	}

	public ConstructorBody(String... strs) {
		this(strs[0]);
	}

}











