package test.java.project;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import project.Arc;
import project.TweetArc;
import project.TwitterGraph;
import project.Vertex;

public class GraphTest {
  private String[] arr = {
    "1447376265187676162	@wqbelle	RT @wqbelle: The enemy is us.  https://t.co/8sgqS2KF6h  from 2019: Yes, we are our own worst enemy. There's nobody else to blame!  #Covid #…",
    "1447376289338470405	@wqbelle	RT @wqbelle: To mask, or not to mask, that should not be a question.  https://t.co/Sa4xEjK9wp  I have to shake my head at all the protests…",
    "1447376329205223425	@nickbuteraa	RT @Chesschick01: \"This is not a negotiation\" #hero ❤️ #PushBack #NoVaccineMandates #NoVaccinePassports https://t.co/qf2QH49X22",
    "1447376292958015488	@KBL17Kathy	RT @TripleThreat_MD: Hello, mhmm so, Pediatricians on BOTH sides of the aisle would really like to get kids vaccinated as soon as they are…",
    "1447376351237869575	@gnader	Homemade vegetarian rainbow chili. Yum!   #QuarantineCuisine #TeamGendra  #GETVACCINATED #GETVAXXED #TrumpsVirus… https://t.co/UO7JBjTNRa",
    "1447376358716231686	@WDogii	RT @You3_JP: カリフォルニアでもワクチンの義務化、ワクチンパスポートに反対する市民運動が行われるようになってきた。  \"We will not comply!\" https://t.co/21ZbV59w9t",
    "1447376366597332993	@Jessied75235626	. Speaking to NURSES about why they won’t take Vxne, how many are leaving their jobs, seeing children admitted to h… https://t.co/355eW7vwpB",
    "1447376404522360835	@KarlNYYankees	BEAUTIFUL SIGHT #Freedom ! #NoVaccineMandates  #NoVaccinePassports  #FireFauci",
    "1447376475284455427	@terriscofield	RT @rd_is_king: #NoVaccinePassports  #Covid1984 #NoMandatoryVaccines  #NoMasks  #GreatReset  #NewNormal",
    "1447376479369801732	@r186mgt	RT @heather_parisi: Why are they using the tools of cowards, to blackmail & coercing people who have doubts?  The exact same methods of the…",
    "1447376509535203331	@AcidoBlamOK	#plandemia #covid1984 #DictaduraSanitaria  19/22 \"Debemos actuar juntos y unir fuerzas\" https://t.co/tOH8g6gvHI",
    "1447376511447695361	@nurembergherald	I guess Jonas Salk was an #AntiVaxxer.  #GetVaccinated #vaccinated #vaccine #Venom #NoTimeToDie #joebiden… https://t.co/TN9HELMdDj",
    "1447376537674784770	@Prezide36505563	RT @Jarjarbinx10: @JustinTrudeau F.U.C.K T.H.A.T this is unconstitutional and a violation of our Section 6(2) mobility rights. #NoVaccinePa…",
    "1447376548147994625	@AcidoBlamOK	#plandemia #covid1984 #DictaduraSanitaria  20/22 En 2001 revisaron el juramento hipocratico y sacaron la frase \"no… https://t.co/oDPY0u5vZZ",
    "1447376559942221825	@Jessied75235626	. Speaking to NURSES about why they won’t take Vxne, how many are leaving their jobs, seeing children admitted to h… https://t.co/3L4rBSXK4b",
    "1447376585934389248	@AcidoBlamOK	#plandemia #covid1984 #DictaduraSanitaria  21/22 Se están perdiendo derechos fundamentales y esto termina con una c… https://t.co/e49tefQNLq",
    "1447376589679902720	@AcidoBlamOK	#plandemia #covid1984 #DictaduraSanitaria  22/22 VIDEO COMPLETO https://t.co/ieTT5O1iE5",
    "1447376627474710535	@TanziaMill	@BkPhilanthropy #GetVaxxed"
  };

  @Test
  public void testVertex() {
    String str =
        "1447358807840075779	@Fz1Gabe	RT @JustLaElisa17: 🇳🇱 The Dutch rising against covid tyranny ⚡  #NoVaccineMandates  #NoVaccinePassports https://t.co/ADsfrakrvO";
    String content =
        "RT @JustLaElisa17: 🇳🇱 The Dutch rising against covid tyranny ⚡  #NoVaccineMandates  #NoVaccinePassports https://t.co/ADsfrakrvO";
    Vertex v = new Vertex(str);

  }

  @Test
  public void testArc() {
    String str =
        "1447358807840075779	@Fz1Gabe	RT @JustLaElisa17: 🇳🇱 The Dutch rising against covid tyranny ⚡  #NoVaccineMandates  #NoVaccinePassports https://t.co/ADsfrakrvO";
    String user = "@JustLaElisa17";
    String content =
        "🇳🇱 The Dutch rising against covid tyranny ⚡  #NoVaccineMandates  #NoVaccinePassports https://t.co/ADsfrakrvO";
    Vertex v = new Vertex(str);
  }

  @Test
  public void deleteThisWhenDone() {
    String tweet =  "1447358807840075779	@Fz1Gabe	RT @JustLaElisa17: 🇳🇱 The Dutch rising against covid tyranny ⚡  #NoVaccineMandates  #NoVaccinePassports https://t.co/ADsfrakrvO";
    Vertex friend = new Vertex(tweet);
    HashMap<Vertex, Arc> map = new HashMap<>();
    int i = 0;
    for (String s : arr) {
      Vertex v = new Vertex(s);
      map.put(v, new TweetArc("tttstasat"));
      }
    System.out.println(map.containsKey(friend));
  }

  @Test
  public void testGraph() throws FileNotFoundException {
    TwitterGraph graph = new TwitterGraph();
    System.out.println(graph.toString());
  }

  @Test
  public void testInvert() throws FileNotFoundException {
    TwitterGraph graph = new TwitterGraph();
    String str, str2 ,str3;
    str = graph.toString();
    graph.invert();
    str2 = graph.toString();
    graph.invert();
    str3 = graph.toString();

    System.out.println("Regular\n" + str + "\n");
    System.out.println("Invert\n" + str2 + "\n");
    System.out.println("Double Invert\n" + str3 + "\n");

    assert(!str2.substring(0,1000).equals(str.substring(0,1000)));
    assert(str3.substring(0,1000).equals(str.substring(0,1000)));

  }

  @Test
  public void testSort(){
    ArrayList<TweetArc> list = new ArrayList<>();
    list.add(new TweetArc("Dog", 10));
    list.add(new TweetArc("Cat", 9));
    list.add(new TweetArc("Squirrel", 1));
    list.add(new TweetArc("Mango", 2));
    list.add(new TweetArc("Car", 3));
    list.add(new TweetArc("Fish", 8));

    Collections.sort(list);
    for (TweetArc arc : list) System.out.println(arc.getVertex());
  }
}
