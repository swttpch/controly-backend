package controly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Comment with the mentioned Id does not exists")
public class CommentIdNotFould extends RuntimeException{
}
