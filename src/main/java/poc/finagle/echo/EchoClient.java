package poc.finagle.echo;

import java.net.InetSocketAddress;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.example.echo.StringCodec;
import com.twitter.util.FutureEventListener;

public final class EchoClient {

  public static void run(String[] args) {
    System.out.println("Running Echo Client");

    // Connect to Service @ 8900 with String Codec
    final Service<String, String> client = ClientBuilder
      .safeBuild(ClientBuilder.get()
          .codec(new StringCodec())
          .hosts(new InetSocketAddress(8900))
          .hostConnectionLimit(2));

    System.out.println("apply " + client);

    client.apply("hello world!\n").addEventListener(
      new FutureEventListener<String>() {

        @Override
        public void onSuccess(String response) {
          System.out.println("received response: " + response);
        }

        @Override
        public void onFailure(Throwable cause) {
          cause.printStackTrace();
          System.out.println("failed with cause:" + cause);
        }
      });

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
    }
    client.release();
  }

}
