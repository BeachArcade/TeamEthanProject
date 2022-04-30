package project.stance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import project.graphs.TwitterGraph;
import project.vertices.Vertex;

public class Stance {
  private static ArrayList<Vertex> users = new ArrayList<>();
  private static final ArrayList<Evangelist> evangelists = new ArrayList<>();
  private static int currUser = 0;
  private static int currTweet = 0;

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("DEBUG: APP IS ON");
    // Graph
    TwitterGraph graph = new TwitterGraph();
    users = graph.getEvangelists(100);

    // set up evangelists
    for (Vertex v : users) {
      System.out.print("Added: " + v.getData() + "\t\t");
      evangelists.add(new Evangelist(v.getData()));
      evangelists.get(evangelists.size() - 1).findTweets();
      System.out.println("gripped tweets!");
    }

    // JFrame instance
    JFrame frame = new JFrame();

    // Buttons
    JButton proStance = new JButton("pro");
    JButton antiStance = new JButton("anti");
    proStance.setBounds(100, 650, 100, 40);
    antiStance.setBounds(250, 650, 100, 40);

    JButton nextTweet = new JButton("Next Tweet");
    nextTweet.setBounds(400, 650, 100, 40);

    // Text Fields
    JLabel user = new JLabel();
    JTextField tweet = new JTextField();
    user.setBounds(100, 100, 100, 40);
    user.setText(evangelists.get(currUser).getData());
    tweet.setBounds(100, 300, 1920 * 2, 100);
    tweet.setText(evangelists.get(currUser).getTweets().get(currTweet));

    System.out.println(
        "Starts here\n================================================================================================");
    // Listeners
    ActionListener proAssign = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(users.get(currUser).getName() + "[PRO]");
        users.get(currUser).setRetweetStance(1000);
        currUser++;
        user.setText(users.get(currUser).getName());
        currTweet = 0;
        tweet.setText(evangelists.get(currUser).getTweets().get(0));
      }
    };
    ActionListener antiAssign = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(users.get(currUser).getName() + " [ANTI]");
        users.get(currUser).setRetweetStance(-1000);
        currUser++;
        user.setText(users.get(currUser).getName());
        currTweet = 0;
        tweet.setText(evangelists.get(currUser).getTweets().get(0));
      }
    };
    ActionListener setTweet = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tweet.setText(evangelists.get(currUser).getTweets()
            .get(currTweet % evangelists.get(currUser).getTweets().size()));
        currTweet++;
      }
    };

    // Finalize
    proStance.addActionListener(proAssign);
    antiStance.addActionListener(antiAssign);
    nextTweet.addActionListener(setTweet);

    frame.add(antiStance);
    frame.add(proStance);
    frame.add(nextTweet);

    frame.add(user);
    frame.add(tweet);

    frame.setSize(1920 * 2, 800);
    frame.setLayout(null);
    frame.setVisible(true);
  }
}