package com.trevorism

import org.junit.jupiter.api.Test

class TimingTest {

    @Test
    void timeIt(){
        long start = System.currentTimeMillis()
        def text = new URL("https://testing.trevorism.com/api/ping").text
        println text
        long end = System.currentTimeMillis()
        println "Millis: ${end - start}"
    }
}
