package com.showka.everpub.publish

import org.springframework.stereotype.Service
import java.io.File

@Service
class PublishService {
    fun publish(publisher: Publisher, filePath: String): File {
        publisher.generateFile(filePath)
        return File(filePath)
    }
}