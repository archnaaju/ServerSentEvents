package hello.comment;

import java.util.List;

import reactor.core.publisher.Flux;

public interface CommentRepository {

	Flux<Comment> findAll(String user);
	
	Comment findUser(String user);

}
