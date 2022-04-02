package project;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Vertex {
    private int id;
    private String name;
    private String content;

    public Vertex(String tweetName, String tweetContent) {
        this.name = tweetName;
        this.content = tweetContent;
    }
    //Overload constructor for full tweet
    public Vertex(String tweet){
        StringTokenizer tokenizer = new StringTokenizer(tweet, ("\t"));
        //Skip ID
        tokenizer.nextToken();
        name = tokenizer.nextToken();
        content = tokenizer.nextToken();
    }
    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
