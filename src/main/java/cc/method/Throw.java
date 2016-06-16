package cc.method;

/**
 * Type variables are allowed in a throws clause even though they are not allowed
 * in a catch clause 
 * @author John Smith
 */
public class Throw<T extends Exception> {

	void method() throws T {
		
	}
	
	void catchT() {
		try {
//			method();//抛出的不是RuntimeException的话就呵呵了...
		} 
		//Cannot use the type parameter T in a catch block
//		catch (T t) {
//		}
		finally {
			
		}
	}
	
}

