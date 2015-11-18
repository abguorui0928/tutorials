package core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author guor
 * @time 2015年11月18日 上午10:55:05
 */
public class IoTest {

	public static void main(String[] args) throws IOException {
		// System.out.println(buffered("data"));
		System.out.println(stream("data"));
	}

	static String buffered(String filename) throws IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(filename)));
		try {
			String tmp = null;
			/**
			 * if replace with isEmpty,there will be a trap
			 */
			while ((tmp = br.readLine()) != null) {
				buffer.append(tmp);
				buffer.append('\n');
			}
			buffer.deleteCharAt(buffer.length() - 1);
		} finally {
			br.close();
		}
		return buffer.toString();
	}

	static String stream(String filename) throws IOException {
		InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
		try {
			InputStreamReader reader = new InputStreamReader(in);
			try {
				StringBuilder buffer = new StringBuilder();
				int read = -1;
				char[] tmp = new char[4 * 1024];
				while ((read = reader.read(tmp)) != -1) {
					buffer.append(tmp, 0, read);
				}
				return buffer.toString();
			} finally {
				reader.close();
			}
		} finally {
			in.close();
		}
	}

	static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}
}