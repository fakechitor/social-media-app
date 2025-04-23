package com.fakechitor.socialmediapostservice.service;

import com.fakechitor.socialmediapostservice.dto.mapper.PostMapper;
import com.fakechitor.socialmediapostservice.dto.request.PostRequestDto;
import com.fakechitor.socialmediapostservice.dto.response.PostResponseDto;
import com.fakechitor.socialmediapostservice.exception.PostNotFoundException;
import com.fakechitor.socialmediapostservice.repository.PostRepository;
import com.fakechitor.socialmediapostservice.util.JwtUtils;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;
    private final PostImageService postImageService;
    private final JwtUtils jwtUtils;
    private final PostRepository postRepository;
    private final EntityManager entityManager;

    @Transactional
    public PostResponseDto save(PostRequestDto postRequestDto, List<MultipartFile> files, String jwt) {
        var post = postMapper.toEntity(postRequestDto);
        post.setUserId(jwtUtils.getUserIdFromJwt(jwt));
        post.setImages(postImageService.getPostImages(files, post));
        postRepository.save(post);
        entityManager.refresh(post);
        return postMapper.toResponseDto(post);
    }

    public PostResponseDto findById(Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post with that id doesn't exist"));
        return postMapper.toResponseDto(post);
    }
}
