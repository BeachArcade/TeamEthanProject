package project.Vertexes;

public class Hashtag implements Comparable{

    private final String tagName;
    private int stance = 0;
    private int numOfTweets = 0;

    public Hashtag(String hashtag) {
        this.tagName = hashtag;
    }

    public String getData() {
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

    public void increaseTweetNum(){
        setNumOfTweets(numOfTweets + 1);
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

    @Override
    public int compareTo(Object o) {
        if(!o.getClass().equals(this.getClass()))
            return 0;
        else
            return this.compareTo((Hashtag)o);
    }
}
