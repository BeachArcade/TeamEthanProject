package project;

import java.util.*;

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

            adjVertices.put(user,)
        }
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
