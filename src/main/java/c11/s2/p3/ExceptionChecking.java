package c11.s2.p3;

import java.io.FileNotFoundException;

public class ExceptionChecking {

	public ExceptionChecking() {
		methodUnchecked();
//		methodChecked();//可抛可catch,不过抛出的话以后new这个对象的地方都要抛出或者catch...
	}
	
	{
		methodUnchecked();
		try {
			methodChecked();//只能catch
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void method() {
		try {
			throw new FileNotFoundException();
		} 
		//The exception FileNotFoundException is already caught by the alternative IOException
		//有了multicatch之后基本也没什么差别,就是父类异常不能跟子类并排了
//		catch (FileNotFoundException | IOException e) {
//		} 
		catch(NullPointerException e) {
			
		}
		catch(Exception e) {
			
		}
	}
	
	void methodUnchecked() throws RuntimeException {
		
	}
	
	void methodChecked() throws Exception {
		
	}
	
}
