package com.fakechitor.socialmediapostservice.controller;

import com.fakechitor.socialmediapostservice.dto.request.PostRequestDto;
import com.fakechitor.socialmediapostservice.dto.response.PostResponseDto;
import com.fakechitor.socialmediapostservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<PostResponseDto> createPost(
            @RequestPart("postData") PostRequestDto postRequestDto,
            @RequestPart("images") List<MultipartFile> images,
            @RequestHeader("Authorization") String jwt
    ) {
        return ResponseEntity.ok(postService.save(postRequestDto, images, jwt));
    }
}
