package com.fakechitor.socialmediapostservice.controller;

import com.fakechitor.socialmediapostservice.dto.response.PostResponseDto;
import com.fakechitor.socialmediapostservice.service.FeedService;
import com.fakechitor.socialmediapostservice.util.SortValid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/feed")
@Validated
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping
    ResponseEntity<List<PostResponseDto>> getFeed(
            @Min(value = 1, message = "Can`t be less that 1") @RequestParam("page") int pageNum,
            @SortValid @RequestParam(name = "sort_by", required = false, defaultValue = "asc") String sortBy,
            @RequestHeader("Authorization") String authToken
            ) {
        return ResponseEntity.ok(feedService.getWithParams(pageNum, sortBy, authToken));
    }
}
