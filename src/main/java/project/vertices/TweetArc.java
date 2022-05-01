package project.vertices;

import java.util.Objects;

public class TweetArc implements Arc {

  // TODO: Should this class contain the source?
  private int hashCode;
  private String destVertex;
  // The number of times this tweet was retweeted
  private int strength;

  public TweetArc(String dest) {
    this.destVertex = dest;
    this.strength = 1;
    this.hashCode = Objects.hashCode(dest);
  }

  public TweetArc(String dest, int strength) {
    this.destVertex = dest;
    this.strength = strength;
    this.hashCode = Objects.hashCode(dest);
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

  public void setStrength(int i) {
    strength = i;
  }

  public void increaseStrength() {
    setStrength(getStrength() + 1);
  }

  public boolean equals(String that) {
    return this.getVertex().equals(that);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TweetArc that = (TweetArc) o;
    return this.hashCode == that.hashCode;
  }

  @Override
  public int hashCode() {
    return this.hashCode;
  }

  @Override
  public int compareTo(Arc genericThat) {
    if (genericThat.getClass().equals(this.getClass())) {
      TweetArc that = (TweetArc) genericThat;
      return Integer.compare(this.getStrength(), that.getStrength());
    }
    return -2;
  }
}
