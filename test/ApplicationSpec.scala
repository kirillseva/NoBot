import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.specs2.matcher.JsonMatchers
import play.api.libs.json._

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

    "should get calendar info for library" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var result  = route( FakeRequest( GET, "/calendarCCoachLibrary").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
        contentType(result) must beSome("application/json")
      }
    }

    "should get calendar info for SCR261" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var result  = route( FakeRequest( GET, "/calendarC261").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
        contentType(result) must beSome("application/json")
      }
    }

    "should get calendar info for SCR281" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var result  = route( FakeRequest( GET, "/calendarC281").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
        contentType(result) must beSome("application/json")
      }
    }

    "should get calendar info for SCR282" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var result  = route( FakeRequest( GET, "/calendarC282").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
        contentType(result) must beSome("application/json")
      }
    }

    "should get calendar info for SCR262" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var result  = route( FakeRequest( GET, "/calendarC262").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
        contentType(result) must beSome("application/json")
      }
    }

    "should get calendar info for SCR263" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var result  = route( FakeRequest( GET, "/calendarC263").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
        contentType(result) must beSome("application/json")
      }
    }

    "should get weather info" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var result  = route( FakeRequest( GET, "/weatherC").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
        contentType(result) must beSome("application/json")
      }
    }
	
	   "should return data about the person from database" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var result  = route( FakeRequest( GET, "/selectPerson").withSession("email"->"CEO@cobot.com")).get
        status(result) must equalTo(200)
		contentType(result) must beSome("application/json")
      }
    }

  }
}
