package pk.service.spring.entity

import jakarta.persistence.*

@Entity
@Table(name = "Printers")
data class Printer (
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id:Int?,
    var name:String,
    @OneToMany(
        mappedBy = "printer",
        cascade =[CascadeType.ALL],
        orphanRemoval =true
    )
    var books:List<Book> = mutableListOf()




)