package cc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeVariable {

	
	<T extends V1 & V2> void method(T t) {
		t.methodV1();
		t.methodV2();
	}
	
	<T extends V1, U extends V2> void method(T t, U u) {
		t.methodV1();
		u.methodV1();
		u.methodV2();
	}

	static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
	
	public static void main(String[] args) {
		//<String, String>newHashMap();		//报错
		TypeVariable.<String, String>newHashMap();//x.<>就没事...
		//https://docs.oracle.com/javase/tutorial/java/generics/methods.html
		setMap(TypeVariable.<String, String>newHashMap());//略神奇
	}
	
	
	<T> void method(List<T> t) {
		
	}
	static void setMap(Map<String, String> map) {
		
	}
	
}

class V1 {
//	void methodV1() {
//		
//	}
	
	public void methodV1() {
		System.out.println("V1 V1");
	}
}

interface V2 {
	void methodV1();
	void methodV2();
}

class Vx extends V1 implements V2 {

	public void methodV2() {
		
	}
	
}