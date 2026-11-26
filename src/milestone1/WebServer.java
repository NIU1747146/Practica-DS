package milestone1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.StringTokenizer;
import milestone1.requests.Request;
import milestone1.requests.RequestArea;
import milestone1.requests.RequestReader;
import milestone1.requests.RequestRefresh;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// WebServer.java
// HTTP server on port 8080: receives the GET requests of the simulator or the Flutter app, builds
// the Request, processes it and answers with its JSON. Each connection runs in its own thread
// (SocketThread). Based on:
// https://www.ssaurel.com/blog/create-a-simple-http-web-server-in-java
// http://www.jcgonzalez.com/java-socket-mini-server-http-example
public class WebServer {
  private static final Logger logger = LoggerFactory.getLogger(WebServer.class);

  private static final int PORT = 8080; // port to listen connection
  private static final int MAX_TOKENS = 20;
  private static final DateTimeFormatter FORMATTER =
          DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

  public WebServer() {
    try (ServerSocket serverConnect = new ServerSocket(PORT)) {
      logger.info("Server started. Listening for connections on port : "
              + PORT + " ...");

      // Listen for incoming connections until the server stops.
      while (true) {
        try {
          // Handle each client connection in a separate thread.
          new SocketThread(serverConnect.accept());
        } catch (IOException e) {
          logger.error("Error accepting client connection: " + e.getMessage());
          break;
        }
      }
    } catch (IOException e) {
      logger.error("Error starting or closing server: " + e.getMessage());
    }
  }

  private static class SocketThread extends Thread {
    // as an inner class, SocketThread sees WebServer attributes
    private final Socket inSocket; // client connection via Socket class

    SocketThread(Socket inSocket) {
      this.inSocket = inSocket;
      this.start();
    }

    @Override
    public void run() {
      // we manage our particular client connection
      BufferedReader in;
      PrintWriter out;
      String resource;

      try {
        // we read characters from the client via input stream on the socket
        in = new BufferedReader(new InputStreamReader(inSocket.getInputStream()));
        // we get character output stream to client
        out = new PrintWriter(inSocket.getOutputStream());
        // get first line of the request from the client
        String input = in.readLine();
        logger.debug("socketThread : " + input);

        // we parse the request with a string tokenizer
        StringTokenizer parse = new StringTokenizer(input);
        String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
        if (!method.equals("GET")) {
          logger.warn("501 Not Implemented : " + method + " method.");
        } else {
          // what comes after "localhost:8080"
          resource = parse.nextToken();
          logger.debug("input " + input);
          logger.debug("method " + method);
          logger.debug("resource " + resource);

          parse = new StringTokenizer(resource, "/[?]=&");
          int i = 0;
          String[] tokens = new String[MAX_TOKENS]; // more than the actual number of parameters
          while (parse.hasMoreTokens()) {
            tokens[i] = parse.nextToken();
            logger.debug(i + " " + tokens[i]);
            i++;
          }

          // Here is where we send the request and get the answer inside it
          Request request = makeRequest(tokens);
          if (request != null) {
            String typeRequest = tokens[0];
            logger.debug("created request " + typeRequest + " " + request);
            request.process();
            // info messages of this class are also saved in activity.log (see logback.xml)
            if (typeRequest.equals("refresh")) {
              logger.info("refresh");
            } else {
              logger.info("processed request " + typeRequest + " " + request);
            }
            // Make the answer as a JSON string, to be sent to the JavaScript client
            String answer = makeJsonAnswer(request);
            logger.debug("answer\n" + answer);
            // Here we send the response to the client
            out.println(answer);
            out.flush(); // flush character output stream buffer
          }
        }

        in.close();
        out.close();
        inSocket.close(); // we close socket connection
      } catch (Exception e) {
        logger.error("Exception : " + e);
      }
    }

    private Request makeRequest(String[] tokens) {
      // always return request because it contains the answer for the JavaScript client
      logger.debug("tokens : " + Arrays.toString(tokens));

      Request request;
      // assertions below evaluated to false won't stop the webserver, just print an
      // assertion error, maybe because the webserver runs in a socket thread
      switch (tokens[0]) {
        case "refresh":
          request = new RequestRefresh();
          break;
        case "reader":
          request = makeRequestReader(tokens);
          break;
        case "area":
          request = makeRequestArea(tokens);
          break;
        case "get_children":
          // TODO: this is to be implemented when programming the mobile app in Flutter
          // in order to navigate the hierarchy of partitions, spaces and doors
          assert false : "request get_children is not yet implemented";
          request = null;
          System.exit(-1);
          break;
        default:
          // just in case we change the user interface or the simulator
          assert false : "unknown request " + tokens[0];
          request = null;
          System.exit(-1);
      }

      return request;
    }

    private RequestReader makeRequestReader(String[] tokens) {
      String credential = tokens[2];
      String action = tokens[4];
      LocalDateTime dateTime = LocalDateTime.parse(tokens[6], FORMATTER);
      String doorId = tokens[8];
      return new RequestReader(credential, action, dateTime, doorId);
    }

    private RequestArea makeRequestArea(String[] tokens) {
      String credential = tokens[2];
      String action = tokens[4];
      LocalDateTime dateTime = LocalDateTime.parse(tokens[6], FORMATTER);
      String areaId = tokens[8];
      return new RequestArea(credential, action, dateTime, areaId);
    }

    private String makeHeaderAnswer() {
      String answer = "";
      answer += "HTTP/1.0 200 OK\r\n";
      answer += "Content-type: application/json\r\n";
      answer += "Access-Control-Allow-Origin: *\r\n";
      // SUPERIMPORTANT to avoid the CORS problem :
      // "Cross-Origin Request Blocked: The Same Origin Policy disallows reading
      // the remote resource..."
      answer += "\r\n"; // blank line between headers and content, very important !
      return answer;
    }

    private String makeJsonAnswer(Request request) {
      String answer = makeHeaderAnswer();
      answer += request.answerToJson().toString();
      return answer;
    }
  }
}