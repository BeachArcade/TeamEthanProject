package project;

import java.util.*;
/*TODO: Add checks to see if the key exists
*       Optimize loops
*       Add invert() method
*       Possibly make an addEdge() method
*       
* */
public class TwitterGraph implements Graph{

    private HashMap<Vertex, List<Arc>> adjVertices = new HashMap <Vertex, List<Arc>>();

    @Override
    //Return all edges of a given vertex
    public List<Arc> getEdges(Vertex user) {
        return adjVertices.get(user);
    }

    @Override
    //Make vertexes for all the
    public void invert() {

    }

    @Override
    //Maps a user with its retweets
    public void add(Vertex user, List<Arc> retweets) {
        adjVertices.put(user, retweets);
    }
    //Overloaded method to map a single retweet
    public void add(Vertex user, Arc retweet){
        if(adjVertices.get(user) == null){
            ArrayList<Arc> list = new ArrayList<>();
            //adjVertices.put(user,)
        }
    }
    /**
     * Adds a single vertex to the hashmap with an empty list of Arcs to the graph
     */
    public void addVertex(String user){
        if(!adjVertices.containsKey(user)){
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
