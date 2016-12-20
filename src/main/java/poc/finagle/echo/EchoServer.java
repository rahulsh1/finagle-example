package poc.finagle.echo;

import java.net.InetSocketAddress;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.example.echo.StringCodec;
import com.twitter.util.Future;

public final class EchoServer {

  public static void run(String[] args) {
    System.out.println("Running Echo Server");

    // Create a Service with String codec - String IN, String OUT
    Service<String, String> service = new Service<String, String>() {
      public Future<String> apply(String request) {
        System.out.println("received: " + request);
        return Future.value(request);
      }
    };

    System.out.println("start " + service);

    // Bind the Service to the Server @ 8900
    ServerBuilder.safeBuild(service,
      ServerBuilder.get().codec(new StringCodec()).name("EchoServer")
        .bindTo(new InetSocketAddress(8900)));
  }

}
