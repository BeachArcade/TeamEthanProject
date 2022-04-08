package project.StanceAssigner;

import project.Reader;
import project.TweetArc;
import project.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Evangelist extends Vertex {
    private int stance;
    private ArrayList<TweetArc> tweets;
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
    public void getTweets() throws FileNotFoundException {
        Reader read = new Reader(new File("Vax Data/vax tweets.txt"));

    }
}

