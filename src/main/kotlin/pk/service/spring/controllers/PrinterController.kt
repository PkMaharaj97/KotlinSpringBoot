package pk.service.spring.controllers

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import pk.service.spring.dto.PrinterDTO
import pk.service.spring.service.PrinterService

@RestController
@RequestMapping("/api/printers")
@Validated
class PrinterController(val bookService: PrinterService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBook(@RequestBody @Valid printerDTO: PrinterDTO): PrinterDTO {
        return bookService.addNewPrinter(printerDTO)
    }

    }


