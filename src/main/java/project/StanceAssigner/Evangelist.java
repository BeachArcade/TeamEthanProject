package project.StanceAssigner;

import project.Reader;
import project.TweetArc;
import project.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Evangelist extends Vertex {
    private int stance;
    private ArrayList<String> tweets;
    public Evangelist(String tweetName) {
        super(tweetName);
        stance = 0;
    }
    public void setStance(int n){
        stance = n;
    }

    public String toString(){
        return getName() + ", " + stance;
    }
    public void findTweets() throws FileNotFoundException {
        Reader read = new Reader(new File("VaxData/vax tweets.txt"));
        tweets = read.getTweets(this);
    }
    public ArrayList<String> getTweets(){
        return tweets;
    }
}

