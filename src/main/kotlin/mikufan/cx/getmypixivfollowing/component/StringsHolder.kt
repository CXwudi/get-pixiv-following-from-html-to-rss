package mikufan.cx.getmypixivfollowing.component

import org.jeasy.batch.core.reader.StringRecordReader
import org.jeasy.batch.core.writer.RecordWriter
import org.springframework.stereotype.Component

/**
 * @date 2021-05-14
 * @author CX无敌
 */
@Component
class StringsHolder {
  lateinit var holdedString: String

  fun createWriterWithClosure() = RecordWriter<String>{
      batch -> holdedString = batch.joinToString("\n") { it.payload }
  }

  fun createReaderWithClosure() = StringRecordReader(holdedString)
}