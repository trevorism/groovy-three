package com.trevorism.security

import io.micronaut.core.async.publisher.Publishers
import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.HttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import org.reactivestreams.Publisher

@Filter("/**")
class TrevorismHstsFilter implements HttpServerFilter {
    @Override
    Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        return Publishers.then(chain.proceed(request), resp -> decorateResponseWithHeaders(request, resp))
    }

    void decorateResponseWithHeaders(HttpRequest<?> httpRequest, MutableHttpResponse<?> mutableHttpResponse) {
        mutableHttpResponse.header("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload")
    }
}
