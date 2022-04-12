package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
/*TODO: Add checks to see if the key exists
 *       Optimize loops
 *       Add invert() method
 *       Possibly make an addEdge() method
 *
 * */

// !default direction is Up
public class TwitterGraph implements Graph {
  private HashMap<Vertex, List<TweetArc>> adjVertices = new HashMap<Vertex, List<TweetArc>>();
  private boolean direction = true; // true == up

  // Hashmap of evangelists, positive integer for pro-vax, negative integer for anti-vax
  private HashMap<String, Integer> evangelists = new HashMap<String, Integer>() {
    {

      // Add keys and values (username, stance)
      put("@BernieSpofforth", -1);
      put("@JamesMelville", -1);
      put("@jackjohnsoniv7", 1);
      put("@GillianMcKeith", -1);
      put("@RealCandaceO", -1);
      put("@EssexPR", -1);
      put("@alanvibe", -1);
      put("@ginacarano", -1);
      put("@Togetherdec", -1);
      put("@catturd2", -1);
      put("@org_scp", -1);
      put("@BananaMediaQ", -1);
      put("@EU_Commission", 1);
      put("@JamesfWells", -1);
      put("@LozzaFox", -1);
      put("@djlange", -1);
      put("@gbrough10", -1);
      put("@greenhousemd", 1);
      put("@TonyHinton2016", -1);
      put("@CaoimhinFachtna", -1);
      put("@atensnut", -1);
      put("@ToniaBuxton", -1);
      put("@RealJamesWoods", -1);
      put("@profnfenton", -1);
      put("@VigilantFox", -1);
      put("@Karenlovecheese", 1);
      put("@mrjamesob", 1);
      put("@HumanRights4UK", -1);
      put("@PeterDooleyDUB", 1);
      put("@BKarahalios", -1);
      put("@DrHoenderkamp", -1);
      put("@RWMaloneMD", -1);
      put("@davidkurten", -1);
      put("@kernaghanscott5", -1);
      put("@danjgregory", -1);

    }
  };

  public TwitterGraph() throws FileNotFoundException {
    Reader userReader = new Reader(new File("VaxData/vax tweets users.txt"));
    Reader tweetReader = new Reader(new File("VaxData/vax tweets.txt"));

    userReader.populateUsers(this);
    tweetReader.populateArcs(this);

    System.out.println("Graph successfully loaded");
  }

  public TwitterGraph(File input) throws FileNotFoundException {
    Reader reader = new Reader(input);
    reader.loadGraph(this);

  }
  public HashMap<Vertex, List<TweetArc>> getAdjVertices() {
    return adjVertices;
  }

  public boolean getDirection() {
    return direction;
  }

  @Override
  public void getVertex(Vertex user) {}

  @Override
  // Return all edges of a given vertex
  public List<TweetArc> getEdges(Vertex user) {
    return adjVertices.get(user);
  }

  @Override
  // Make vertexes for all the
  public void invert() {
    System.out.println("Inverting");
    HashMap<Vertex, List<TweetArc>> invertMap = new HashMap<>();
    // add all the users from original map
    for (Map.Entry<Vertex, List<TweetArc>> entry : adjVertices.entrySet()) {
      invertMap.put(entry.getKey(), new ArrayList<>());
    }
    // get retweeters
    for (Map.Entry<Vertex, List<TweetArc>> entry : adjVertices.entrySet()) {
      Vertex user = entry.getKey();
      // Make sure list isnt empty
      if (entry.getValue().size() > 0) {
        for (TweetArc arc : entry.getValue()) {
          // swap arc and vertex
          Vertex newUser = new Vertex(arc.getVertex());
          TweetArc newArc = new TweetArc(user.getName());
          newArc.setStrength(arc.getStrength());
          if (!entry.getValue().contains(newArc))
           invertMap.get(newUser).add(newArc);
           Collections.sort(invertMap.get(newUser));
        }
      }
    }
    adjVertices = invertMap;
    direction = !direction;
  }

  /**
   * Maps a user with its retweets
   */
  @Override
  public void add(Vertex user, List<?> retweets) {
    Collections.sort((List<TweetArc>) retweets);
    adjVertices.put(user, (List<TweetArc>) retweets);
  }

  /**
   * Overloaded method to map a single retweet
   */
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
      Collections.sort(adjVertices.get(user));
    }
  }

  /**
   * Adds a single vertex to the hashmap with an empty list of Arcs to the graph
   */
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
      for (TweetArc arc : entry.getValue()) {
        str += arc.getVertex() + " " + arc.getStrength() + ",";
      }
      str += "}\n";
    }
    return str;
  }

  //Strip non retweeters
  public void stripNonRetweeters(){
    for(Map.Entry<Vertex,List<TweetArc>> entry: adjVertices.entrySet()){
      if(entry.getValue().size() < 1){
        adjVertices.remove(entry.getKey());
      }
    }
  }
  public void sort(){
    ArrayList<Vertex> list = new ArrayList<>();
    int count = 0;
    if (direction) {
      System.out.println("Not Inverted: Inverting now");
      invert();
      }
    for(Map.Entry<Vertex, List<TweetArc>> entry: adjVertices.entrySet()){
      entry.getKey().setRetweetNum(entry.getValue().size());
      list.add(entry.getKey());
    }
    Collections.sort(list);
  }

  //Overloaded sort to return
  public ArrayList<Vertex> getEvangelists(int n){
    ArrayList<Vertex> list = new ArrayList<>();
    int count = 0;
    if (direction) {
      System.out.println("Not Inverted: Inverting now");
      invert();
    }
    for(Map.Entry<Vertex, List<TweetArc>> entry: adjVertices.entrySet()){
      entry.getKey().setRetweetNum(entry.getValue().size());
      list.add(entry.getKey());
    }
    Collections.sort(list);

    ArrayList<Vertex> newList= new ArrayList<>();
    for(int i = 0; i < n; i++){
      newList.add(list.get(i));
    }

    return newList;
  }
}

/*
 *  public void percolate(Vertex[] evangelists)
 *    go thru hashmap and get the values of all evangelists
 *      add stance of evangelist to retweeter (Stance += stance/retweet#)
 *
 *
 */




