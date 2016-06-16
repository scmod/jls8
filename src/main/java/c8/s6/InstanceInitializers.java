package c8.s6;

public class InstanceInitializers {
	
	private int i;
	{
		System.out.println("!");
		this.hashCode();
		super.hashCode();
		System.out.println(i);
		/**@see ForwardReferencesDuringFieldInitialization*/
//		System.out.println(j);//Cannot reference a field before it is defined
		System.out.println(k);
	}

	private static int k;
	private int j;
	
	public static void main(String[] args) {
		new InstanceInitializers();
		/**
		 * Exception checking for an instance initializer is specified in §11.2.3
		 * @see ExceptionChecking
		 */
	}
	
}
