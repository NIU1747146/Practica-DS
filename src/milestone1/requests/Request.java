package milestone1.requests;

import org.json.JSONObject;

// Request.java
// Common interface of the three kinds of request (refresh, reader, area), so WebServer creates
// one, calls process() and asks for its JSON without knowing which kind it is.
public interface Request {
  JSONObject answerToJson();

  String toString();

  void process();
}
