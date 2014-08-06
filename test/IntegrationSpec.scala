import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.specs2.matcher.JsonMatchers
import play.api.libs.json._

import play.api.test._
import play.api.test.Helpers._

/**
* add your integration spec here.
* An integration test will fire up a whole play application in a real (or headless) browser
*/


@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  "Application" should {

    "work from within a browser" in new WithBrowser {

      browser.goTo("http://localhost:" + port)

      browser.pageSource must contain("Cobot Nobotics")
    }
  }

  "require login" in {
    running(FakeApplication()) {
      val Some(result1) = route(FakeRequest(GET, "/"))
      status(result1) must equalTo(303)

      val Some(result) = route(FakeRequest(GET, "/").withSession("email"->"CEO@cobot.com"))
      status(result) must equalTo(200)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("Bradley")

      val Some(result2) = route(FakeRequest(GET, "/logout").withSession("email"->"CEO@cobot.com"))
      status(result2) must equalTo(303)
    }
  }

  "robotC get location" in {
    running(FakeApplication()) {
      val Some(result) = route(FakeRequest(POST, "/getLocation"))
      status(result) must equalTo(200)
      contentType(result) must beSome("application/json")
    }
  }

  "robotC set location" in {
    running(FakeApplication()) {
      val json = Json.obj(
        "x" -> JsNumber(1000),
        "y" -> JsNumber(1000)
      )
      val req = FakeRequest(
        method = "POST",
        uri = "/setLocation",
        headers = FakeHeaders(
          Seq("Content-type"->Seq("application/json"))
        ),
        body =  json
      )

      val Some(result) = route(req)
      status(result) must equalTo(200)
      contentType(result) must beSome("application/json")
    }
  }

}
