package c9.s3;

import java.lang.reflect.Modifier;

public class FieldDeclarations {

	public static void main(String[] args) {
		System.out.println(Modifier.toString(InfA.class.getDeclaredFields()[0].getModifiers()));
		System.out.println(InfC.KICK);
		//The field InfC.GIT is ambiguous
//		System.out.println(InfC.GIT);
	}
	
}

interface InfA {
	//默认public static final 
	int KICK = 0;
	int GIT = 0;
}

interface InfB {
	int KICK = 1;
	int GIT = 1;
}

interface InfC extends InfA, InfB {
	int KICK = 2;
}
