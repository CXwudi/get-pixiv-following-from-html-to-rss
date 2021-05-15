package mikufan.cx.getmypixivfollowing.component

import mu.KotlinLogging
import org.jeasy.batch.core.mapper.RecordMapper
import org.jeasy.batch.core.record.Record
import org.jeasy.batch.core.record.StringRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.text.MessageFormat

/**
 * @date 2021-05-14
 * @author CX无敌
 */
@Component
class ToRssMapper(
  @Value("\${config.regex-to-html-tag}") val regex: Regex,
  @Value("\${config.rss-opml-tag-template}") val rssTemplate: String
): RecordMapper<String, String> {

  override fun processRecord(record: Record<String>): Record<String> {
    val groupValues = regex.find(record.payload)?.groupValues
      ?: throw RuntimeException("Fail to match regex ${regex.pattern} for ${record.payload}").also {
        log.error(it) { "${it.message}" }
    }
    // [0] is the entire match, so starting from [1]
    val rssStr = MessageFormat.format(rssTemplate, groupValues[1], groupValues[2].normalize())
    log.debug { "  #${record.header.number} rss str = $rssStr" }
    return StringRecord(record.header, rssStr)
  }

  private fun String.normalize(): String = this.replace("\"", "")
}
private val log = KotlinLogging.logger {}