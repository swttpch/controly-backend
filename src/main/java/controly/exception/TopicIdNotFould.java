package controly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Topic with the mentioned Id does not exists")
public class TopicIdNotFould extends RuntimeException{
}
