package com.fakechitor.socialmediauserservice.docs.internal

import com.fakechitor.socialmediauserservice.dto.response.ExceptionMessageDto
import com.fakechitor.socialmediauserservice.dto.response.UserResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    tags = ["Internal User Interaction"],
    summary = "Save user internal",
    description = "Endpoint for user creating internally. Requires \"X-Internal-Secret\" header with a secret code",
    responses = [
        ApiResponse(
            responseCode = "201",
            description = "User saved successfully",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserResponseDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                        {
                            "id" : "1",
                            "username": "vladliv4ik",
                            "email": "vlados228@gmail.com",
                            "password": "vladusprogramus"
                        }
                    """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "403",
            description = "Content is unavailable due to missing or wrong \"X-Internal-Secret\" header",
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
            responseCode = "409",
            description = "User with that login already exists",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "User with that name already exists"
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
annotation class SaveUserDocs
