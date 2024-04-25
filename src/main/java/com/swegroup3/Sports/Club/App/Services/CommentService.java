package com.swegroup3.Sports.Club.App.Services;

import java.util.List;

import com.swegroup3.Sports.Club.App.Entities.Comment;

public interface CommentService {
    Comment createComment(Comment comment);

    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    Comment updateComment(Long id, Comment comment);

    void deleteComment(Long id);
}
