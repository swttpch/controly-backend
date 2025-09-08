package br.com.controly.viewmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class UserProfileViewModel {
    private UserViewModel user;
    private List<SimplifiedPostViewModel> posts;
    private List<SimplifiedTopicViewModel> followedTopics;
    private SimplifiedPostViewModel mostRatedPost;
}
