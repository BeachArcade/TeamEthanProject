package project.Vertexes;

import java.util.Objects;
import java.util.ArrayList;

public class Tag {

    private final String tagName;
    private final int hashCode;
    private ArrayList<String> references = new ArrayList<String>;
    private int stance = 0;

    public Tag(String tag) {
        this.tagName = tag;
        this.hashCode = Objects.hashCode(tag);
    }

    public String getName() { return tagName; }

    public int getStance() {
        return stance;
    }

    public void setStance(int x) {
        this.stance = x;
    }

    public ArrayList<String> getReferences() { return references; }

    public void setReferences( ArrayList<String> newReferences ) { references = newReferences; }

    public void addReference ( String newReference) { references.add(newReference); }

    /*
    public int compareTo(Hashtag genericThat) {
        return Integer.compare(genericThat.numOfTweets, this.numOfTweets);
    }

    public int compareTo(Object o) {
        if (o.getClass().equals(this.getClass())) {
            return this.compareTo((Hashtag) o);
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hashtag that = (Hashtag) o;
        return this.hashCode == that.hashCode;
    }


     */
}
