package mikufan.cx.getmypixivfollowing.service

import mikufan.cx.getmypixivfollowing.component.FollowingUsersExtractor
import mikufan.cx.getmypixivfollowing.component.StringsHolder
import mikufan.cx.getmypixivfollowing.component.ToRssMapper
import mikufan.cx.inlinelogging.KInlineLogging
import org.jeasy.batch.core.job.JobBuilder
import org.jeasy.batch.core.job.JobExecutor
import org.jeasy.batch.core.job.JobReport
import org.jeasy.batch.core.reader.FileRecordReader
import org.jeasy.batch.core.writer.FileRecordWriter
import org.springframework.stereotype.Service
import java.nio.file.Path

/**
 * @date 2021-05-14
 * @author CX无敌
 */
private val log = KInlineLogging.logger()

@Service
class MainService(
  val jobExecutor: JobExecutor,
  val fileRecordReader: FileRecordReader,
  val followingUsersExtractor: FollowingUsersExtractor,
  val stringsHolder: StringsHolder,
  val toRssMapper: ToRssMapper,
  val fileRecordWriter: FileRecordWriter
) : Runnable {

  override fun run() {
    // 1. read all files and write them into lines
    val job1 = JobBuilder<Path, String>()
      .named("read-followers-jobs")
      .reader(fileRecordReader)
      .processor(followingUsersExtractor)
      .writer(stringsHolder.createWriterWithClosure())
      .build()

    val jobReport1: JobReport = jobExecutor.execute(job1)
    log.info { "Finishing job 1: \n$jobReport1" }
    log.info { "Starting job 2" }

    // 2. do the things
    val job2 = JobBuilder<String, String>()
      .named("transform-to-partial-opml-jobs")
      .reader(stringsHolder.createReaderWithClosure())
      .mapper(toRssMapper)
      .writer(fileRecordWriter)
      .build()
    val jobReport2 = jobExecutor.execute(job2)
    log.info { "Finishing job 2: \n$jobReport2" }

    jobExecutor.close()
  }
}
