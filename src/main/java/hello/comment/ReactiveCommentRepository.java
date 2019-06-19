package hello.comment;


import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Repository
public class ReactiveCommentRepository implements CommentRepository {

    @Override
    public Flux<Comment> findAll(String user) {
        //simulate data streaming every 1 second.
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(this::generateComment)
                .flatMapIterable(x -> x);
    }
    @Override
    public Comment findUser(String user) {
        //simulate data streaming every 1 second.
    	Comment obj = new Comment(CommentGenerator.randomAuthor(user), CommentGenerator.randomMessage(user), CommentGenerator.getCurrentTimeStamp());
    	return obj;

    }
    
    private List<Comment> generateComment(long interval) {

        Comment obj = new Comment(CommentGenerator.randomAuthor(""), CommentGenerator.randomMessage(""), CommentGenerator.getCurrentTimeStamp());
        return Arrays.asList(obj);

    }

}
