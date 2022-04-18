package project.Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import project.IO.Reader;
import project.Vertexes.Hashtag;
import project.Vertexes.Vertex;

public class HashtagGraph {

  private final ArrayList<Hashtag> hashtags = new ArrayList<>();
  private HashMap<Hashtag, ArrayList<Vertex>> hashtagGraph = new HashMap<>();
  private boolean direction = true; // true == up

  public HashtagGraph() throws FileNotFoundException {
    initTags();
    for (Hashtag tag : hashtags) {
      Reader reader = new Reader(new File("VaxData/vax tweets.txt"));
      ArrayList<Vertex> users = reader.getHashtags(tag);
      hashtagGraph.put(tag, users);
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

  public HashMap<Vertex,ArrayList<Hashtag>> invert() {
    ArrayList<Hashtag> tags = new ArrayList<>();
    ArrayList<Vertex> users = new ArrayList<>();
    HashMap<Vertex, ArrayList<Hashtag>> invertGraph = new HashMap<>();
    // Add all users and hashtags to the arrays
    for (Map.Entry<Hashtag, ArrayList<Vertex>> entry : hashtagGraph.entrySet()) {
      // Add tag
      if (!tags.contains(entry.getKey())) {
        tags.add(entry.getKey());
      }
      // Add user
      for (Vertex v : entry.getValue()) {
        if (!users.contains(v)) {
          users.add(v);
        }
      }
    }
    // add users to the inverted map
    for (Vertex v : users) {
      invertGraph.put(v, new ArrayList<>());
      //Add all hashtags to the list
      ArrayList<Hashtag> newTags = new ArrayList<>();
      for (Hashtag h : tags) {
        for (Vertex otherV : hashtagGraph.get(h)) {
          if (v.compareTo(otherV) == 0) {
            if (!newTags.contains(h)) {
              newTags.add(h);
            }
          }
        }
      }

    }
    return invertGraph;
  }

  public void add(Hashtag user, List<?> retweets) {
  }

  public void remove(Hashtag user) {
  }

  private void initTags(){
    String[] anti = "#faucifraud,#notovaccine,#novax,#antivaxxers,#ivermectin,#billgates,#arrestbillgates,#scamdemic,#plandemic,#covidlies,#BuiltInALab,#clotshot,#NoNewNormal,#FacuiLiedPeopleDied,#NoVaccinePassportsAnywhere,#FauciEmails,#Convid,#fraudci,#AdverseReactions,#CovidGate,#unvaccinated,#covidiots,#vaccineskill,#vaccinesharm,#getvaxxed,#DoNotComply,#idonotconsent,#NoMedicalApartheid,#NoVaccineMandates".split(",");
    String[] pro = "#getvaccinated,#vax,#vaccine,#covidisnotover,#wearamask,#notacold,#getboosted,#nottheflu,#vaccinessavelives,#GetTheVax,#getvaccinatednow,#CovidIsNotDoneWithUs,#vaccinated,#fullyVaxxed,#vaccinesWork,#GetBoostedNow,#GetYourKidsVaccinatedNow,#GetYourKidsBoostedNow".split(",");

    for(String s : anti){
      hashtags.add(new Hashtag(s));
      hashtags.get(hashtags.size() - 1).setStance(-1000);
    }
    for(String s : pro){
      hashtags.add(new Hashtag(s));
      hashtags.get(hashtags.size() - 1).setStance(1000);
    }
  }
}
