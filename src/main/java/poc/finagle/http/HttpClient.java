package poc.finagle.http;

import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.util.CharsetUtil;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.http.Http;
import com.twitter.util.FutureEventListener;

public final class HttpClient {

  public static void run(String[] args) {
    System.out.println("Running HTTP Client");

    // Bind to Server @ 10000
    final Service<HttpRequest, HttpResponse> client = ClientBuilder.safeBuild(
        ClientBuilder.get()
            .codec(Http.get())
            .hosts("localhost:10000")
            .hostConnectionLimit(10));

    final String endPoint = "/";
    final HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, endPoint);
    request.addHeader("Host", "localhost"); // to prevent Bad Host errors

    for (int i = 0; i < 5; i++) {
      client.apply(request).addEventListener(
          new FutureEventListener<HttpResponse>() {
            public void onSuccess(HttpResponse response) {
              System.out.println("received response: " + response);
              System.out.println(response.getContent().toString(
                  CharsetUtil.UTF_8));
            }

            public void onFailure(Throwable cause) {
              System.out.println("failed with cause: " + cause);
            }
          }
      );
    }

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
    }
    client.release();
  }

}
