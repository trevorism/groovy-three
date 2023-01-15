package com.trevorism.controller

import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/")
class RootController {

    private static final Logger log = LoggerFactory.getLogger(RootController)


    @Operation(summary = "Context Root of the Application")
    @ApiResponse(
            responseCode = "200", content = @Content(mediaType = "text/html", schema = @Schema(type = "string"))
    )
    @Get(produces = MediaType.TEXT_HTML)
    HttpResponse<List<String>> index() {
        log.info("Hit context root")
        HttpResponse.ok(['<a href="/ping">/ping</a>', '<a href="/help">/help</a>', '<a href="/version">/version</a>'])
    }

    @Operation(summary = "Returns 'pong' on success")
    @ApiResponse(
            responseCode = "200", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))
    )
    @Get(value = "/ping", produces = MediaType.TEXT_PLAIN)
    String ping() {
        return "pong"
    }

    @Operation(summary = "This help page")
    @ApiResponse(responseCode = "302")
    @Get(value = "/help")
    HttpResponse<String> help() {
        return HttpResponse.redirect(new URI("swagger-ui/index.html"))
    }

    @Operation(summary = "Returns the version of the API")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(type = "string"))
    )
    @Get(value = "/version", produces = MediaType.TEXT_PLAIN)
    @Secure(value = Roles.ADMIN)
    String version() {
        return "0-3-1"
    }
}
