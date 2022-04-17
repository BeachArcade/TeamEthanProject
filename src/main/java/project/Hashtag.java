package project;

import java.util.Objects;

public class Hashtag {

  private final String tagName;
  private final int hashCode;
  private int stance = 0;
  private int numOfTweets = 0;

  public Hashtag(String hashtag) {
    this.tagName = hashtag;
    this.hashCode = Objects.hashCode(hashtag);
  }

  public String getName() {
    return tagName;
  }

  public int getStance() {
    return stance;
  }

  public void setStance(int x) {
    this.stance = x;
  }

  public int getCalculatedStance() {
    return stance / getNumOfTweets();
  }

  public void changeStance(int change) {
    this.setStance(this.getStance() + change);
  }


  public int getNumOfTweets() {
    return numOfTweets;
  }

  public void setNumOfTweets(int n) {
    this.numOfTweets = n;
  }

  public int compareTo(Hashtag genericThat) {
    return Integer.compare(genericThat.numOfTweets, this.numOfTweets);
  }

  public int compareTo(Object o) {
    if (o.getClass().equals(this.getClass())) {
      return this.compareTo((Hashtag) o);
    }
    return 0;
  }

  @Override
  public int hashCode() {
    return this.hashCode;
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hashtag that = (Hashtag) o;
    return this.hashCode == that.hashCode;
  }

}