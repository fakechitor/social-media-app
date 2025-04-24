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
        summary = "Delete post",
        description = "Delete post with id",
        responses = {
                @ApiResponse(
                        responseCode = "204",
                        description = "Successfully deleted post",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PostResponseDto.class)
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
                                             "message": "getPostById.id: Post id can`t be negative"
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
                        responseCode = "404",
                        description = "Post with that id not found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ExceptionMessageDto.class),
                                examples = @ExampleObject(value = """
                                        {
                                             "message": "Post with that id doesn't exist"
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
public @interface DeletePostDocs {
}
