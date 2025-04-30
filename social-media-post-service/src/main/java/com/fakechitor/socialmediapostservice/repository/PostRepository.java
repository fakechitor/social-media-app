package com.fakechitor.socialmediapostservice.repository;

import com.fakechitor.socialmediapostservice.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM post.posts p WHERE p.user_id IN (SELECT subscribed_to_id FROM \"user\".subscriptions WHERE subscriber_id = :userId)", nativeQuery = true)
    Page<Post> findAllSubscribed(
            @Param("userId") Long userId,
            Pageable pageable);
}
