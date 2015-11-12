package core.thread;

import java.lang.reflect.Method;

/**
 * 问：java中类是否可以被两个类加载器加载？ java中存在双亲委派机制，因此所有的类加载请求最终都是送到顶层启动类加载器中完成
 * 
 * @author guor
 * @time 2015年11月11日 下午5:09:43
 */
public class TestClassLoader {
	public static void main(String[] args) throws Exception {
		System.out.println(0.1 * 3 != 0.3);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?> klass1 = classLoader.loadClass("core.thread.Temp");
		Method method1 = klass1.getMethod("getInstance");
		Temp obj1 = (Temp) method1.invoke(null);
		// System.out.println(klass1.getDeclaredField("p").get(klass1.newInstance()));

		CustomClassLoader myClassLoader = new CustomClassLoader();
		Class<?> klass2 = myClassLoader.loadClass("core.thread.Temp");
		Method method2 = klass2.getMethod("getInstance");
		Temp obj2 = (Temp) method2.invoke(null);
		// System.out.println(klass2.getDeclaredField("p").get(klass2.newInstance()));
		System.out.println(obj1 == obj2);
	}
}

class Temp {
	int p = 10;

	private Temp() {
	}

	private static Temp instance = new Temp();

	public static final Temp getInstance() {
		return instance;
	}
}

class CustomClassLoader extends ClassLoader {
}