package mikufan.cx.getmypixivfollowing.config

import org.jeasy.batch.core.job.JobExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @date 2021-05-14
 * @author CX无敌
 */
@Configuration
class JobConfig {
  @Bean
  fun getExecutor(): JobExecutor = JobExecutor()
}