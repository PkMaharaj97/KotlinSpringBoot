package pk.service.spring.exception

class BookNotFoundException(message:String="Data not found") : RuntimeException(message)
class PrinterNotValidException(message:String="Invalid Printer") : RuntimeException(message)
