package project.Graphs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import project.IO.Reader;
import project.IO.Writer;
import project.Vertexes.Hashtag;
import project.Vertexes.Vertex;

public class HashtagGraph {

  private final ArrayList<Hashtag> hashtags = new ArrayList<>();
  private final ArrayList<Vertex> users = new ArrayList<>();
  private HashMap<Hashtag, ArrayList<Vertex>> hashtagGraph = new HashMap<>();
  private final HashMap<Vertex, ArrayList<Hashtag>> invertedGraph = new HashMap<>();
  private boolean direction = true; // true == up

  public HashtagGraph() throws IOException {
    initTags();
    System.out.println("DEBUG: INIT STARTED");
    for (Hashtag tag : hashtags) {
      Reader reader = new Reader(new File("VaxData/vax tweets.txt"));
      ArrayList<Vertex> users = reader.getHashtags(tag);
      for (Vertex user : users) {
        // if the user is not in the inverted graph add it;
        if (!invertedGraph.containsKey(user)) {
          ArrayList<Hashtag> hashtagList = new ArrayList<>();
          hashtagList.add(tag);
          invertedGraph.put(user, hashtagList);
        } else {
          invertedGraph.get(user).add(tag);
        }
      }
      hashtagGraph.put(tag, users);
      System.out.println(">>> " + tag.getName());
    }
    System.out.println("DEBUG: INIT FINISHED");
    Writer writer = new Writer(new File("VaxData/hashGraph.txt"));
    System.out.println("Writing to file");
    writer.writeHashtagGraphToFile(this);
    System.out.println("Writer done");
  }

  public HashtagGraph(String file) throws IOException {
    Reader reader = new Reader(new File(file));
    System.out.println("DEBUG: LOADING GRAPH FROM FILE");
    hashtagGraph = reader.loadHashtagGraph();
    System.out.println("DEBUG LOADED GRAPH");
    System.out.println("DEBUG: Creating inverted graph");
    for (Map.Entry<Hashtag, ArrayList<Vertex>> entry : hashtagGraph.entrySet()) {
      for (Vertex user : entry.getValue()) {
        // if the user is not in the inverted graph add it;
        if (!invertedGraph.containsKey(user)) {
          ArrayList<Hashtag> hashtagList = new ArrayList<>();
          hashtagList.add(entry.getKey());
          invertedGraph.put(user, hashtagList);
        } else {
          invertedGraph.get(user).add(entry.getKey());
        }
      }
    }
  }


  public HashMap<Hashtag, ArrayList<Vertex>> getHashtagGraph() {
    return hashtagGraph;
  }

  public void setHashtagGraph(HashMap<Hashtag, ArrayList<Vertex>> hashtagGraph) {
    this.hashtagGraph = hashtagGraph;
  }

  public boolean isDirection() {
    return direction;
  }

  public void setDirection(boolean direction) {
    this.direction = direction;
  }

  public HashMap<Vertex, ArrayList<Hashtag>> invert() {
    return invertedGraph;
  }

  public void add(Hashtag user, List<?> retweets) {
  }

  public void remove(Hashtag user) {
  }

  private void initTags() {
    String[] anti = "#faucifraud,#notovaccine,#novax,#antivaxxers,#ivermectin,#billgates,#arrestbillgates,#scamdemic,#plandemic,#covidlies,#BuiltInALab,#clotshot,#NoNewNormal,#FacuiLiedPeopleDied,#NoVaccinePassportsAnywhere,#FauciEmails,#Convid,#fraudci,#AdverseReactions,#CovidGate,#unvaccinated,#covidiots,#vaccineskill,#vaccinesharm,#getvaxxed,#DoNotComply,#idonotconsent,#NoMedicalApartheid,#NoVaccineMandates".split(
        ",");
    String[] pro = "#getvaccinated,#vax,#vaccine,#covidisnotover,#wearamask,#notacold,#getboosted,#nottheflu,#vaccinessavelives,#GetTheVax,#getvaccinatednow,#CovidIsNotDoneWithUs,#vaccinated,#fullyVaxxed,#vaccinesWork,#GetBoostedNow,#GetYourKidsVaccinatedNow,#GetYourKidsBoostedNow".split(
        ",");

    for (String s : anti) {
      hashtags.add(new Hashtag(s));
      hashtags.get(hashtags.size() - 1).setStance(-1000);
    }
    for (String s : pro) {
      hashtags.add(new Hashtag(s));
      hashtags.get(hashtags.size() - 1).setStance(1000);
    }
  }
}
