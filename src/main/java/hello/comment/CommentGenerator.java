package hello.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommentGenerator {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final List<String> COMMENT_AUTHOR =
            Arrays.asList(
                    "Archna 1", "Archna 2", "Archna 3", "Archna 4", "Archna 5",
                    "Archna 6", "Archna 7", "Archna 8", "Archna 9", "Archna 10");

    private static final List<String> COMMENT_AUTHOR_2 =
            Arrays.asList(
                    "Gupta 1", "Gupta 2", "Gupta 3", "Gupta 4", "Gupta 5",
                    "Gupta 6", "Gupta 7", "Gupta 8", "Gupta 9", "Gupta 10");

    private static final List<String> COMMENT_MESSAGE =
            Arrays.asList(
                    "Archna - I Love this!",
                    "Archna - Me too!",
                    "Archna - Wow",
                    "Archna - True!",
                    "Archna - Hello everyone here?",
                    "Archna - Good!");

    private static final List<String> COMMENT_MESSAGE_2 =
            Arrays.asList(
                    "Gupta - I Love this!",
                    "Gupta - Me too!",
                    "Gupta - Wow",
                    "Gupta - True!",
                    "Gupta - Hello everyone here?",
                    "Gupta - Good!");
    
    public static String randomAuthor(String user) {
    	if(user.equals("Archna"))
    		return COMMENT_AUTHOR.get(RANDOM.nextInt(COMMENT_AUTHOR.size()));
    	else
    		return COMMENT_AUTHOR_2.get(RANDOM.nextInt(COMMENT_AUTHOR_2.size()));
    }

    public static String randomMessage(String user) {
    	if(user.equals("Archna"))
    		return COMMENT_MESSAGE.get(RANDOM.nextInt(COMMENT_MESSAGE.size()));
    	else
    		return COMMENT_MESSAGE_2.get(RANDOM.nextInt(COMMENT_MESSAGE_2.size()));
    }

    public static String getCurrentTimeStamp() {
        return dtf.format(LocalDateTime.now());
    }
}
