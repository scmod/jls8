package cc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Resource;

public class TypeAnnotations {

	@Deprecated 
	@Resource 
	@TypeUse 
	@Field2 
	int[] arr0;
	@Deprecated int @TypeUse [] arr1;
	int @TypeUse(message = "what?") [][] arr2;
	int[] @TypeUse [] arr3;
	List<@TypeUse String> list;

	public static void main(String[] args) throws Exception {
 
		System.out.println("get annotations directly");
		for (Field f : TypeAnnotations.class.getDeclaredFields()) {
			System.out.println(f.getName() + " : "
					+ f.getDeclaredAnnotations().length);
		}

		System.out.println("===============================================");
		System.out.println("get from annotatedType");
		for (Field f : TypeAnnotations.class.getDeclaredFields()) {
			if (f.getAnnotatedType().getDeclaredAnnotations().length > 0)
				System.out.println(f.getName()
						+ " : "
						+ f.getAnnotatedType().getAnnotation(TypeUse.class)
								.message());
		}
		
		System.out.println("===============================================");
		System.out.println("get from special annotatedType");
		System.out.println(((AnnotatedArrayType)TypeAnnotations.class.getDeclaredField("arr3")
				.getAnnotatedType()).getAnnotatedGenericComponentType().getType());	//null
		System.out.println(((AnnotatedParameterizedType)TypeAnnotations.class.getDeclaredField("list")
				.getAnnotatedType()).getAnnotatedActualTypeArguments()[0].getAnnotations()[0]);
		//这好麻烦...要强转成对应annotatedType,还好我用不着~
	}
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Field2 {
}

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@interface TypeUse {
	String message() default "ok~";
}