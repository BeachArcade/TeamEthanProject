package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

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
      String[] tweetContent = line.split("\t")[2].split(":")[1].split(" ");
      for (String word : tweetContent) {
        if (word.startsWith("#")) {
          twitterGraph.addHashtag(new Hashtag(word)); // Add hashtag to Graph
          twitterGraph.getVertex(new Vertex(user)).addHashtag(word); // Add hashtag to user
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
  public void loadGraph(TwitterGraph twitterGraph) {
    String line;
    while ((line = nextLine()) != null) {
      String[] str = line.split("\t");
      List<String> list = Arrays.asList(str[2].replace("{", "").replace("}", "").split(","));
      twitterGraph.add(new Vertex(str[0]), Integer.parseInt(str[1]), list);
    }
  }

  public ArrayList<String> getTweets(Vertex user){
    String line, userStr;
    ArrayList<String> list = new ArrayList<>();
    while((line = nextLine()) != null){
      if(line.contains(user.getName())){
        list.add(line);
      }
    }
    return list;
  }
}

