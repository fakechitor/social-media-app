package com.fakechitor.socialmediapostservice.docs;


import com.fakechitor.socialmediapostservice.dto.response.ExceptionMessageDto;
import com.fakechitor.socialmediapostservice.dto.response.PostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        tags = {"Post management"},
        summary = "Create post",
        description = "Save post with images to database",
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Successfully saved post",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PostResponseDto.class),
                                examples = @ExampleObject(value = """
                                        {
                                            "title": "my first post",
                                            "text": "^_^",
                                            "createdAt": "2025-04-24T11:16:08.226+00:00",
                                            "images": [
                                                {
                                                 "imageName": "photo.jpeg",
                                                 "data": "(some byte data)",
                                                 "contentType": "image/jpeg",
                                                 "createdAt": "2025-04-24T11:16:08.226+00:00"
                                                }
                                            ]
                                        }
                                        """)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad request",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ExceptionMessageDto.class),
                                examples = @ExampleObject(value = """
                                        {
                                             "message": "Title length cant be more than 30"
                                        }
                                        """)
                        )
                ),
                @ApiResponse(
                        responseCode = "401",
                        description = "Jwt token is missing, expired or invalid",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ExceptionMessageDto.class),
                                examples = @ExampleObject(value = """
                                        {
                                            "message": "Token is missing or invalid"
                                        }
                                        """)
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ExceptionMessageDto.class),
                                examples = @ExampleObject(value = """
                                        {
                                            "message": "Something went wrong =("
                                        }
                                        """)
                        )
                )
        }
)
public @interface CreatePostDocs {
}
