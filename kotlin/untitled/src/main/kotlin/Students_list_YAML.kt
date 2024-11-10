package main.kotlin

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

class Students_list_YAML : Student_list(), StudentListStrategy {
    override fun readFromFile(path: String) {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        data = mapper.readValue(File(path), mapper.typeFactory.constructCollectionType(MutableList::class.java, Student::class.java))
    }

    override fun writeToFile(path: String) {
        val file = File(path)
        val YAMLMapper = ObjectMapper(YAMLFactory())
        YAMLMapper.writeValue(file, data)
    }
}
