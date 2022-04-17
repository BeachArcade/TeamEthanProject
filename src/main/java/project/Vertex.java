package project;

import java.util.Objects;
import java.util.ArrayList;
//import java.util.StringTokenizer;

public class Vertex implements Comparable {

  private final String user;
  private final int hashCode;
  private int stance = 0;
  private int retweetNum = 1;
  ArrayList<Hashtag> listOfHashtags = new ArrayList<>();

  public Vertex(String tweetName) {
    this.user = tweetName;
    this.hashCode = Objects.hashCode(tweetName);
  }

  public String getName() {
    return user;
  }

  public int getStance() {
    return stance;
  }

  public int getCalculatedStance(){
    return stance/getRetweetNum();
  }

  public void setStance(int x) {
    this.stance = x;
  }

  public void changeStance(int change) {
    this.setStance(this.getStance() + change);
  }

  public ArrayList getListOfHashtags() { return listOfHashtags; }

  public void setHashtagList ( ArrayList newListOfHashes) {this.listOfHashtags = newListOfHashes;}

  public void addHashtagToList( Hashtag newHash) { this.listOfHashtags.add(newHash);}

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

  public int getRetweetNum() {
    return retweetNum;
  }

  public void setRetweetNum(int n) {
    this.retweetNum = n;
  }

  public int compareTo(Vertex genericThat) {
    return Integer.compare(genericThat.retweetNum, this.retweetNum);
  }

  @Override
  public int compareTo(Object o) {
    if (o.getClass().equals(this.getClass())) {
      return this.compareTo((Vertex) o);
    }
    return 0;
  }

  public void addHashtag(Hashtag hashtag){
    if(!listOfHashtags.contains(hashtag)){
      hashtag.setNumOfTweets(1);
      listOfHashtags.add(hashtag);
    } else {
      listOfHashtags.get(listOfHashtags.indexOf(hashtag)).setNumOfTweets(listOfHashtags.get(listOfHashtags.indexOf(hashtag)).getNumOfTweets() +1);
    }
  }

}



