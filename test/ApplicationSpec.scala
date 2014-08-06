import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.specs2.matcher.JsonMatchers
import play.api.libs.json._

import play.api.test._
import play.api.test.Helpers._

import models._

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


    "return by name" in {
      running(FakeApplication()) {
        val Some(person) = People.returnDataByName("Jane", "Miller")
        person.first_name must beSome("Jane")
        person.last_name must beSome("Miller")
        person.status must beSome("Associate Director of International Initiatives and Programs Manager")
        person.office must beSome(273)
      }
    }

    "return all data by name" in {
      running(FakeApplication()) {
        val Some(person) = People.returnDataByName("Jane", "Miller")
        person.first_name must beSome("Jane")
        person.last_name must beSome("Miller")
        person.status must beSome("Associate Director of International Initiatives and Programs Manager")
        person.office must beSome(273)
        person.phone must beSome("(412) 268-4359")
        person.email must beSome("jmiller@cmu.edu")
        person.additional_info must beSome("""<a href="http://mse.isri.cmu.edu/software-engineering/Staff/miller-jane.html" target="_blank">More info</a>""")
      }
    }

    "return office by name" in {
      running(FakeApplication()) {
        val Some(person) = People.returnOfficeByName("Jane", "Miller")
        person must equalTo(273)
      }
    }

    "return phone by name" in {
      running(FakeApplication()) {
        val Some(person) = People.returnPhoneByName("Jane", "Miller")
        person must equalTo("(412) 268-4359")
      }
    }

    "should get person info" in {
      running(FakeApplication()) {
        var result  = route( FakeRequest(POST, "/selectLocation").withSession("email"->"CEO@cobot.com")).get
        contentType(result) must beSome("text/html")
      }
    }

    "should get location or phone info" in {
      running(FakeApplication()) {
        var result  = route( FakeRequest(POST, "/selectPerson").withSession("email"->"CEO@cobot.com")).get
        contentType(result) must beSome("text/html")
      }
    }

    "weather model" in {
      running(FakeApplication()) {
        val someweather = Weather.stubWeather
        someweather.tempC must equalTo(18)
      }
    }

    "time model" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        var res = Time.readTime("test")
        res.get.time must equalTo (Time.default)
        Time.saveTime(Time("test", "test"))
        res = Time.readTime("test")
        res.get.time must equalTo ("test")
      }
    }
  }
}
