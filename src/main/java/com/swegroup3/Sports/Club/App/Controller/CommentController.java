package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Comment;
import com.swegroup3.Sports.Club.App.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/add")
    public ModelAndView showAddCommentForm() {
        ModelAndView modelAndView = new ModelAndView("add_comment");
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addComment(@ModelAttribute Comment comment) {
        commentService.createComment(comment);
        return "redirect:/comments/all"; // Adjust the redirect to wherever you want users to go after submission
    }

    @GetMapping("/all")
    public ModelAndView getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        ModelAndView modelAndView = new ModelAndView("list_comments");
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }
}
