package tutorials;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        router.post("/test").handler(context -> {
            HttpServerRequest request = context.request();
            request.bodyHandler(buffer -> System.out.println(buffer.toJsonObject()));
            context.response().end();
        });
        router.get("/test").handler(context -> {
            context.response().end("hello world");
        });
        vertx.createHttpServer().requestHandler(router).listen(8888);
    }
}
