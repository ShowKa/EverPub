package com.showka.everpub.publish

import java.io.File

interface PublishService {
    fun publish(publisher: Publisher, filePath: String): File {
        publisher.generateFile(filePath)
        return File(filePath)
    }
}