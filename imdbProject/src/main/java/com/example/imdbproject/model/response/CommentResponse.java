package com.example.imdbproject.model.response;


import com.example.imdbproject.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponse {

    private String username;
    private String text;
    private Set<CommentResponse> replies;
    private Long id;


    public static Set <CommentResponse> makeCommentRespond(Set <Comment> commentResponses){

        Set <CommentResponse> result = new HashSet<>();
        for (Comment comment : commentResponses){
            CommentResponse commentResponse = new CommentResponse(comment.getUser().getUsername() , comment.getText() , new HashSet<>() , comment.getID());
            result.add(commentResponse);
        }

        return result;
    }

    public static boolean isEqual(Comment comment , CommentResponse commentResponse){

        if (comment.getID().equals(commentResponse.id))
            return true;

        return false;
    }
}
