package com.fakechitor.socialmediauserservice.exception

class UserAlreadyExistsException : RuntimeException {
    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    constructor(cause: Throwable?) : super(cause)
}
