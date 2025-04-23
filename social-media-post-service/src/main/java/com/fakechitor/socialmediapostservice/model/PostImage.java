package com.fakechitor.socialmediapostservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_images", schema = "post")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageName;
    private byte[] data;
    private String contentType;
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private Timestamp createdAt;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
