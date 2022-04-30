package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import project.io.Reader;
import project.vertices.Tag;

public class Lexicon {

  private List<Tag> tags = new ArrayList<>();

  public Lexicon() throws FileNotFoundException {
    Reader lexiconReader = new Reader(new File("Data/labeled tag elements.txt"));
    lexiconReader.loadLexicon(this);
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public void addTag(String tag, String references) {
    // Check if the tag is already contained in the lexicon
    if (tags.contains(new Tag(tag))) {
      // if so, add new references to the tag
      tags.get(tags.indexOf(new Tag(tag))).addReferences(references);
    } else {
      // Else add the new to the lexicon
      this.tags.add(new Tag(tag, references));
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Lexicon{\n");
    for (Tag tag : tags) {
      stringBuilder.append("\t").append(tag.toString()).append('\n');
    }
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}
