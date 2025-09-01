package br.com.controly.viewmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class UserProfileViewModel {
    private SimplifiedUserViewModel user;
    private List<SimplifiedPostViewModel> posts;
    private List<SimplifiedTopicViewModel> followedTopics;
    private SimplifiedPostViewModel mostRatedPost;
}
