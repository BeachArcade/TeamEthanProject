package project.Vertexes;

import java.util.ArrayList;
import java.util.Objects;

public class Tag implements Comparable<Tag> {

  private final String tagName;
  private final int hashCode;
  private ArrayList<String> references = new ArrayList<String>();
  private int stance = 0;

  public Tag(String tag, String references) {
    this.tagName = tag;
    this.hashCode = Objects.hashCode(tag);
    addReferences(references);

    if (this.references.contains("accepting")) { this.setStance(1); }

    if (this.references.contains("rejecting")){ this.setStance(-1); }
  }

  public String getName() {
    return tagName;
  }

  public int getStance() {
    return stance;
  }

  public void setStance(int x) {
    this.stance = x;
  }

  public ArrayList<String> getReferences() {
    return references;
  }

  public void setReferences(ArrayList<String> newReferences) {
    references = newReferences;
  }

  public void addReference(String newReference) {
    references.add(newReference);
  }

  public void addReferences(String references) {
    if (references.contains(", ")) {
      for (String reference : references.split(", ")) {
        addReference(reference);
      }
    } else {
      addReference(references);
    }
  }

  public int compareTo(Tag genericThat) {
    return Integer.compare(genericThat.hashCode, this.hashCode);
  }

  @Override
  public int hashCode() {
    return this.hashCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    Tag that = (Tag) o;
    return this.hashCode == that.hashCode;
  }

  @Override
  public String toString() {
    return "'" + tagName + "'\t" + "{" +references.toString().replace("[","").replace("]","") + '}';
  }
}
