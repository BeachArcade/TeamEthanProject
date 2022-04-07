package project;

import java.util.Objects;
//import java.util.StringTokenizer;

public class Vertex implements Comparable{

  private final String user;
  private int stance = 0;
  private final int hashCode;
  private int retweetNum;

  public Vertex(String tweetName) {
    this.user = tweetName;
    this.hashCode = Objects.hashCode(tweetName);
  }

  public String getName() {
    return user;
  }

  public int getStance() { return stance; }

  public void setStance( int x ){ this.stance = x; }

  public void changeStance( int change ) { this.setStance( this.getStance() + change); }

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

  public void setRetweetNum(int n){
    this.retweetNum = n;
  }
  public int getRetweetNum(){
    return retweetNum;
  }
  public int compareTo(Vertex genericThat) {
    if(genericThat.retweetNum > this.retweetNum) return 1;
    else if (genericThat.retweetNum < this.retweetNum) return -1;
    else return 0;
  }

  @Override
  public int compareTo(Object o) {
    if(o.getClass().equals(this.getClass())){
      return this.compareTo((Vertex) o);
    }
    return 0;
  }
}



