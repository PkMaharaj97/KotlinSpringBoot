package pk.service.spring.entity

import jakarta.persistence.*

@Entity
@Table(name = "Books")
data class Book(
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    val id:Int?,
    var name:String,
    var category:String,
    var author:String,
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "Printer_ID", nullable = false)
    val printer: Printer? = null
){
    override fun toString(): String {
        return "Book(id=$id, name=$name, category=$category, author=$author, printer=${printer?.id})"
    }
}
