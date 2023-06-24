package pk.service.spring.dto
import jakarta.validation.constraints.NotBlank

data class PrinterDTO(
    val id:Int?,
    @get:NotBlank(message = "Printer name should not be empty")
    var name:String
)
