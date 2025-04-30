package com.fakechitor.socialmediapostservice.service;

import com.fakechitor.socialmediapostservice.dto.mapper.PostMapper;
import com.fakechitor.socialmediapostservice.dto.response.PostResponseDto;
import com.fakechitor.socialmediapostservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final PostService postService;
    private final JwtUtils jwtUtils;
    private final PostMapper postMapper;

    public List<PostResponseDto> getWithParams(int pageNum, String sortBy, String authToken) {
        var userId = jwtUtils.getUserIdFromJwt(authToken);
        var posts = postService.findSubscribed(userId, sortBy, pageNum);
        return posts.get().map(postMapper::toResponseDto).toList();
    }
}
