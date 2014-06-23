import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */


@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "go to login page without credentials" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val result  = route( FakeRequest( GET, "/")).get
        status(result) must equalTo(303)
      }
    }

    "show dashboard page if login with credentials" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val result  = route( FakeRequest( GET, "/").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
      }
    }

  }
}
