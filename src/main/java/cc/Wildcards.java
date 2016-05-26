package cc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wildcards {

	//没有界限的通配符
	//Collection<?> c和Collection<Object> c的区别
	static void printCollection(Collection<?> c) {
		for (Object o : c) {
			System.out.println(o);
		}
	}
	
	//有界限的,似乎一般也用不到啊....
	static <T> void add(T element, List<? super T> list) {
		list.add(element);
	}
	
	//额..
	static <T extends U, U> void add(T element, U u, List<U> list) {
		list.add(element);
		list.add(u);
	}

	public static void main(String[] args) {
//		Collection<Object> cs = new ArrayList<String>();	//不行..
//		Collection<?> cs = new ArrayList<String>();			//?比较随意,然而什么都别想往里面加了..
//		Collection<?> cs = new ArrayList<>();				//7的语法糖,同上
//		Collection<String> cs = new ArrayList<>();			//....正常....
		Collection<String> cs = new ArrayList<String>();	//还是老实点..
		cs.add("hello");
		cs.add("world");
		printCollection(cs);
	}

}
