package dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by guor on 2016/3/18.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("client-side.xml");
        ctx.start();

        Helloworld helloworld = (Helloworld) ctx.getBean("helloworld", Helloworld.class);
        System.out.println(helloworld.hello());
        System.in.read();
    }
}
