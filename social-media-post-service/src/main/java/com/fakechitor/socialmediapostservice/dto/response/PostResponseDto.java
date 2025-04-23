package com.fakechitor.socialmediapostservice.dto.response;

import java.sql.Timestamp;
import java.util.List;

public record PostResponseDto(
         String title,
         String text,
         Timestamp createdAt,
         List<PostImageDto> images
) {
}
