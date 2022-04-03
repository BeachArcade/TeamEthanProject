package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    }
  }

  /**
   * This reads the vax tweets file and creates the arcs between vertices
   */
  public void populateArcs(TwitterGraph twitterGraph) {
    String line;
    while ((line = nextLine()) != null) {
      String user = line.split("\t")[1];
      String content = line.split("\t")[2];
      if (content.startsWith("RT")) {
        String retweededUser = content.split(":", 0)[0];
        retweededUser = retweededUser.substring(3);
        //TODO call method from TwitterGraph
      } else {
        //if there is no RT, just add new tweet to graph
        twitterGraph.add(user);
      }
    }

  }


}

