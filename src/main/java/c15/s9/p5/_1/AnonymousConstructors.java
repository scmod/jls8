package c15.s9.p5._1;

import java.io.EOFException;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.naming.ldap.LdapReferralException;

public class AnonymousConstructors {

	// 假设匿名类C是S的直接子类

	public static void main(String[] args) {
		// 如果S不是一个内部类,或者是个static context里的local class
		// 那根据创建这个匿名类实例的参数,会有个跟参数一一对应且顺序相同的构造方法
		new SA1() {

		}.showCtors();
		methodSA2();
		// 除了上面这俩情况,其他的都会生成一个第一个参数是他的immediately encloses
		// the declaration of S
		new AnonymousConstructors().new SB1() {

		}.showCtors();
		// [c15.s9.p5._1.AnonymousConstructors$4
		// (c15.s9.p5._1.AnonymousConstructors,c15.s9.p5._1.AnonymousConstructors,int)]
		// ??怎么传了两次.....为了搞明白这个..再搞个methodO1()
		new AnonymousConstructors().methodSB2();
		new AnonymousConstructors().methodO1();
		// 这样就清楚了,local class本身也是一个inner class,所以其实methodSB2()是一个localclass的匿名子类
		// 所以自然传了两次...

		// 直接在代码块里面捕捉是不会加到构造方法后面的
		try {
			new SC1() {
				{
					if (false)
						try {
							throw new IOException();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}.showCtors();
		} catch (LdapReferralException | IllegalAccessException e) {
			e.printStackTrace();
		}
		// 在new匿名类实例外捕捉,这样这个在普通代码块里的检查异常也会被加到他的构造方法throws后面
		try {
			try {
				new SC1() {
					{
						if (false)
							throw new IOException();
					}
				}.showCtors();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (LdapReferralException | IllegalAccessException e) {
			e.printStackTrace();
		}
		// 这个比较符合常理,因为可能匿名类里面有一堆东西自顾自的初始化,然后会抛出的检查异常,统统在new外层捕捉

		// IOException > RemoteException > AccessException & EOFException
		// 父类构造抛的异常会跟代码块及代码初始化什么的比较..最后只加上那个最大的和其他的
		// 这里AccessException & EOFException & RemoteException全被一个IOException代替了
		try {
			new SC2() {
				{
					if (false)
						throw new AccessException(null);
					if (false)
						throw new IOException();
				}
			}.showCtors();
		} catch (LdapReferralException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}

		methodFinal();

	}

	static void methodSA2() {
		class SA2 extends S {
			public SA2(int i) {
				super(i);
			}
		}
		new SA2(1) {

		}.showCtors();
	}

	class SB1 extends S {

	}

	void methodSB2() {
		class SB2 extends S {
			public SB2(int i) {
				super(i);
			}
		}
		new SB2(1) {

		}.showCtors();
	}

	void methodO1() {
		new Object() {
			void showCtors() {
				System.out.println(Arrays.toString(this.getClass()
						.getDeclaredConstructors()));
			}
		}.showCtors();
	}

	// 为啥匿名内部类调用外部的值需要是final或者effectively final
	static void methodFinal() {
		int i = 0;
		new Runnable() {
			@Override
			public void run() {
				// i++,假设能重新赋值,那么i=1
				System.out.println(i);
			}

			void showCtors() {
				System.out.println(this.getClass().getDeclaredConstructors()[0]
						.getParameters()[0].getModifiers());
			}
		}.showCtors();
		// .run();System.out.println(i);//i=0
		// run完后实际这里的i还是0,跟方法传参用"="赋值后只是方法里的参数值变了,原来值没变一个道理
		// 这里的i会被匿名内部类当做参数传进去,即使在里面赋值,结果出来还是没变化,
		// 会让人感觉有歧义,所以干脆只能final?规范里面暂时没找到...
	}

}

class S {

	protected int i;

	public S() {
	}

	public S(int i) {
		this.i = i;
	}

	void showCtors() {
		System.out.println(Arrays.toString(this.getClass()
				.getDeclaredConstructors()));
	}
}

class SA1 extends S {

}

class SC1 extends S {
	public SC1() throws LdapReferralException, IllegalAccessException {

	}
}

class SC2 extends S {
	public SC2() throws LdapReferralException, IllegalAccessException,
			EOFException, RemoteException {

	}
}
