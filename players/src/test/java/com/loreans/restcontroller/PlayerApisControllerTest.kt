package com.loreans.restcontroller

import com.fasterxml.jackson.databind.ObjectMapper
import com.loreans.PlayersDomainAppStarter
import org.axonframework.commandhandling.CommandBus
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = arrayOf(PlayersDomainAppStarter::class))
@ContextConfiguration(classes = arrayOf(Configurations::class))
@TestPropertySource("/test.properties")
@WebAppConfiguration
class PlayerApisControllerTest {
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var controller: PlayerApiController

    @Autowired
    private lateinit var commandBus: CommandBus


    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).
                build()
    }

    @Test
    fun shouldreturnsuccessforpost() {
        mockMvc.
                perform(
                        post("/register-player").
                                contentType(MediaType.APPLICATION_JSON).
                                content(getAsJson(RegisterPlayerRequest("nikesh", 21, 9)))
                )
                .andExpect(status().isOk)
    }

    private fun getAsJson(request: Any): String {
        return ObjectMapper().writeValueAsString(request)
    }
}
