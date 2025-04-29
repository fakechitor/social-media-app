package com.fakechitor.socialmediauserservice.docs.open.subscription
import com.fakechitor.socialmediauserservice.dto.response.ExceptionMessageDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    tags = ["Subscription management"],
    summary = "Subscribe user",
    description = "Endpoint for subscribing user. Requires 'Authorization' header with jwt token",
    responses = [
        ApiResponse(
            responseCode = "201",
            description = "User subscribed successfully.",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "subscriberId": 1,
                                "subscribedUserId": 2,
                                "subscribedUsername": "user228"
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
            responseCode = "409",
            description = "You already subscribed to that user",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "You already subscribed to that user"
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
annotation class SubscribeDocs
