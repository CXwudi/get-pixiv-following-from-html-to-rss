package mikufan.cx.getmypixivfollowing

import mikufan.cx.getmypixivfollowing.service.MainService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GetMyPixivFollowingApplication

fun main(args: Array<String>) {
  val context = runApplication<GetMyPixivFollowingApplication>(*args)
  context.getBean(MainService::class.java).run()
}
