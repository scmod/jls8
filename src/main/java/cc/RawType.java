package cc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * rawtype主要是因为要兼容旧的代码,并不是所有泛型的类都能获取到完整的信息,这些都是为了兼容 见4.7 Reifiable Types, 4.8
 * Raw Types
 */
public class RawType {

	// @SuppressWarnings("rawtypes")
	@SuppressWarnings({ "unused", "null" } )
	public static void main(String[] args) throws Exception {
		// rawtype就是指这种..明明应该带上类型的却不带的...
		List list = new ArrayList();
		// 或者元素是rawtype的数组
		List[] lists = null;
		// 或者是一个名为R的rawtype中非static并且不是继承自R的成员类
		new OuterRaw1().new InnerRaw1().t = "1";
		new OuterRaw2().new InnerRaw2().s = "1";
		/**
		 * 这俩反编译出来有点神奇
		 * void tmp21_18 = new OuterRaw1();tmp21_18.getClass();new OuterRaw1.InnerRaw1(tmp21_18).t = "1"; 
		 * void tmp45_42 = new OuterRaw2();tmp45_42.getClass();new OuterRaw2.InnerRaw2(tmp45_42).s = "1";
		 * void类型的变量,大概是反编译工具自己弄得?~ 内部类初始化会把外部类传进去这个倒是没啥...
		 */
		// rawtype..编译运行并不会报错...
		new OuterRaw3().list.add("123");
	    
		RawMembers rw = null;
		//不被擦除的情况,继承自一个non-generic的成员
		Collection<Number> cn = rw.myNumbers();
		//这里实现了Collection<String>但是进行了擦除(被T覆盖?),如果RawMembers不是一个Rawtype的话就不会擦除(覆盖?)
		Iterator<String> is = rw.iterator();
		RawMembers.class.getGenericInterfaces(); //[java.util.Collection<java.lang.String>]并没有擦除,感觉只是覆盖了
		//不被擦除的情况,static的声明,似乎不是static的变量声明也不会被擦啊
		Collection<NonGeneric> cnn = rw.cng;
		//反编译出来是这样子:static Collection<NonGeneric> cng = new ArrayList();
		//new ArrayList()的泛型好像没什么保留的必要,只是码字时候看起来比较对称....
		System.out.println(RawMembers.class.getDeclaredField("cng").getGenericType());//java.util.Collection<cc.NonGeneric>
		
		System.out.println("done");
		//擦不擦除...重在感觉...
	}

}

class OuterRaw1<T> {
	class InnerRaw1 {
		T t;
	}
}

class OuterRaw2<T> {
	class InnerRaw2<S> {
		S s;
	}
}

class OuterRaw3<T extends Number> {

	List<T> list = new ArrayList<T>();

}

class NonGeneric {
	Collection<Number> n;
	Collection<Number> myNumbers() {
		return null;
	}
}

abstract class RawMembers<T> extends NonGeneric implements Collection<String> {
	static Collection<NonGeneric> cng = new ArrayList<NonGeneric>();

}
