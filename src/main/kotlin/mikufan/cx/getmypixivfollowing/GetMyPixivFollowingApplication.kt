package mikufan.cx.getmypixivfollowing

import mikufan.cx.getmypixivfollowing.service.MainService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.nativex.hint.TypeAccess
import org.springframework.nativex.hint.TypeHint

@TypeHint(types = [Regex::class], access = [TypeAccess.AUTO_DETECT])
@SpringBootApplication
class GetMyPixivFollowingApplication

fun main(args: Array<String>) {
  val context = runApplication<GetMyPixivFollowingApplication>(*args)
  context.getBean(MainService::class.java).run()
}
