package c9.s9;

import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLTransientException;
import java.util.List;
import java.util.concurrent.TimeoutException;
//这块还没怎么看...以后慢慢看..
public class FunctionTypes {

}

interface A {
	List<String> foo(List<String> arg) throws IOException,
			SQLTransientException;
}

interface B {
	List foo(List<String> arg) throws EOFException, SQLException,
			TimeoutException;
}

interface C {
	List foo(List arg) throws Exception;
}

class AB implements A, B {

	@Override
	public List<String> foo(List<String> arg) throws EOFException,
			SQLTransientException {
		return null;
	}

}

class ABC implements A, B, C {

	// 可以抛出兼容的子异常,也可以啥都不抛
	// 返回类型按理用List<String>...
	@Override
	public List foo(List arg) /* throws EOFException, SQLTransientException */{
		return null;
	}

}
