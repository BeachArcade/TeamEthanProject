package project;

import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationCreator {

  private Properties prop;

  public ConfigurationCreator() {
    this.prop = new Properties();
    setUpConfigurationsFile();
  }

  public Properties getProp() {
    return prop;
  }

  public void setProp(Properties prop) {
    this.prop = prop;
  }

  private void setUpConfigurationsFile() {
    try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
      prop.load(input);

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public ConfigurationBuilder getConfigurationBuilderForTwitter4j() {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
        .setOAuthConsumerKey(prop.getProperty("oauth.consumerKey"))
        .setOAuthConsumerSecret(prop.getProperty("oauth.consumerSecret"))
        .setOAuthAccessToken(prop.getProperty("oauth.accessToken"))
        .setOAuthAccessTokenSecret(prop.getProperty("oauth.accessTokenSecret"))
        .setTweetModeExtended(true);
    return cb;
  }
}
