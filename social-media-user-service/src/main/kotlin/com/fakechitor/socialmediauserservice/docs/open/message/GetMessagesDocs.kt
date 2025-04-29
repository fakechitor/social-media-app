package com.fakechitor.socialmediauserservice.docs.open.message

import com.fakechitor.socialmediauserservice.dto.response.ExceptionMessageDto
import com.fakechitor.socialmediauserservice.dto.response.MessageResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    tags = ["Message management"],
    summary = "Get all messages",
    description = "Endpoint for getting message history for two different users.",
    responses = [
        ApiResponse(
            responseCode = "201",
            description = "User saved successfully",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = MessageResponseDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                        [
                            {
                                "id": 1,
                                "sender": "zhukovskiy",
                                "message": "hi",
                                "sentAt": "2025-04-29T09:33:38.434+00:00"
                            },
                            {
                                "id": 2,
                                "sender": "arthurarmenia",
                                "message": "hello",
                                "sentAt": "2025-04-29T09:33:55.589+00:00"
                            }
                        ]
                    """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "401",
            description = "Unauthorized. Problems with jwt token",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "Token is missing or invalid"
                            }
                        """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "404",
            description = "User with that id not found",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "User with id 228 not found"
                            }
                        """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "Something went wrong =("
                            }
                        """,
                        ),
                    ],
                ),
            ],
        ),
    ],
)
annotation class GetMessagesDocs
