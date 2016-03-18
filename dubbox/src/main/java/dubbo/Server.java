package dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by guor on 2016/3/18.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("server-side.xml");
        ctx.start();
        System.in.read();
    }
}
