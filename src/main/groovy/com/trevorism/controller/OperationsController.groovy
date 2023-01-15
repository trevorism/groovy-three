package com.trevorism.controller

import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.MediaType
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("ops")
class OperationsController {

    @Tag(name = "Ops Operations")
    @Get(produces = MediaType.TEXT_PLAIN)
    String simpleGet(){
        return "get"
    }

    @Tag(name = "Ops Operations")
    @Get("secure")
    @Secure(value = Roles.USER)
    String secureGet(){
        return "secure"
    }

    @Tag(name = "Ops Operations")
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    def post(@Body Map obj){
        return obj
    }

    @Tag(name = "Ops Operations")
    @Post(value = "secure", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.SYSTEM)
    def postSecure(@Body Map obj){
        return obj
    }
}
