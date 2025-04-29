package com.fakechitor.socialmediauserservice.docs.open.friend

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
    tags = ["Friends management"],
    summary = "Get friendship status",
    description = "Endpoint for getting friendship status. Can be: ['NONE','SUBSCRIBED','PENDING','FRIEND']",
    responses = [
        ApiResponse(
            responseCode = "200",
            description = "Status retrieved successfully",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = MessageResponseDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                        {
                            "requestedUserId": 2,
                            "status": "FRIEND"
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
            description = "User can not retrieve status for himself",
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
annotation class GetFriendshipStatusDocs
