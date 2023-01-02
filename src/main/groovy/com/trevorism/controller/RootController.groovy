package com.trevorism.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Controller("/")
class RootController {

    @Operation(summary = "say hello")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(type = "string"))
    )
    @Get(produces = MediaType.TEXT_PLAIN)
    String index() {
        return "Hello World"
    }

    @Operation(summary = "ping")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(type = "string"))
    )
    @Get(value = "/ping", produces = MediaType.TEXT_PLAIN)
    String ping() {
        return "pong"
    }

    @Operation(summary = "help")
    @ApiResponse(responseCode = "302")
    @Get(value = "/help")
    HttpResponse<String> help() {
        return HttpResponse.redirect(new URI("swagger/views/swagger-ui/index.html"))
    }

    @Operation(summary = "version")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(type = "string"))
    )
    @Get(value = "/version", produces = MediaType.TEXT_PLAIN)
    String version() {
        return "0-3-1"
    }
}
