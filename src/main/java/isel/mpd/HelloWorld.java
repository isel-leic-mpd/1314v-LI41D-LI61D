package isel.mpd;

import isel.mpd.misc.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World {} {}", 1, "abc"  );
	logger.error("Some error in main");
	Utils.say("Hello world");
  }
}
