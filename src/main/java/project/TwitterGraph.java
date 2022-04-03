package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*TODO: Add checks to see if the key exists
 *       Optimize loops
 *       Add invert() method
 *       Possibly make an addEdge() method
 *
 * */

// !default direction is Up
public class TwitterGraph implements Graph {
  private final HashMap<Vertex, List<TweetArc>> adjVertices = new HashMap<Vertex, List<TweetArc>>();
  private final boolean direction = true; // true == up

  public TwitterGraph() throws FileNotFoundException {
    Reader userReader = new Reader(new File("VaxData/100VaxUsersTweets.txt"));
    Reader tweetReader = new Reader(new File("VaxData/100VaxTweets.txt"));

    userReader.populateUsers(this);
    tweetReader.populateArcs(this);
    System.out.println(this);
  }

  @Override
  public void getVertex(Vertex user) {
    System.out.println("");
  }

  @Override
  // Return all edges of a given vertex
  public List<TweetArc> getEdges(Vertex user) {
    return adjVertices.get(user);
  }

  @Override
  // Make vertexes for all the
  public Graph invert() {
    HashMap<Vertex, List<TweetArc>> invertMap = new HashMap<>();
    //Get retweeters
    return null;

  }

  /** Maps a user with its retweets */
  @Override
  public void add(Vertex user, List<?> retweets) {
    adjVertices.put(user, (List<TweetArc>) retweets);
  }

  /** Overloaded method to map a single retweet */
  public void add(String userStr, String retweetStr) {
    Vertex user = new Vertex(userStr);
    TweetArc retweet = new TweetArc(retweetStr);
    // if the user doesnt exist create new entry
    if (!adjVertices.containsKey(user)) {
      add(userStr);
    }
    // if the retweeted user does not exist create new entry
    if (!adjVertices.containsKey(new Vertex(retweetStr))) {
      add(retweetStr);
    }
    /* is retweeter in the user's arcs?
        Yes? increase strength.
        No? add it to the empty list
    */
    if (adjVertices.get(user).contains(retweet)) {
      adjVertices.get(user).get(adjVertices.get(user).indexOf(retweet)).increaseStrength();
    } else {
      adjVertices.get(user).add(retweet);
    }
  }
  /** Adds a single vertex to the hashmap with an empty list of Arcs to the graph */
  public void add(String user) {
    if (!adjVertices.containsKey(new Vertex(user))) {
      adjVertices.put(new Vertex(user), new ArrayList<>());
    }
  }

  @Override
  public void remove(Vertex user) {
    adjVertices.remove(user);
  }


  /* Format:
     retweeter{retweeted users,...}
  */
  public String toString() {
    String str = "";
    for (Map.Entry<Vertex, List<TweetArc>> entry : adjVertices.entrySet()) {
      str += entry.getKey().getName() + "\t{";
      for (Arc arc : entry.getValue()) {
        str += arc.getVertex() + ",";
      }
      str += "}\n";
    }
    return str;
  }
}
