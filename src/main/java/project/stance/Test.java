package project.stance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test {

  private static int curr = 0;

  public static void main(String[] args) throws FileNotFoundException {

    System.out.println("DO\tIT\tGRIP\t?".split("\t")[0]);
    String[] words = { "Poop", "Someone", "Racecar Jesus", "Nine Finger Jeff", "Carrots", "Shroomy",
        "Toes", "Pineapple", "Othello" };
    // JFrame instance
    JFrame frame = new JFrame();

    // Buttons
    JButton button = new JButton("new");
    button.setBounds(350, 600, 100, 40);

    // Text Fields
    JLabel text = new JLabel();
    text.setBounds(100, 200, 100, 400);

    // Listener
    ActionListener newText = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        text.setText(words[curr++ % words.length]);
      }
    };

    // Finalize

    frame.add(button);
    button.addActionListener(newText);
    frame.add(text);

    frame.setSize(800, 800);
    frame.setLayout(null);
    frame.setVisible(true);
  }
}