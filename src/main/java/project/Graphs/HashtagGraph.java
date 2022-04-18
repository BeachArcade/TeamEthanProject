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
    System.out.println("DEBUG: INIT STARTED");
    for (Hashtag tag : hashtags) {
      Reader reader = new Reader(new File("VaxData/vax tweets.txt"));
      ArrayList<Vertex> users = reader.getHashtags(tag);
      hashtagGraph.put(tag, users);
      System.out.println(">>> " + tag.getName());
    }
    System.out.println("DEBUG: INIT FINISHED");
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
    System.out.println("DEBUG: Invert starting");
    ArrayList<Hashtag> tags = new ArrayList<>();
    ArrayList<Vertex> users = new ArrayList<>();
    HashMap<Vertex, ArrayList<Hashtag>> invertGraph = new HashMap<>();
    for(Map.Entry<Hashtag, ArrayList<Vertex>> entry: hashtagGraph.entrySet()){
      //Add everything to the arrays
      tags.add(entry.getKey());
      for(Vertex v: entry.getValue()){
        if(!users.contains(v)){
          users.add(v);
        }
      }
    }
    System.out.println(">>>Arrays added");
    int count = 0;
      for(Vertex v: users){
        count++;
        ArrayList<Hashtag> tagList = new ArrayList<>();
        for(Hashtag h: tags){
          if(hashtagGraph.get(h).contains(v)){
            tagList.add(h);
          }
        }
      if (count % 1000 == 0) System.out.println(">>>Users " + count);
        invertGraph.put(v, tagList);
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
