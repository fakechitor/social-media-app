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
    summary = "Send message",
    description = "Endpoint for sending message. Requires header with jwt token and message in json format",
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
                            {
                                "id": 1,
                                "sender": "maktraher",
                                "message": "hi",
                                "sentAt": "2025-04-29T09:33:38.434+00:00"
                            }
                        """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "400",
            description = "Message doesn't satisfy the requirements",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "You do not have permission to access that endpoint"
                            }
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
            responseCode = "403",
            description = "User can not send messages to himself",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "You can not send messages to yourself"
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
annotation class SendMessageDocs
