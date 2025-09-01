package br.com.controly.viewmodels;

import br.com.controly.domain.entities.PostEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedPostWithContentViewModel implements Comparable<SimplifiedPostWithContentViewModel> {


    private Long idPost;
    private String title;
    private String content;
    private int comments;
    private SimplifiedUserViewModel owner;
    private SimplifiedTopicViewModel topic;
    private boolean isDoubt;
    private int points;
    private boolean userHasVoted;

    private LocalDateTime createdIn;

    public SimplifiedPostWithContentViewModel(PostEntity post,
                                              SimplifiedTopicViewModel topic,
                                              SimplifiedUserViewModel user) {
        this.convert(post, topic, user);
    }

    public SimplifiedPostWithContentViewModel convert(PostEntity post,
                                                      SimplifiedTopicViewModel topic,
                                                      SimplifiedUserViewModel user){
        setTitle(post.getTitle());
        setOwner(user);
        setPoints(post.getPoints());
        setDoubt(post.isDoubt());
        setTopic(topic);
        if(post.getComments() == null) {
            setComments(0);
        }
        else{
            setComments(post.getComments().size());
        }
        setCreatedIn(post.getCreatedIn());
        setIdPost(post.getIdPost());
        setContent(post.getContent());
        setCreatedIn(post.getCreatedIn());
        return this;
    }

    @Override
    public int compareTo(SimplifiedPostWithContentViewModel o) {
        return Integer.compare(o.points,this.points);
    }
}
