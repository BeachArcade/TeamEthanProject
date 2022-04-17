package project.Vertexes;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.StringTokenizer;

public class Vertex implements Comparable {

  public HashMap<String, Integer> getListOfHashtags() {
    return listOfHashtags;
  }

  public void setListOfHashtags(HashMap<String, Integer> listOfHashtags) {
    this.listOfHashtags = listOfHashtags;
  }

  private final String user;
  private final int hashCode;
  private int retweetStance = 0;

  public int getHashtagStance() {
    return hashtagStance;
  }

  public void setHashtagStance(int hashtagStance) {
    this.hashtagStance = hashtagStance;
  }

  private int hashtagStance = 0;
  private int retweetNum = 0;

  // <Hashtag, Count>
  HashMap<String, Integer> listOfHashtags = new HashMap<>();

  public Vertex(String tweetName) {
    this.user = tweetName;
    this.hashCode = Objects.hashCode(tweetName);
  }

  public String getData() {
    return user;
  }

  public int getRetweetStance() {
    return retweetStance;
  }

  public int getCalcRetweetStance(){
    if(getRetweetNum()!=0) {
      return retweetStance / getRetweetNum();
    }
    return 0;
  }

  private int getCalcHashtagStance(){
    if (!listOfHashtags.isEmpty()){
      return hashtagStance /getListOfHashtagsCount();
    }
    return 0;
  }

  private int getListOfHashtagsCount(){
    AtomicInteger count = new AtomicInteger();
    listOfHashtags.values().forEach(count::addAndGet);
    return count.get();
  }

  public int getCalculatedStance(){
    int stance = 0;
    stance += getCalcRetweetStance();
    stance+=getCalcHashtagStance();
    return stance;
  }

  public void setRetweetStance(int x) {
    this.retweetStance = x;
  }

  public void changeRetweetStance(int change) {
    this.setRetweetStance(this.getRetweetStance() + change);
  }

  public void changeHashtagStance(int change){
    this.setHashtagStance(this.getHashtagStance()+change);
  }

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

  public void addHashtag(String hashtag){
    if (listOfHashtags.containsKey(hashtag)){
      listOfHashtags.replace(hashtag,listOfHashtags.get(hashtag), listOfHashtags.get(hashtag)+1);
    } else {
      listOfHashtags.put(hashtag, 1);
    }
  }

  public String getName(){
    return this.user;
  }
}



