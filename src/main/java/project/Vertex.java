package project;

import java.util.Objects;
import java.util.StringTokenizer;

public class Vertex {
  private String user;
  private int hashCode;

  public Vertex(String tweetName) {
    this.user = tweetName;
    this.hashCode = Objects.hashCode(tweetName);
  }

    public String getName() {
      return user;
    }

    @Override
    public int hashCode(){
      return this.hashCode;
    }
  }



