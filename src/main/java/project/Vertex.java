package project;

import java.util.Objects;
//import java.util.StringTokenizer;

public class Vertex {

  private final String user;
  private int stance = 0;
  private final int hashCode;

  public Vertex(String tweetName) {
    this.user = tweetName;
    this.hashCode = Objects.hashCode(tweetName);
  }

  public String getName() {
    return user;
  }

  public int getStance() { return stance; }

  public void setStance( int x ){ this.stance = x; }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vertex that = (Vertex) o;
    return this.hashCode == that.hashCode;
  }

  @Override
  public int hashCode() {
    return this.hashCode;
  }
}



