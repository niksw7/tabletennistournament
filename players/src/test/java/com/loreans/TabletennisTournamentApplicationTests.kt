package com.loreans

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes= arrayOf(PlayersDomainApp::class),webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TabletennisTournamentApplicationTests {

    @Test
    fun contextLoads() {
        //assert(false)

    }


}




