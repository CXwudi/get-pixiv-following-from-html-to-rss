package mikufan.cx.getmypixivfollowing.config

import org.jeasy.batch.core.reader.FileRecordReader
import org.jeasy.batch.core.writer.FileRecordWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Path

/**
 * @date 2021-05-14
 * @author CX无敌
 */
@Configuration
class IOConfig {
  @Bean
  fun getFilesReader(@Value("\${io.input-directory}") inputDir: Path) = FileRecordReader(inputDir)

  @Bean
  fun getFileWriter(@Value("\${io.output-file}") outputFile: Path) = FileRecordWriter(outputFile)
}