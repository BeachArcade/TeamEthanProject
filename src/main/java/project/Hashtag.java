package project;

public class Hashtag {

    private final String tagName;
    private int stance = 0;
    private int numOfTweets = 0;

    public Hashtag(String hashtag) {
        this.tagName = hashtag;
    }

    public String getName() {
        return tagName;
    }

    public int getStance() {
        return stance;
    }

    public int getCalculatedStance(){
        return stance/getNumOfTweets();
    }

    public void setStance(int x) {
        this.stance = x;
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
        if (genericThat.numOfTweets > this.numOfTweets) {
            return 1;
        } else if (genericThat.numOfTweets < this.numOfTweets) {
            return -1;
        } else {
            return 0;
        }
    }

}
