package com.fakechitor.socialmediaauthorization.docs.auth

import com.fakechitor.socialmediaauthorization.dto.response.AuthenticationResponse
import com.fakechitor.socialmediaauthorization.dto.response.ExceptionMessageDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    tags = ["Authentication"],
    summary = "Save user internal",
    description = "Endpoint for user creating internally. Requires \"X-Internal-Secret\" header with a secret code",
    responses = [
        ApiResponse(
            responseCode = "201",
            description = "User saved successfully",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = AuthenticationResponse::class),
                    examples = [
                        ExampleObject(
                            value = """
                        {
                            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWFyaXRlc3RzIiwiaWF0IjoxNzQ0MTAyNDg5LCJleHAiOjE3NDQxMDYwODl9.qMhdVh8TQyCUdvKszrylvmmgKo74T1tmobEzaCTowo8",
                            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWFyaXRlc3RzIiwiaWF0IjoxNzQ0MTAyNDg5LCJleHAiOjE3NDQxODg4ODl9.aSeXqahZo-JO9yntDsqLpkgYo9hRt81CUc8iabL3xIk"
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
annotation class RegisterUser
