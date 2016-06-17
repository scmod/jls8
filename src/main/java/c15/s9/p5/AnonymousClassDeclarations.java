package c15.s9.p5;

import java.lang.reflect.Modifier;

public class AnonymousClassDeclarations {

	public static void main(String[] args) {
		new AnonymousClassDeclarations().method();
	}
	
	/*
	 * An anonymous class declaration is automatically derived from a class
	 * instance creation expression by the Java compiler.
	 * 
	 * An anonymous class is never abstract (§8.1.1.1).
	 * 
	 * An anonymous class is always implicitly final (§8.1.1.2).
	 * 
	 * An anonymous class is always an inner class (§8.1.3); it is never static
	 * (§8.1.1, §8.5.1)
	 */

	// 匿名类是在一个类创建实例的时候由编译器自己弄出来的....
	// 永远是一个内部类,永远不是static,永远是隐式的final的
	void method() {
		int i = 0;
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(this.getClass().getModifiers());//0
				System.out.println(Modifier.isStatic(this.getClass().getModifiers()));
			}
		}).start();
	}
	//实际上这个匿名内部类的Modifilers啥都没...
	//好像是说旧的jdk都没有给匿名内部类加上modifier,为了兼容所以都不加
	//http://bugs.java.com/view_bug.do?bug_id=4777101
	//http://bugs.java.com/view_bug.do?bug_id=6520152
	//这俩是说final的,modifiler在计算default serial version UID时候有用到,
	//如果这个匿名类是在一个static context(static方法块,static方法什么的)
	//那这匿名类其实会是static的,并不是完全像规范说的那样never static
	//但是static的modifier应该也是类似,为了兼容,不然uid全变了,序列化什么的就完蛋了....


}
