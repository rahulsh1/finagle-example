package poc.finagle.http;

import java.net.InetSocketAddress;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.util.CharsetUtil;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.http.Http;
import com.twitter.util.Future;

public final class HttpServer {

  public static void run(String[] args) {
    System.out.println("Running HTTP Server");

    // Create HTTP Service
    Service<HttpRequest, HttpResponse> service = new Service<HttpRequest, HttpResponse>() {
      @Override
      public Future<HttpResponse> apply(HttpRequest request) {
        System.out.println(request);
        HttpResponse res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        res.setContent(ChannelBuffers.copiedBuffer("Hello World!", CharsetUtil.UTF_8));
        res.setHeader(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return Future.<HttpResponse>value(res);
      }
    };

    ServerBuilder.safeBuild(service,
      ServerBuilder.get()
        .codec(Http.get())
        .name("HttpServer")
        .bindTo(new InetSocketAddress("localhost", 10000)));
  }
}
