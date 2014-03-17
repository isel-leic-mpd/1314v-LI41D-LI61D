package isel.mpd.typesystem;

import java.lang.reflect.Field;

public class TypeInspection {

	public static void main(String[] args) {
		
		Veichle v = new Veichle();
		Veichle v1 = new Car();
		
		Class c = v.getClass();
		Class c1 = v1.getClass();
		
		
		Field[] fields = c1.getFields();
		for (int i = 0; i < fields.length; i++) {
			
		}
		
		
		System.out.println(c1 == c);

	}

}
