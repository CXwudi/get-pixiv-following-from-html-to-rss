package mikufan.cx.getmypixivfollowing.component

import mu.KotlinLogging
import org.jeasy.batch.core.processor.RecordProcessor
import org.jeasy.batch.core.record.Record
import org.jeasy.batch.core.record.StringRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader
import java.nio.file.Path

/**
 * @date 2021-05-14
 * @author CX无敌
 */
@Component
class FollowingUsersExtractor(
  @Value("\${config.pixiv-html-tag-contain-string}") val matchingTarget: String
) : RecordProcessor<Path, String> {

  /**
   * extracting the target line of html from each file
   */
  override fun processRecord(record: Record<Path>): Record<String> {
    val reader = BufferedReader(FileReader(record.payload.toFile()))
    log.debug { "Processing #${record.header.number} html file: ${record.payload.fileName}" }

    val followingUsersHtmlTags = reader.useLines { seq ->
      seq.filter { it.contains(matchingTarget) }
        .map { it.trim() }
        .onEachIndexed { index, content -> log.debug { "  $index = $content" } }
        .joinToString("\n")
    }
    return StringRecord(record.header, followingUsersHtmlTags)
  }
}

private val log = KotlinLogging.logger {}
