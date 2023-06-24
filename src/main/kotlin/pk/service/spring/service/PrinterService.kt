package pk.service.spring.service

import org.springframework.stereotype.Service
import pk.service.spring.dto.PrinterDTO
import pk.service.spring.entity.Printer
import pk.service.spring.repository.PrinterRepository
import java.util.Optional


@Service
class PrinterService (val printerRepository: PrinterRepository) {


    fun addNewPrinter(printerDTO: PrinterDTO): PrinterDTO {
        val printerEntity = printerDTO.let {
            Printer(it.id, it.name)
        }
        printerRepository.save(printerEntity)

        return printerEntity.let {
            PrinterDTO(it.id, it.name)
        }
    }


    fun findPrinterById(printerId: Int): Optional<Printer> {
        return printerRepository.findById(printerId)
    }
}