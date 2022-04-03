package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*TODO: Add checks to see if the key exists
*       Optimize loops
*       Add invert() method
*       Possibly make an addEdge() method
*       
* */
public class TwitterGraph implements Graph{
    private HashMap<Vertex, List<Arc>> adjVertices = new HashMap <Vertex, List<Arc>>();

    //TODO Change filepaths
    public TwitterGraph() throws FileNotFoundException {
        Reader userReader = new Reader(new File("VaxData/100VaxUsersTweets.txt"));
        Reader tweetReader = new Reader(new File("VaxData/100VaxTweets.txt"));

        userReader.populateUsers(this);
        System.out.println(this.toString());
    }
    @Override
    //Return all edges of a given vertex
    public List<Arc> getEdges(Vertex user) {
        return adjVertices.get(user);
    }

    @Override
    //Make vertexes for all the
    public void invert() {

    }


    /**
     * Maps a user with its retweets
     */
    @Override
    public void add(Vertex user, List<Arc> retweets) {
        adjVertices.put(user, retweets);
    }

    /**
     * Overloaded method to map a single retweet
     */
    public void add(Vertex user, Arc retweet){
        if(adjVertices.get(user) == null){ //When user is not in the map
            ArrayList<Arc> list = new ArrayList<Arc>();
            list.add(retweet);
            adjVertices.put(user, list);
        }
    }
    /**
     * Adds a single vertex to the hashmap with an empty list of Arcs to the graph
     */
    public void add(String user){
        if(!adjVertices.containsKey(new Vertex(user))){
            adjVertices.put(new Vertex(user), new ArrayList<Arc>());
        }

    }
    @Override
    public void remove(Vertex user) {
        adjVertices.remove(user);

    }


    @Override
    public void getVertex(Vertex user){

    }

    /* Format:
        retweeter{retweeted users,...}
     */
    public String toString(){
        String str = "";
        for(Map.Entry<Vertex, List<Arc>> entry: adjVertices.entrySet()){
            str += entry.getKey().getName() + "\t{";
            for(Arc arc: entry.getValue()){
                str += arc.getVertex().getName()+",";
            }
            str += "}\n";
        }
        return str;
    }
}
