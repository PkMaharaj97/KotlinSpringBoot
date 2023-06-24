package pk.service.spring.repository

import org.springframework.data.repository.CrudRepository
import pk.service.spring.entity.Printer

interface PrinterRepository:CrudRepository<Printer,Int> {
    fun findPrinterByName(name : String) : Printer

}