package project.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Alternative class to Tweet Gatherer using the Streaming API
 *
 * @author Ethan Chan & Ethan Hammond
 * @version 1.0
 */

// !TODO make different output files for different groups?
public class GatherTweets {

  private final Properties properties;
  private final HashMap<String, Boolean> tweetOccured = new HashMap<>();
  private final HashMap<String, Boolean> userOccured = new HashMap<>();
  private BufferedWriter tweetWriter, userWriter;
  private StatusListener listener;

  public GatherTweets() throws IOException {
    // Initialize Config
    ConfigurationCreator configurationCreator = new ConfigurationCreator();
    ConfigurationBuilder cb = configurationCreator.getConfigurationBuilderForTwitter4j();

    // Add properties
    properties = configurationCreator.getProp();

    // set up the stream
    TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
    initListener();
    twitterStream.addListener(listener);

    // Find old stuff
    getOldTweets();
    getOldUsers();

    // initialize writers
    tweetWriter = new BufferedWriter(new FileWriter(properties.getProperty("pathToTweetsOutputFile"), true));
    userWriter = new BufferedWriter(new FileWriter(properties.getProperty("pathToUsersOutputFile"), true));

    // Create filters
    FilterQuery filterQuery = new FilterQuery(properties.getProperty("allTags"));
    filterQuery.language("en");

    // Collect tweets
    twitterStream.filter(filterQuery);
  }

  /**
   * Initializes the listener TODO: Make listener write to the respective files
   */
  private void initListener() {
    listener = (new StatusListener() {
      @Override
      public void onStatus(Status status) {
        User user = status.getUser();
        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        try {
          appendUserToOutputFile(user);
          appendTweetToOutputFile(status);
        } catch (IOException e) {
          System.out.print("Couldn't write: ");
          e.printStackTrace();
        }
      }

      @Override
      public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        // System.out.println("Got a status deletion notice id:" +
        // statusDeletionNotice.getStatusId());
      }

      @Override
      public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
      }

      @Override
      public void onScrubGeo(long userId, long upToStatusId) {
        System.out.println(
            "Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
      }

      @Override
      public void onStallWarning(StallWarning warning) {
        System.out.println("Got stall warning:" + warning);
      }

      @Override
      public void onException(Exception ex) {
        ex.printStackTrace();
      }
    });
  }

  // Write to files:
  private void appendUserToOutputFile(User user) throws IOException {
    userWriter = new BufferedWriter(new FileWriter(properties.getProperty("pathToUsersOutputFile"), true));
    if (!userOccured.getOrDefault("@" + user.getScreenName(), false)) {
      // Deal with null bio
      String biography;
      if (user.getDescription() == null) {
        biography = "n/a";
      } else {
        biography = user.getDescription().replaceAll("[\\t\\n\\r]+", " ");
      }
      userWriter.append(
          "@"
              + user.getScreenName()
              + "\t"
              + user.getLocation()
              + "\t"
              + biography
              + "\t"
              + user.getFollowersCount()
              + "\n");
      userOccured.put("@" + user.getScreenName(), true);

      userWriter.close();
    }
  }

  private void appendTweetToOutputFile(Status tweet) throws IOException {
    tweetWriter = new BufferedWriter(new FileWriter(properties.getProperty("pathToTweetsOutputFile"), true));
    if (!tweetOccured.getOrDefault(tweet.getId() + "", false) && !tweet.isRetweet()) {
      String tweetText = tweet.getText().replaceAll("[\\t\\n\\r]+", " ");
      tweetWriter.append(
          tweet.getId()
              + "\t@"
              + tweet.getUser().getScreenName()
              + "\t"
              + tweetText
              + "\t"
              + tweet.getRetweetCount()
              + "\t"
              + tweet.getCreatedAt().getTime()
              + "\n");
      tweetOccured.put(tweet.getId() + "", true);
    }

    tweetWriter.close();
  }

  private void getOldUsers() {
    try {
      BufferedReader buf = new BufferedReader(new FileReader(properties.getProperty("pathToUsersOutputFile")));
      String lineJustFetched = null;
      String[] wordsArray;
      while (true) {
        lineJustFetched = buf.readLine();
        if (lineJustFetched == null) {
          break;
        } else {
          wordsArray = lineJustFetched.split("\t");
          userOccured.put(wordsArray[0], true);
        }
      }
      buf.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void getOldTweets() {
    try {
      BufferedReader buf = new BufferedReader(new FileReader(properties.getProperty("pathToTweetsOutputFile")));
      String lineJustFetched = null;
      String[] wordsArray;
      while (true) {
        lineJustFetched = buf.readLine();
        if (lineJustFetched == null) {
          break;
        } else {
          wordsArray = lineJustFetched.split("\t");
          tweetOccured.put(wordsArray[0], true);
        }
      }
      buf.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
