package br.com.controly.viewmodels;

import br.com.controly.domain.entities.PostEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimplifiedPostViewModel {
    private Long idPost;
    private String title;
    private SimplifiedUserViewModel owner;
    private SimplifiedTopicViewModel topic;
    private boolean isDoubt;
    private int points;

    public SimplifiedPostViewModel(PostEntity post,
                                   SimplifiedTopicViewModel topic,
                                   SimplifiedUserViewModel user) {
        this.convert(post, topic, user);
    }

    public SimplifiedPostViewModel convert(PostEntity post,
                                           SimplifiedTopicViewModel topic,
                                           SimplifiedUserViewModel user){
        setTitle(post.getTitle());
        setOwner(user);
        setPoints(post.getPoints());
        setDoubt(post.isDoubt());
        setTopic(topic);
        setIdPost(post.getIdPost());
        return this;
    }
}
