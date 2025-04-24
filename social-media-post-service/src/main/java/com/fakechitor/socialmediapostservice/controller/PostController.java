package com.fakechitor.socialmediapostservice.controller;

import com.fakechitor.socialmediapostservice.dto.request.PostRequestDto;
import com.fakechitor.socialmediapostservice.dto.response.PostResponseDto;
import com.fakechitor.socialmediapostservice.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<PostResponseDto> createPost(
            @Valid @RequestPart("postData") PostRequestDto postRequestDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestHeader("Authorization") String jwt
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postRequestDto, images, jwt));
    }

    @GetMapping("/{id}")
    ResponseEntity<PostResponseDto> getPostById(@Min(value = 0, message = "Post id can`t be negative") @PathVariable("id") Long id) {
        var post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @PatchMapping("/{id}")
    ResponseEntity<PostResponseDto> updatePost(@Min(value = 0, message = "Post id can`t be negative") @PathVariable("id") Long id,
                                               @Valid @RequestPart(name = "postData", required = false) PostRequestDto postRequestDto,
                                               @RequestPart(name = "images", required = false) List<MultipartFile> images,
                                               @RequestHeader("Authorization") String jwt) {
        var updatedPost = postService.update(postRequestDto, images, id, jwt);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<PostResponseDto> deletePost(@Min(value = 0, message = "Post id can`t be negative") @PathVariable("id") Long id,
                                               @RequestHeader("Authorization") String jwt) {
        postService.delete(id, jwt);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
