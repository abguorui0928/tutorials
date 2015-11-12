package core.model;

public class Person {
	static final int DEFAULT_SEX = 1;
	public static int sex = DEFAULT_SEX;
	public String name = "default";

	static {
		System.out.println("------person initialed------");
	}

	private Person(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ",sex=" + sex + "]";
	}
}
