package milestone1.requests;

import org.json.JSONObject;

// Request sent by the simulator to the ACU.
public interface Request {
  JSONObject answerToJson();

  String toString();

  void process();
}
