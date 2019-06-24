package tutorials.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Encode2Decode {
    public static void main(String[] args) {
        //编码
        ByteBuffer buffer1 = ByteBuffer.allocate(9);
        buffer1.putInt(12);//int 4个字节
        buffer1.put("hello".getBytes(StandardCharsets.UTF_8));//字符串，长度为5，5个字节
        byte[] transferByte = buffer1.array();//网络上传输的字节数据，总共9个字节
        ByteBuffer buffer2 = ByteBuffer.wrap(transferByte);
        //解码
        int id = buffer2.getInt();//读取4个字节的整形数据
        byte[] sayBs = new byte[5];
        buffer2.get(sayBs);//批量读取5个字节的数据，并转换为字符串
        String say = new String(sayBs, StandardCharsets.UTF_8);
        System.out.println("id:" + id);
        System.out.println("say:" + say);
    }
}
