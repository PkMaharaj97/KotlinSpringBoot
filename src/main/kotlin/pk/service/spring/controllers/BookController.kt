package pk.service.spring.controllers

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import pk.service.spring.dto.BookDTO
import pk.service.spring.service.BookService

@RestController
@RequestMapping("/api/books")
@Validated
class BookController(val bookService: BookService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBook(@RequestBody @Valid bookDTO: BookDTO): BookDTO {
      return  bookService.addBook(bookDTO)
    }


    @GetMapping
    fun getAllBooks(@RequestParam("book_name", required = false)bookName:String?):List<BookDTO>{
        return bookService.retriveAllBooks(bookName)
    }

    @PutMapping("/{book_id}")
    fun updateBook(
        @RequestBody bookDTO: BookDTO,
        @PathVariable("book_id") bookId:Int):BookDTO=bookService.updateBook(bookId,bookDTO)

    @DeleteMapping("/{book_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable("book_id") bookId:Int)=bookService.deleteBook(bookId)
}