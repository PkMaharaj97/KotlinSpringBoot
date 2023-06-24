package pk.service.spring.service

import mu.KLogging
import org.springframework.stereotype.Service
import pk.service.spring.dto.BookDTO
import pk.service.spring.entity.Book
import pk.service.spring.exception.BookNotFoundException
import pk.service.spring.exception.PrinterNotValidException
import pk.service.spring.repository.BookRepository
import pk.service.spring.repository.PrinterRepository

@Service
class BookService(val bookRepository: BookRepository,val printerRepository: PrinterRepository ){

    companion object :KLogging()
    fun addBook(bookDTO: BookDTO):BookDTO{

        val printer=printerRepository.findById(bookDTO.printerId?:0)
        if(!printer.isPresent){
            throw PrinterNotValidException("Printer with Id ${bookDTO.printerId?:0} is not valid.")
        }
        val bookEntity=bookDTO.let {
            Book(id = null, name = it.name, author = it.author, category = it.category, printer = printer.get())
        }
        bookRepository.save(bookEntity)
        logger.info { "Addded Book Is $bookEntity" }

        return bookEntity.let {
            BookDTO(id = it.id, name = it.name, author = it.author, category = it.category, printerId = it.printer?.id)
        }
    }

    fun retriveAllBooks(bookName:String?): List<BookDTO> {
       val books= bookName?.let {
            bookRepository.findBooksByNameContaining(it)
        }?:bookRepository.findAll()

       return  books.map {
            BookDTO(
                id = it.id,
                name = it.name,
                author = it.author,
                category = it.category,
            )
        }
    }

    fun updateBook(bookId: Int, bookDTO: BookDTO): BookDTO {
        val existingBook=bookRepository.findById(bookId)
      return  if(existingBook.isPresent){
            existingBook.get().let {
                it.name=bookDTO.name
                it.author=bookDTO.author
                it.category=bookDTO.category
                bookRepository.save(it)
                BookDTO( it.id,it.name,it.category,it.author)
            }

        }else{
            throw BookNotFoundException("Book not available with Id $bookId")

        }

    }

    fun deleteBook(bookId: Int){
        val existingBook=bookRepository.findById(bookId)
          if(existingBook.isPresent){
                bookRepository.deleteById(bookId) }else{
            throw BookNotFoundException("Book not available with Id $bookId")

        }
    }

}
