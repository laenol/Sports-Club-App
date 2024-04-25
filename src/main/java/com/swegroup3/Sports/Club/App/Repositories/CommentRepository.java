package com.swegroup3.Sports.Club.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swegroup3.Sports.Club.App.Entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
