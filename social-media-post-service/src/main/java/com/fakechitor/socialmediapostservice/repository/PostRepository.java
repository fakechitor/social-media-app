package com.fakechitor.socialmediapostservice.repository;

import com.fakechitor.socialmediapostservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
