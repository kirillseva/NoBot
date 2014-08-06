import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.specs2.matcher.JsonMatchers
import play.api.libs.json._

import play.api.test._
import play.api.test.Helpers._
import models._

/**
* add your integration spec here.
* An integration test will fire up a whole play application in a real (or headless) browser
*/


@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification with JsonMatchers {

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

  "authenticate fails" in {
    running(FakeApplication()) {
      val req = FakeRequest(
        method = "POST",
        uri = "/selectPerson",
        headers = FakeHeaders(
          Seq(
            "Content-type"->Seq("application/x-www-form-urlencoded"),
            "Referer"->  Seq("http://cobot-nobotics.herokuapp.com/login")
          )
        ),
        body = "bad+email"
      )

      val Some(result) = route(req)
      status(result) must equalTo(400)

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

  "personC select wrong person" in {
    running(FakeApplication()) {
      val json = Json.obj(
        "fname" -> JsString("Dave"),
        "lname" -> JsString("bang!")
      )
      val req = FakeRequest(
        method = "POST",
        uri = "/selectPerson",
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

  "personC select real person" in {
    running(FakeApplication()) {
      val json = Json.obj(
        "fname" -> JsString("Jane"),
        "lname" -> JsString("Miller")
      )
      val req = FakeRequest(
        method = "POST",
        uri = "/selectPerson",
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

  "personC set location" in {
    running(FakeApplication()) {
      val json = Json.obj(
        "loc_fname" -> JsString("Jane"),
        "loc_lname" -> JsString("Miller")
      )
      val req = FakeRequest(
        method = "POST",
        uri = "/selectLocation",
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

  "widgetC restore default" in {
    running(FakeApplication()) {
      val json = Json.obj(
        "task" -> JsString("test")
      )
      val req = FakeRequest(
        method = "POST",
        uri = "/restoreDefault",
        headers = FakeHeaders(
          Seq("Content-type"->Seq("application/json"))
        ),
        body =  json
      ).withSession("email"->"CEO@cobot.com")

      val Some(result) = route(req)
      status(result) must equalTo(200)
      contentType(result) must beSome("application/json")
    }
  }

  "widgetC save default" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val json1 = Json.parse("""{"task":"/","widgets":[{"id":"time","col":3,"row":2,"size_x":1,"size_y":1},{"id":"map","col":1,"row":1,"size_x":1,"size_y":1}]}""")
      val requ = FakeRequest(
        method = "POST",
        uri = "/saveLayout",
        headers = FakeHeaders(
          Seq("Content-type"->Seq("application/json"))
        ),
        body =  json1
      ).withSession("email"->"CEO@cobot.com")
      val Some(res) = route(requ)

      status(res) must equalTo(200)
      contentType(res) must beSome("application/json")
    }
  }

  "widgetC remove Widget" in {
    running(FakeApplication()) {
      val json = Json.obj(
        "name" -> JsString("fakeWidget"),
        "task" -> JsString("test")
      )
      val req = FakeRequest(
        method = "POST",
        uri = "/removeWidget",
        headers = FakeHeaders(
          Seq("Content-type"->Seq("application/json"))
        ),
        body =  json
      ).withSession("email"->"CEO@cobot.com")

      val Some(result) = route(req)
      status(result) must equalTo(200)
      contentType(result) must beSome("application/json")
    }
  }

  "timeC default time" in {
    running(FakeApplication()) {
      val Some(result2) = route(FakeRequest(GET, "/getTime").withSession("email"->"tester"))
      status(result2) must equalTo(200)
      contentType(result2) must beSome("application/json")
      contentAsString(result2) must /("savedtime" -> Time.default)
    }
  }

  "timeC add/read time record" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val json = Json.obj(
        "time" -> JsString("testTime")
      )
      val req = FakeRequest(
        method = "POST",
        uri = "/saveTime",
        headers = FakeHeaders(
          Seq("Content-type"->Seq("application/json"))
        ),
        body =  json
      ).withSession("email"->"tester")

      val Some(result) = route(req)
      status(result) must equalTo(200)
      contentType(result) must beSome("application/json")

      val Some(result2) = route(FakeRequest(GET, "/getTime").withSession("email"->"tester"))
      status(result2) must equalTo(200)
      contentType(result2) must beSome("application/json")
      contentAsString(result2) must /("savedtime" -> "testTime")
    }
  }



}
