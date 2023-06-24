package pk.service.spring.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import pk.service.spring.entity.Book

interface BookRepository :CrudRepository<Book,Int>{

    /**
     * Retriving books list based on the name containing by using JPA Query
     */
    fun findByNameContaining(bookName:String):List<Book>

    /**
     * Retriving books list based on the name containing by using Native Sql Query
     */

    @Query(value = "SELECT * FROM BOOKS where name like %?1%", nativeQuery = true)
    fun findBooksByNameContaining(bookName:String):List<Book>
}