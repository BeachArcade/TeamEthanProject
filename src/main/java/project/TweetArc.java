package project;

import java.util.StringTokenizer;

public class TweetArc implements Arc {

  // TODO: Should this class contain the source?
  private String destVertex;
  // The number of times this tweet was retweeted
  private int strength;

  public TweetArc(String dest) {
    this.destVertex = dest;
    this.strength = 1;
  }

  @Override
  public String getTweetName() {
    return "";
  }

  @Override
  public String getVertex() {
    return destVertex;
  }

  @Override
  public int strength() {
    return strength;
  }



  public void setDestVertex(String destVertex) {
    this.destVertex = destVertex;
  }

  public int getStrength() {
    return strength;
  }

  public void increaseStrength() {
    setStrength(getStrength()+ 1);
  }

  public void setStrength(int i){
    strength = i;
  }

  public boolean equals(String that){
    return this.getVertex().equals(that);
  }
}
