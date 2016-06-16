package cc.method;

import java.util.Collection;
import java.util.List;

public class GenericOverride {

	public static void main(String[] args) {
		new BB().toList(null);
	}
	
}
//泛型方法的重写,用擦除的方法能更好兼容以前的旧代码
class AA {
//	List toList(Collection c) {
//		return null;
//	}
	
	//这样子依旧算重写,兼容兼容...
	<T> List<T> toList(Collection<T> c) {
		System.out.println("AA");
		return null;
	}
}

class BB extends AA {
	@Override
	List toList(Collection c) {
		System.out.println("BB");
		return super.toList(c);
	}
}
