/**
 * @deprecated use GatherTweets.java instead
 */
package project;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


import java.io.*;
import java.util.*;

public class TweetGatherer {

    private BufferedWriter tweetWriter, userWriter;
    private Twitter twitter;
    private Properties properties;
    private HashMap<String, Boolean> tweetOccured = new HashMap<>();
    private HashMap<String, Boolean> userOccured = new HashMap<>();

    public TweetGatherer() throws IOException{
        setUpConfigurations();
    }

    private void setUpConfigurations() throws IOException {
        ConfigurationCreator configurationCreator = new ConfigurationCreator();
        ConfigurationBuilder cb = configurationCreator.getConfigurationBuilderForTwitter4j();
        properties = configurationCreator.getProp();
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        getOldTweets();
        getOldUsers();
    }

    private void getOldUsers() {
        try{
            BufferedReader buf = new BufferedReader(new FileReader("VaxData/users.txt"));
            String lineJustFetched = null;
            String[] wordsArray;
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    wordsArray = lineJustFetched.split("\t");
                    userOccured.put(wordsArray[0], true);
                }
            }
            buf.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void getOldTweets() {
        try{
            BufferedReader buf = new BufferedReader(new FileReader("VaxData/vax tweets.txt"));
            String lineJustFetched = null;
            String[] wordsArray;
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    wordsArray = lineJustFetched.split("\t");
                    tweetOccured.put(wordsArray[0], true);
                }
            }
            buf.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void startQuery() throws IOException {
        String hashtags = properties.getProperty("antiVaxTags");
        List<String> hashtagList = Arrays.asList(hashtags.split(","));
        for(String hashtag: hashtagList){
            tweetWriter = new BufferedWriter(new FileWriter(properties.getProperty("pathToTweetsOutputFile"), true));
            userWriter = new BufferedWriter(new FileWriter(properties.getProperty("pathToUsersOutputFile"), true));
            queryByHashtag(hashtag);
            tweetWriter.close();
            userWriter.close();
        }

    }
    private void queryByHashtag(String hashtag) throws IOException{
        try{

            Query query = new Query(hashtag);
            query.setLang(properties.getProperty("language"));
            QueryResult result;

            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    appendTweetToOutputFile(tweet);
                    appendUserToOutputFile(tweet.getUser());
                }
            } while ((query = result.nextQuery()) != null);

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }

    }

    private void appendUserToOutputFile(User user) throws IOException {
        if(!userOccured.getOrDefault("@" + user.getScreenName(), false)) {
            String biography = user.getDescription().replaceAll("[\\t\\n\\r]+", " ");
            userWriter.append("@" + user.getScreenName() + "\t" + user.getLocation() + "\t" + biography + "\t" + user.getFollowersCount() + "\n");
            userOccured.put("@" + user.getScreenName(), true);
        }
    }

    private void appendTweetToOutputFile(Status tweet) throws IOException {
        if(!tweetOccured.getOrDefault(tweet.getId()+"", false) && !tweet.isRetweet()) {
            String tweetText = tweet.getText().replaceAll("[\\t\\n\\r]+", " ");
            tweetWriter.append(tweet.getId() + "\t@" + tweet.getUser().getScreenName() + "\t" + tweetText + "\t" + tweet.getRetweetCount() + "\t" + tweet.getCreatedAt().getTime() + "\n");
            tweetOccured.put(tweet.getId()+"", true);
        }
    }
}
