package poc.finagle;

import poc.finagle.echo.EchoClient;
import poc.finagle.echo.EchoServer;
import poc.finagle.http.HttpClient;
import poc.finagle.http.HttpServer;

/**
 * Main class to run all the examples.
 */
public class Main {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("java Main <1=HTTP, 2=Echo> <1=Client, 0=Server>");
      System.exit(1);
    }

    int type = Integer.parseInt(args[0]);
    int client = Integer.parseInt(args[1]);
    if (type == 1) {
      if (client == 1) {
        HttpClient.run(null);
      } else {
        HttpServer.run(null);
      }
    } else if (type == 2) {
      if (client == 1) {
        EchoClient.run(null);
      } else {
        EchoServer.run(null);
      }
    }
  }
}
