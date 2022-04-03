package project;

import java.util.StringTokenizer;

public class TweetArc implements Arc {

  // TODO: Should this class contain the source?
  private Vertex destVertex;
  // The number of times this tweet was retweeted
  private int strength;

  public TweetArc(String content) {}

  @Override
  public String getTweetName() {
    return "";
  }

  @Override
  public Vertex getVertex() {
    return destVertex;
  }

  @Override
  public int strength() {
    return strength;
  }

  public void increaseStrength() {
    strength++;
  }
}
