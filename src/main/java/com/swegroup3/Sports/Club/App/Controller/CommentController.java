package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Comment;
import com.swegroup3.Sports.Club.App.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Method to display the form for adding a new comment
    @GetMapping("/new")
    public String showFormAddComment(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("action", "/comments/save");
        return "comment/form_comment";
    }

    // Method to save a new comment
    @PostMapping("/save")
    public String saveComment(@ModelAttribute Comment comment) {
        commentService.createComment(comment);
        return "redirect:/comments/list";
    }

    // Method to list all comments
    @GetMapping("/list")
    public String listComments(Model model) {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "comment/list_comments";
    }
}
