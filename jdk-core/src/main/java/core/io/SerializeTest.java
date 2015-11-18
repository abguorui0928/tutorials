package core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 通过序列化和反序列化，可以将单例对象改造成多列对象
 * 
 * @author guor
 * @time 2015年11月18日 上午11:21:04
 */
public class SerializeTest {
	public static void main(String[] args) throws Exception {
		Serial serial = Serial.getInstance();
		System.out.println(serial);

		ObjectOutput output = new ObjectOutputStream(new FileOutputStream(new File("d:/data")));
		output.writeObject(serial);
		output.close();

		ObjectInput input = new ObjectInputStream(new FileInputStream(new File("d:/data")));
		Object obj = input.readObject();
		if (obj instanceof Serial) {
			System.out.println(obj);
		}
		input.close();
	}
}

class Serial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Serial() {
		super();
	}

	private static final Serial instance = new Serial();

	public static final Serial getInstance() {
		return instance;
	}
}
