package pk.service.spring.service

import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class WelcomeService {



   // @Value("\${message}")
   // lateinit var message:String=""
     var message:String="Hello"
    fun sayGreetings(name : String)="$name $message"

}