package com.swegroup3.Sports.Club.App.Services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swegroup3.Sports.Club.App.Entities.Comment;
import com.swegroup3.Sports.Club.App.Repositories.CommentRepository;
import com.swegroup3.Sports.Club.App.Services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setText(commentDetails.getText());
            commentRepository.save(comment);
        }
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
