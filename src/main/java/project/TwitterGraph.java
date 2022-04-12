package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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

  // Hashmap of evangelists, positive integer for pro-vax, negative integer for anti-vax
  private final HashMap<String, Integer> evangelists = new HashMap<String, Integer>() {
    {
      // Add keys and values (username, stance)
      put("@BernieSpofforth", -1000);
      put("@JamesMelville", -1000);
      put("@jackjohnsoniv7", 1000);
      put("@GillianMcKeith", -1000);
      put("@RealCandaceO", -1000);
      put("@EssexPR", -1000);
      put("@alanvibe", -1000);
      put("@ginacarano", -1000);
      put("@Togetherdec", -1000);
      put("@catturd2", -1000);
      put("@org_scp", -1000);
      put("@BananaMediaQ", -1000);
      put("@EU_Commission", 1000);
      put("@JamesfWells", -1000);
      put("@LozzaFox", -1000);
      put("@djlange", -1000);
      put("@gbrough10000", -1000);
      put("@greenhousemd", 1000);
      put("@TonyHinton2010006", -1000);
      put("@CaoimhinFachtna", -1000);
      put("@atensnut", -1000);
      put("@ToniaBuxton", -1000);
      put("@RealJamesWoods", -1000);
      put("@profnfenton", -1000);
      put("@VigilantFox", -1000);
      put("@Karenlovecheese", 1000);
      put("@mrjamesob", 1000);
      put("@HumanRights4UK", -1000);
      put("@PeterDooleyDUB", 1000);
      put("@BKarahalios", -1000);
      put("@DrHoenderkamp", -1000);
      put("@RWMaloneMD", -1000);
      put("@davidkurten", -1000);
      put("@kernaghanscott5", -1000);
      put("@danjgregory", -1000);
      put("@HPVSideEffects", -1000);
      put("@WhiteCoatWaste", -1000);
      put("@mysteriouskat", 0);
      put("@Eric_Schmitt", -1000);
      put("@EliseiNicole", -1000);
      put("@chiproytx", -1000);
      put("@RefusenikExmoor", -1000);
      put("@SolNataMD", 1000);
      put("@JuliaHB1", -1000);
      put("@SteveBakerHW", -1000);
      put("@MaajidNawaz", -1000);
      put("@Surabees", -1000);
      put("@JT4USA", -1000);
      put("@Jemmapalmer", 1000);
      put("@SebGorka", -1000);
      put("@michaelmalice", -1000);
      put("@EricMMatheny", -1000);
      put("@ian_charles007", 1000);
      put("@Femi_Sorry", -1000);
      put("@beverleyturner", -1000);
      put("@laworfiction", -1000);
      put("@Valkyrie20201", -1000);
      put("@TomthunkitsMind", 1000);
      put("@RWTaylors", -1000);
      put("@PapiTrumpo", -1000);
      put("@RpsAgainstTrump", 1000);
      put("@realDailyWire", -1000);
      put("@castterry73", -1000);
      put("@CarlosSimancas", -1000);
      put("@ChrisLoesch", -1000);
      put("@ProtestNews_EN", -1000);
      put("@Andy_In_The_UK", -1000);
      put("@TheDemCoalition", 1000);
      put("@bmay", 1000);
      put("@TheVoxWolf", -1000);
      put("@GeorginaLishma1", -1000);
      put("@Arwenstar", -1000);
      put("@parisofprairie", 1000);
      put("@Zieleds", -1000);
      put("@ProfessorFergu1", -1000);
      put("@MattHoyOfficial", -1000);
      put("@DrNeilStone", 1000);
      put("@libertytarian", -1000);
      put("@BuffyWicks", 1000);
      put("@AmerAcadPeds", 1000);
      put("@CarymaRules", 1000);
      put("@taradublinrocks", 1000);
      put("@NeilClark66", -1000);
      put("@awareness_4all", -1000);
      put("@bblock29", 1000);
      put("@marcowenjones", 1000);
      put("@mommamia1217", 1000);
      put("@DrMadej", -1000);
      put("@Lenabellalou", -1000);
      put("@McGiff", -1000);
      put("@NeverSleever", -1000);
      put("@zoeharcombe", -1000);
      put("@JeffreyPeel", -1000);
      put("@AngelaBelcamino", -1000);
      put("@BetteMidler", -1000);
      put("@Rene4D45", -1000);
      put("@BorisJohnson_MP", 1000);
      put("@TheRightMelissa", -1000);
      put("@Belondyy", -1000);
      put("@richardcings", -1000);
    }
  };
  private HashMap<Vertex, List<TweetArc>> adjVertices = new HashMap<>();
  private boolean direction = true; // true == up

  public TwitterGraph() throws FileNotFoundException {
    Reader userReader = new Reader(new File("VaxData/vax tweets users.txt"));
    Reader tweetReader = new Reader(new File("VaxData/vax tweets.txt"));
//    Reader userReader= new Reader(new File("VaxData/100VaxUsersTweets.txt"));
//    Reader tweetReader = new Reader(new File("VaxData/100VaxTweets.txt"));

    userReader.populateUsers(this);
    tweetReader.populateArcs(this);

    percolate();

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
  public void getVertex(Vertex user) {
  }

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
          if (!entry.getValue().contains(newArc)) {
            invertMap.get(newUser).add(newArc);
          }
          Collections.sort(invertMap.get(newUser));
        }
      }
    }
    adjVertices = invertMap;
    direction = !direction;
  }

  public void add(Vertex user, int stance, List<?> retweets){
    user.setStance(stance);
    add(user, retweets);
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
    // if the user doesn't exist create new entry
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
    StringBuilder str = new StringBuilder();
    for (Map.Entry<Vertex, List<TweetArc>> entry : adjVertices.entrySet()) {
      str.append(entry.getKey().getName()).append("\t{");
      for (TweetArc arc : entry.getValue()) {
        str.append(arc.getVertex()).append(" ").append(arc.getStrength()).append(",");
      }
      str.append("}\n");
    }
    return str.toString();
  }

  //Strip non retweeters
  public void stripNonRetweeters() {
    for (Map.Entry<Vertex, List<TweetArc>> entry : adjVertices.entrySet()) {
      if (entry.getValue().size() < 1) {
        adjVertices.remove(entry.getKey());
      }
    }
  }

  public void sort() {
    ArrayList<Vertex> list = new ArrayList<>();
    int count = 0;
    if (direction) {
      System.out.println("Not Inverted: Inverting now");
      invert();
    }
    for (Map.Entry<Vertex, List<TweetArc>> entry : adjVertices.entrySet()) {
      entry.getKey().setRetweetNum(entry.getValue().size());
      list.add(entry.getKey());
    }
    Collections.sort(list);
  }

  //Overloaded sort to return
  public ArrayList<Vertex> getEvangelists(int n) {
    ArrayList<Vertex> list = new ArrayList<>();
    int count = 0;
    if (direction) {
      System.out.println("Not Inverted: Inverting now");
      invert();
    }
    for (Map.Entry<Vertex, List<TweetArc>> entry : adjVertices.entrySet()) {
      entry.getKey().setRetweetNum(entry.getValue().size());
      list.add(entry.getKey());
    }
    Collections.sort(list);

    ArrayList<Vertex> newList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      newList.add(list.get(i));
    }

    return newList;
  }

  /**
   * This method percolates the evangelists stances through the graph
   */
  public void percolate() {
    for (int i = 0; i < 4; i++) {
      for (Map.Entry<Vertex, List<TweetArc>> entry : adjVertices.entrySet()) {
        // Check if the vertex belongs to an evangelist;
        if (evangelists.containsKey(entry.getKey().getName())) {
          entry.getKey().setStance(evangelists.get(entry.getKey().getName()));
        } else {
          // Update the stance and the retweet num for each arc
          for (TweetArc tweetArc : entry.getValue()) {
            Vertex destVertex = adjVertices.keySet().stream()
                .filter(vertex -> vertex.equals(new Vertex(tweetArc.getVertex()))).findFirst()
                .get();
            entry.getKey().changeStance(destVertex.getStance() * tweetArc.getStrength());
            entry.getKey().setRetweetNum(entry.getKey().getRetweetNum() + tweetArc.getStrength());
          }
        }
      }
    }
  }

}

/*
 *  public void percolate(Vertex[] evangelists)
 *    go thru hashmap and get the values of all evangelists
 *      add stance of evangelist to retweeter (Stance += stance/retweet#)
 */





