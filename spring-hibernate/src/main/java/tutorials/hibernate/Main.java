package tutorials.hibernate;

import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tutorials.hibernate.entity.User;
import tutorials.hibernate.repository.UserRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
        User user = new User();
        user.setUserName("guor");
        user.setEmailAddress("abguorui0928@163.com");
        user.setCredential("ABCDEFGH");
        user.setDisplayName("郭锐");
        user.setCreateDate(Calendar.getInstance());
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        user = userRepository.save(user);
        long id = user.getId();

        System.out.println(id);

        User u1 = userRepository.findOne(id);
        System.out.println(u1);

        u1.setCredential("ABCDEFGHH");
        userRepository.save(u1);

        User u2 = userRepository.findOne(id);
        System.out.println(u2);

        userRepository.delete(u2);

        User u3 = userRepository.findOne(id);
        System.out.println(u3);
        ctx.close();
    }
}
