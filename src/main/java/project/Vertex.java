package project;

/**
 * Vertex class
 *
 * @author Ethan Hammond
 * @version 1.0
 */

public class Vertex{

    private String name = null;
    private String content = null;

    public Vertex(String tweetName, String tweetContent) {
        this.name = tweetName;
        this.content = tweetContent;
    }

    public String getName(){
        return name;
    }

    public String getContent(){
        return content;
    }
}
