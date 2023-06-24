package pk.service.spring.controllers

import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pk.service.spring.service.WelcomeService

@RestController
@RequestMapping("/api/welcome")
class WelcomeController(val welcomeService: WelcomeService) {

    companion object: KLogging()


    @GetMapping("/{name}")
    fun sayGreetings(@PathVariable("name") name:String):String{
        val result=welcomeService.sayGreetings(name)
        logger.info { "$result" }
        return result
    }
}