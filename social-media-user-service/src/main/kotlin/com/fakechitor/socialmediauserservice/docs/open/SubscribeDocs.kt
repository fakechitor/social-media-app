package com.fakechitor.socialmediauserservice.docs.open

import com.fakechitor.socialmediauserservice.dto.response.ExceptionMessageDto
import com.fakechitor.socialmediauserservice.dto.response.SubscribeResponseDocs
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    tags = ["User Interaction"],
    summary = "Subscribe on user",
    description = "Endpoint for adding subscription on user",
    responses = [
        ApiResponse(
            responseCode = "201",
            description = "Subscription was successful",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = SubscribeResponseDocs::class),
                    examples = [
                        ExampleObject(
                            value = """
                        {
                            "subscribedUserLogin" : "Mark Zhukov"
                        }
                    """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "404",
            description = "User with that username not found and can not be subscribed",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "User with login BorjomiGeorgia does not exist",
                            }
                        """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "409",
            description = "You are already subscribed to this user",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "You already subscribed to this user",
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
                                "message": "Internal Server Error"
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
