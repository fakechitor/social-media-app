package com.fakechitor.socialmediapostservice.dto.response;

import java.sql.Timestamp;

public record PostImageDto(
        String imageName,
        byte[] data,
        String contentType,
        Timestamp createdAt
) {
}
