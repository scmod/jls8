package cc;

public class Names {

	public static void main(String[] args) {
		System.out.println(S.class);					//
		System.out.println(S.class.getCanonicalName());	//cc.Names.S,这个跟规范上说的一样
		System.out.println(S.class.getName());			//cc.Names$S,这个却不是
		System.out.println(S.class.getSimpleName());	//S
		
		System.out.println("====>>>>@@@@====炫酷的分割线====@@@@<<<<====");
		
		System.out.println(ExNames.S.class);					//
		System.out.println(ExNames.S.class.getCanonicalName());	//cc.Names.S,这个跟规范上说的一样
		System.out.println(ExNames.S.class.getName());			//cc.Names$S,这个却不是
		System.out.println(ExNames.S.class.getSimpleName());	//S
		//似乎是没有api能拿到fully qualified name了...
	}
	
	class S {
		
	}
	
}

class ExNames extends Names {
	
}