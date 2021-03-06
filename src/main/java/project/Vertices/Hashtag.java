package project.Vertices;

import java.util.Set;

import project.Lexicon;

import java.util.Objects;

public class Hashtag {
  private final String tagName;
  private final int hashCode;
  private int stance = 0;
  private int numOfTweets = 0;
  private Set<Tag> tags;

  public Hashtag(String hashtag) {
    this.tagName = hashtag;
    this.hashCode = Objects.hashCode(hashtag);
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
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

  public boolean contains(Tag tag) {
    return this.getName().toLowerCase().contains(tag.getName().toLowerCase());
  }

  public void calculateContainedTags(Lexicon lexicon) {
    for (Tag tag : lexicon.getTags()) if (this.contains(tag)) this.tags.add(tag);
  }

  public int getCalculatedStance() {
    int tweets = this.getNumOfTweets();
    if (tweets == 0) return this.stance;
    return (int)(tags.stream().mapToInt(Tag::getStance).sum() / (double)tweets);
  }

  public void changeStance(int change) {
    this.setStance(this.getStance() + change);
  }


  public int getNumOfTweets() {
    return this.numOfTweets;
  }

  public void setNumOfTweets(int n) {
    this.numOfTweets = n;
  }

  public void increaseTweetNum() {
    setNumOfTweets(numOfTweets + 1);
  }

  public void changeNumOfTweets(int n) {
    this.setNumOfTweets(this.getNumOfTweets() + n);
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
