package cc.method;

public class InlineGetter {

	public static void main(String[] args) {
		new InlineGetter().method();
	}

	//这个例子final不final都能内联啊....
	final void method() {
		System.out.println("!!!");
	}

}

/**
 * afterInline -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions
 * -XX:+PrintAssembly -XX:+DebugNonSafepoints -Xcomp
 * -XX:CompileCommand=compileonly,*InlineGetter.main
 * -XX:CompileCommand=compileonly,*InlineGetter.method
 */
/**
 * noInline
 * -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
 * -XX:+DebugNonSafepoints -Xcomp
 * -XX:CompileCommand=dontinline,*InlineGetter.method
 * -XX:CompileCommand=compileonly,*InlineGetter.main
 * -XX:CompileCommand=compileonly,*InlineGetter.method
 */

