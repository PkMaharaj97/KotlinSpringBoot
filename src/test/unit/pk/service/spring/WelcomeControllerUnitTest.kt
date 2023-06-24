package pk.service.spring

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import pk.service.spring.controllers.BookController
import pk.service.spring.controllers.WelcomeController
import pk.service.spring.dto.BookDTO
import pk.service.spring.service.BookService
import pk.service.spring.service.WelcomeService

@WebMvcTest(controllers = [WelcomeController::class,BookController::class])
@AutoConfigureWebTestClient
class WelcomeControllerUnitTest {

    @Autowired
    lateinit var webTstClient: WebTestClient


    @MockkBean
    lateinit var welcomeServiceMock:WelcomeService
    @MockkBean
    lateinit var bookServiceMock:BookService

    @Test
    fun retriueveGreetings(){
        val name="PkServices"
        every {
            welcomeServiceMock.sayGreetings(any())
        } returns "$name Hi from Default"
        val result=webTstClient.get()
            .uri("/api/welcome/{name}",name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()


        Assertions.assertEquals("$name Hi from Default",result.responseBody)

    }
    @Test
    fun testBookValidation(){

        val bookDto=BookDTO(null," ","Deves"," ")
        every {
            bookServiceMock.addBook(any())
        } returns bookDto

        val result=webTstClient.post()
            .uri("/api/books")
                   .bodyValue(bookDto)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
                   .responseBody



        assertEquals("Author name should not be empty, Book name should not be empty",result)

    }



    @Test
    fun testRunTimeException(){


        val message="Unexpected Error"
        val bookDto=BookDTO(null,"khfvb ","Deves","wkhvbkw ")
        every {
            bookServiceMock.addBook(any())
        } throws RuntimeException(message)

        val result=webTstClient.post()
            .uri("/api/books")
            .bodyValue(bookDto)
            .exchange()
            .expectStatus().is5xxServerError
            .expectBody(String::class.java)
            .returnResult()
            .responseBody
        assertEquals(message,result)

    }

}