package core;

import java.lang.reflect.Field;

import core.model.Person;

public class TestUnsafe {

	@SuppressWarnings({ "restriction" })
	public static void main(String[] args) throws Exception {
		sun.misc.Unsafe unsafe = getUnsafe();
		Person p = (Person) unsafe.allocateInstance(Person.class);
		System.out.println(p);
	}

	@SuppressWarnings("restriction")
	public static sun.misc.Unsafe getUnsafe() throws Exception {
		Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		return (sun.misc.Unsafe) f.get(null);
	}
}
