package project.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import project.Graphs.HashtagGraph;
import project.Graphs.TwitterGraph;
import project.Lexicon;
import project.Vertexes.Hashtag;
import project.Vertexes.Vertex;

public class Reader {

  BufferedReader bufferedReader;
  StringTokenizer stringTokenizer;

  public Reader(File file) throws FileNotFoundException {
    bufferedReader = new BufferedReader(new FileReader(file));
  }

  public String next() {
    while (stringTokenizer == null || !stringTokenizer.hasMoreElements()) {
      try {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), "\t");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return stringTokenizer.nextToken();
  }


  public long nextLong() {
    return Long.parseLong(next());
  }

  public String nextString() {
    return next();
  }

  public String nextLine() {
    String str = "";
    try {
      str = bufferedReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  /**
   * This reads the vax users file and creates the vertices in the graph
   */
  public void populateUsers(TwitterGraph twitterGraph) {
    String line;
    while ((line = nextLine()) != null) {
      String user = line.split("\t")[0];
      twitterGraph.add(user);
      String[] tweetContent;
      if (line.split("\t")[2].contains(":")) {
        tweetContent = line.split("\t")[2].split(":")[1].split(" ");
        for (String word : tweetContent) {
          if (word.startsWith("#")) {
            twitterGraph.addHashtag(new Hashtag(word)); // Add hashtag to Graph
            twitterGraph.getVertex(new Vertex(user)).addHashtag(word); // Add hashtag to user
          }
        }
      }
    }
  }

  /**
   * This reads the vax tweets file and creates the arcs between vertices
   */
  public void populateArcs(TwitterGraph twitterGraph) {
    String line;
    while ((line = nextLine()) != null) {
      if (lineCheck(line)) {
        String user = line.split("\t")[1];
        String content = line.split("\t")[2];
        if (content.startsWith("RT")) {
          String retweededUser = content.split(":", 0)[0];
          if (retweededUser.length() > 3) {
            retweededUser = retweededUser.substring(3);
            twitterGraph.add(user, retweededUser);
          } else {
            twitterGraph.add(user);
          }
        } else {
          // if there is no RT, just add new tweet to graph
          twitterGraph.add(user);
        }
      }
    }
  }

  private boolean lineCheck(String line) {
    String[] split = line.split("\t");
    return split.length > 2;
  }

  /**
   * This reads a previously saved graph file and then passes the input to create a graph
   */
  public void loadTwitterGraph(TwitterGraph twitterGraph) {
    String line;
    while ((line = nextLine()) != null) {
      String[] str = line.split("\t");
      List<String> list = Arrays.asList(str[2].replace("{", "").replace("}", "").split(","));
      twitterGraph.add(new Vertex(str[0]), Integer.parseInt(str[1]), list);
    }
  }

  public ArrayList<String> getTweets(Vertex user) {
    String line, userStr;
    ArrayList<String> list = new ArrayList<>();
    while ((line = nextLine()) != null) {
      if (line.contains(user.getData())) {
        //Tokenize and strip irrelevant data
        String[] arr = line.split("\t");
        if (arr[2].length() > 2) {
          if (!arr[2].startsWith("RT")) {
            list.add(arr[1] + arr[2]);
          }
        }
      }
    }
    return list;
  }

  public ArrayList<Vertex> getHashtags(Hashtag hashtag) {
    ArrayList<Vertex> list = new ArrayList<>();
    String line;
    while ((line = nextLine()) != null) {
      line = line.toLowerCase();
      if (line.contains(hashtag.getName().toLowerCase())) {
        Vertex user = parseUser(line);
        // TODO fix this!
        if (!list.contains(user) && user != null) {
          list.add(user);
        }
        hashtag.increaseTweetNum();
      }
    }
    return list;
  }

    public void loadHashtagGraph(HashtagGraph hashtagGraph) {

      /*

      TODO: Fit to hashtag graph format

        String line;
        while ((line = nextLine()) != null) {
            String[] str = line.split("\t");
            List<String> list = Arrays.asList(str[2].replace("{", "").replace("}", "").split(","));
            hashtagGraph.add(new Hashtag(str[0]), Integer.parseInt(str[1]), list);
        }

       */
    }

  private Vertex parseUser(String tweet) {
    if(!tweet.startsWith("1")) return null;
    String[] tweetArr = tweet.split("\t");
    if (tweetArr.length < 1)
      return null;
    return new Vertex(tweetArr[1]);
  }

  public HashMap<Hashtag, ArrayList<Vertex>> loadHashtagGraph(){
    HashMap<Hashtag, ArrayList<Vertex>> graph = new HashMap<>();
    String line;
    while((line = nextLine()) != null){
      String[] split = line.split("\t");
      Hashtag h = new Hashtag(split[0]);
      ArrayList<Vertex> list = new ArrayList<>();
      String[] allUsers =  split[1].split(",");
      for(String userStr : allUsers){
        Vertex user = new Vertex(userStr);
        if(!list.contains(user)){
          list.add(user);
        }
      }
      graph.put(h,list);
    }
    return graph;
  }

  public void loadLexicon(Lexicon lexicon){
    String line;
    while((line = nextLine()) != null){
      String tag = line.split(" ", 2)[1].split(" ",2)[0];
      String references = line.split(" ", 2)[1].split(" ",2)[1].replace("[", "").replace("]","");
      lexicon.addTag(tag,references);
    }
  }

}

