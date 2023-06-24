package pk.service.spring.exceptionhandler

import mu.KLogger
import mu.KLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import pk.service.spring.exception.PrinterNotValidException


@Component
@ControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler(){
    companion object:KLogging()
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        logger.error("Invalid Inputs",ex)
       val errors= ex.bindingResult.allErrors.map {
            error->error.defaultMessage!!
        }.sorted()

        logger.info("invalid inputs are: $errors")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.joinToString(", "){it})
    }

    @ExceptionHandler(PrinterNotValidException::class)
    fun handleInputRequestError(ex: PrinterNotValidException, request: WebRequest): ResponseEntity<Any> {
        logger.info("Exception occurred: ${ex.message} on request: $request")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ex.message
            )
    }


    @ExceptionHandler(Exception::class)
    fun handleAllRunTimeExceptions(ex:Exception,request: WebRequest):ResponseEntity<Any>{
        logger.error("Exception Observed${ex.message}",ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.message)

    }
}