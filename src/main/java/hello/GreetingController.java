package hello;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.comment.Comment;
import hello.comment.CommentRepository;

import java.util.concurrent.ThreadLocalRandom;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@RestController
public class GreetingController {
	
    @Autowired
    private CommentRepository commentRepository;
    
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value="/person", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> getFlx(){
		ArrayList<String> lst=new ArrayList(Arrays.asList("Person 1","Person 2","Person 3","Person 4","Person 5","Person 6","Person 7","Person 8","Person 9","Person 10"));
		return Flux.fromIterable(lst).log()
				.delayElements(Duration.ofMillis(2000));
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/random")
    public Flux<ServerSentEvent<Integer>> randomNumbers(@RequestParam("user") String user) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1())+" "+user)
                        .data(data.getT2())
                        .build());
    }
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/comment", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Comment> feed(@RequestParam("user") String user) {
        return this.commentRepository.findAll(user);
    }
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/user", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Comment>> user(@RequestParam("user") String user) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Comment>builder()
                        .event("comment")
                        .id(Long.toString(data.getT1())+" "+user)
                        .data(this.commentRepository.findUser(user))
                        .build());
    }
	
}
