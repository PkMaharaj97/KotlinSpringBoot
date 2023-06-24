package pk.service.spring.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BookDTO (
    val id:Int?,
    @get:NotBlank(message = "Book name should not be empty")
    val name:String,
    val category:String,
    @get:NotBlank(message = "Author name should not be empty")
    val author:String,
    @get:NotNull(message = "printerId must not be null")
    val printerId: Int? = null

)
