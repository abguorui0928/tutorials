package tutorials.protobuf;

import com.google.protobuf.util.JsonFormat;
import tutorials.protobuf.entity.PersonProtos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        PersonProtos.Person.Builder builder = PersonProtos.Person.newBuilder();
        builder.setId(12);
        builder.setName("hello");
        PersonProtos.Person person = builder.build();
        String js = JsonFormat.printer().print(builder);
        System.out.println(js);

        ByteArrayOutputStream bout = new ByteArrayOutputStream(100);
        person.writeTo(bout);


        PersonProtos.Person deserialized
                = PersonProtos.Person.newBuilder()
                .mergeFrom(new ByteArrayInputStream(bout.toByteArray())).build();
        System.out.println(deserialized.getName());
    }
}
