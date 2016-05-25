package core.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

/**
 * 通过Socket发起http请求
 * 
 * @author guor
 * @time 2015年11月16日 下午12:06:16
 */
public class TestSocket {
    public static final String SEQUENCE = "\r\n";

    public static void main(String[] args) throws Exception {
        System.out.println(new String(get("http://news.qq.com/"), "GB2312"));
    }

    static byte[] get(String url) throws IOException {
        URI uri = URI.create(url);
        String host = uri.getHost();
        int port = uri.getPort() == -1 ? 80 : uri.getPort();
        String path = uri.getPath();
        Socket socket = new Socket(host, port);

        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        try {
            pw.write("GET " + path + " HTTP/1.0\r\n");
            pw.write("Accept: text/plain, text/html, text/*\r\n");
            pw.write("Connection: keep-alive\r\n");
            pw.write("Host: " + host + "\r\n");
            pw.write("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36\r\n");
            pw.write("\r\n");
            pw.flush();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] tmp = new byte[4096];
            int read = -1;
            InputStream in = socket.getInputStream();
            try {
                while ((read = in.read(tmp)) != -1) {
                    out.write(tmp, 0, read);
                }
            } finally {
                in.close();
            }
            return out.toByteArray();
        } finally {
            pw.close();
            socket.close();
        }
    }

    static byte[] post(String url, Map<String, String> params) throws IOException {
        URI uri = URI.create(url);
        String host = uri.getHost();
        int port = uri.getPort() == -1 ? 80 : uri.getPort();
        String path = uri.getPath();
        Socket socket = new Socket(host, port);

        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        try {
            pw.write("GET " + path + " HTTP/1.0\r\n");
            pw.write("Accept: text/plain, text/html, text/*\r\n");
            pw.write("Connection: keep-alive\r\n");
            pw.write("Host: " + host + "\r\n");
            pw.write("Content-Type: application/x-www-form-urlencoded\r\n");
            String content = map2String(params);
            pw.write("Content-Length: " + content.length() + "\r\n");
            pw.write("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36\r\n");
            pw.write("\r\n");
            pw.flush();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] tmp = new byte[4096];
            int read = -1;
            InputStream in = socket.getInputStream();
            try {
                while ((read = in.read(tmp)) != -1) {
                    out.write(tmp, 0, read);
                }
            } finally {
                in.close();
            }
            return out.toByteArray();
        } finally {
            pw.close();
            socket.close();
        }
    }

    static String map2String(Map<String, String> params) {
        StringBuilder buffer = new StringBuilder();
        Iterator<String> iterator = params.keySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (i > 0) {
                buffer.append("&");
            }
            buffer.append(k).append("=").append(params.get(k));
        }
        return buffer.toString();
    }
}
