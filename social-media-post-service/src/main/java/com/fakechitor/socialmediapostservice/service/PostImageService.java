package com.fakechitor.socialmediapostservice.service;

import com.fakechitor.socialmediapostservice.model.Post;
import com.fakechitor.socialmediapostservice.model.PostImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostImageService {


    public List<PostImage> getPostImages(List<MultipartFile> files, Post post) {
        var postImages = mapFilesToPostImages(files);
        postImages.forEach(postImage -> postImage.setPost(post));
        return postImages;
    }

    private List<PostImage> mapFilesToPostImages(List<MultipartFile> files) {
        return files.stream()
                .map(this::mapToPostImage)
                .toList();
    }

    private PostImage mapToPostImage(MultipartFile file) {
        try {
            var postImage = new PostImage();
            postImage.setData(file.getBytes());
            postImage.setImageName(file.getOriginalFilename());
            postImage.setContentType(file.getContentType());
            return postImage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
