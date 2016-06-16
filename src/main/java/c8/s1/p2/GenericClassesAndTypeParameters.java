package c8.s1.p2;

import java.util.Vector;

public class GenericClassesAndTypeParameters<T> {
	// 运行时才知道T是啥..静态显然是不行的
	// static T t;

	public static void main(String[] args) {
		Vector<String> x = new Vector<String>();
		Vector<Integer> y = new Vector<Integer>();
		System.out.println(x.getClass() == y.getClass());// true

		ReprChange<C1, C2> rc = new ReprChange<>();
		rc.set(new C2());
		rc.get();
		
		Seq<String> strs = new Seq<String>("a", new Seq<String>("b",
				new Seq<String>()));
		Seq<Number> nums = new Seq<Number>(new Integer(1), new Seq<Number>(
				new Double(1.5), new Seq<Number>()));
		Seq<String>.Zipper<Number> zipper = strs.new Zipper<Number>();
		Seq<Pair<String, Number>> combined = zipper.zip(nums);
	}

}

/**
 * The generic class S<T> may not subclass java.lang.Throwable
 * 
 * This restriction is needed since the catch mechanism of the Java Virtual
 * Machine works only with non-generic classes. 虚拟机不让Throwable子类带泛型~
 */
// class S<T> extends Throwable {
//
// }

// Cycle detected: the type S cannot extend/implement itself or one of its own
// member types,这....
// class K<S extends S> {}

// 书上这个例子啥用????
interface ConvertibleTo<K> {
	K convert();
}

class C1 implements ConvertibleTo<C2> {

	@Override
	public C2 convert() {
		return new C2();
	}

}

class C2 implements ConvertibleTo<C1> {

	@Override
	public C1 convert() {
		return new C1();
	}

}

class ReprChange<T extends ConvertibleTo<S>, S extends ConvertibleTo<T>> {

	T t;

	void set(S s) {
		t = s.convert();
	}

	S get() {
		return t.convert();
	}
}

//Nested Generic Classes
//这又是要干嘛????
class Seq<T> {
	T head;
	Seq<T> tail;

	Seq() {
		this(null, null);
	}

	Seq(T head, Seq<T> tail) {
		this.head = head;
		this.tail = tail;
	}

	boolean isEmpty() {
		return tail == null;
	}

	class Zipper<S> {
		Seq<Pair<T, S>> zip(Seq<S> that) {
			if (isEmpty() || that.isEmpty()) {
				return new Seq<Pair<T, S>>();
			} else {
				Seq<T>.Zipper<S> tailZipper = tail.new Zipper<S>();
				return new Seq<Pair<T, S>>(new Pair<T, S>(head, that.head),
						tailZipper.zip(that.tail));
			}
		}
	}
}

class Pair<T, S> {
	T fst;
	S snd;

	Pair(T f, S s) {
		fst = f;
		snd = s;
	}
}

