package controllers

import play.api.mvc.{Action, Controller}
import play.api._
import play.api.mvc._
import play.api.Play.current
import scala.concurrent._
import scala.concurrent.duration._
import play.api.libs.ws._
import scala.concurrent.Future
import ExecutionContext.Implicits.global
import play.api.libs.json._
import play.api.libs.json.JsResult
import play.api.data._
import play.api.data.Forms._
import java.util._;
import java.text._;

object CalendarC extends Controller {

  //Get current date
  var cal = Calendar.getInstance();
  cal.add(Calendar.DATE, 0);
  var format1 = new SimpleDateFormat("yyyy-MM-dd");
  var currentDate = format1.format(cal.getTime());

  //Set upper and lower time boundaries for google calendar api window
  val timeMin = currentDate + "T00%3A00%3A00-04%3A00"
  val timeMax = currentDate + "T23%3A59%3A59-04%3A00"

  //Google calendar API requests for 300 SCR meeting rooms

  def googleCalendarSCR261 = Action.async { implicit request =>

      val URL = "https://www.googleapis.com/calendar/v3/calendars/2fsakh49r55e0lpr05gr3oikcc@group.calendar.google.com/events?orderBy=startTime&singleEvents=true&timeMax=" + timeMax + "&timeMin=" + timeMin + "&fields=items(creator%2FdisplayName%2Cend%2Cstart%2Csummary)&key=AIzaSyDCxa7PIXSYbYv4ERy1yP_FtW9nu4aUp2A"

      WS.url(URL).get.map { response =>
      val res = response.json
      Ok(Json.obj(
        "displayName" -> (res \\ "displayName").map(_.as[String]),
        "summary" -> (res \\ "summary").map(_.as[String]),
        "dateTime" -> (res \\ "dateTime" ).map(_.as[String])
      ))
      }
  }

  def googleCalendarSCR262 = Action.async { implicit request =>

      val URL = "https://www.googleapis.com/calendar/v3/calendars/ptab8do44b5votmejo9k4b75g0@group.calendar.google.com/events?orderBy=startTime&singleEvents=true&timeMax=" + timeMax + "&timeMin=" + timeMin + "&fields=items(creator%2FdisplayName%2Cend%2Cstart%2Csummary)&key=AIzaSyDCxa7PIXSYbYv4ERy1yP_FtW9nu4aUp2A"

      WS.url(URL).get.map { response =>
      val res = response.json
      Ok(Json.obj(
        "displayName" -> (res \\ "displayName").map(_.as[String]),
        "summary" -> (res \\ "summary").map(_.as[String]),
        "dateTime" -> (res \\ "dateTime" ).map(_.as[String])
      ))
      }
  }

  def googleCalendarSCR263 = Action.async { implicit request =>

      val URL = "https://www.googleapis.com/calendar/v3/calendars/asmgtes27669vbdibsiqd6kacs%40group.calendar.google.com/events?orderBy=startTime&singleEvents=true&timeMax=" + timeMax + "&timeMin=" + timeMin + "&fields=items(creator%2FdisplayName%2Cend%2Cstart%2Csummary)&key=AIzaSyDCxa7PIXSYbYv4ERy1yP_FtW9nu4aUp2A"

      WS.url(URL).get.map { response =>
      val res = response.json
      Ok(Json.obj(
        "displayName" -> (res \\ "displayName").map(_.as[String]),
        "summary" -> (res \\ "summary").map(_.as[String]),
        "dateTime" -> (res \\ "dateTime" ).map(_.as[String])
      ))
      }
  }

  def googleCalendarSCR281 = Action.async { implicit request =>

      val URL = "https://www.googleapis.com/calendar/v3/calendars/g006l1jlnlhn12u8e08j3go37o@group.calendar.google.com/events?orderBy=startTime&singleEvents=true&timeMax=" + timeMax + "&timeMin=" + timeMin + "&fields=items(creator%2FdisplayName%2Cend%2Cstart%2Csummary)&key=AIzaSyDCxa7PIXSYbYv4ERy1yP_FtW9nu4aUp2A"

      WS.url(URL).get.map { response =>
      val res = response.json
      Ok(Json.obj(
        "displayName" -> (res \\ "displayName").map(_.as[String]),
        "summary" -> (res \\ "summary").map(_.as[String]),
        "dateTime" -> (res \\ "dateTime" ).map(_.as[String])
      ))
      }
  }

  def googleCalendarSCR282 = Action.async { implicit request =>

      val URL = "https://www.googleapis.com/calendar/v3/calendars/7q9cvf3991bkcp71aio3j3dcrs@group.calendar.google.com/events?orderBy=startTime&singleEvents=true&timeMax=" + timeMax + "&timeMin=" + timeMin + "&fields=items(creator%2FdisplayName%2Cend%2Cstart%2Csummary)&key=AIzaSyDCxa7PIXSYbYv4ERy1yP_FtW9nu4aUp2A"

      WS.url(URL).get.map { response =>
      val res = response.json
      Ok(Json.obj(
        "displayName" -> (res \\ "displayName").map(_.as[String]),
        "summary" -> (res \\ "summary").map(_.as[String]),
        "dateTime" -> (res \\ "dateTime" ).map(_.as[String])
      ))
      }
  }

  def googleCalendarCoachLibrary = Action.async { implicit request =>

      val URL = "https://www.googleapis.com/calendar/v3/calendars/fflur2bq21hlamj26ufllug5kg@group.calendar.google.com/events?orderBy=startTime&singleEvents=true&timeMax=" + timeMax + "&timeMin=" + timeMin + "&fields=items(creator%2FdisplayName%2Cend%2Cstart%2Csummary)&key=AIzaSyDCxa7PIXSYbYv4ERy1yP_FtW9nu4aUp2A"

      WS.url(URL).get.map { response =>
      val res = response.json
      Ok(Json.obj(
        "displayName" -> (res \\ "displayName").map(_.as[String]),
        "summary" -> (res \\ "summary").map(_.as[String]),
        "dateTime" -> (res \\ "dateTime" ).map(_.as[String])
      ))
      }
  }


}
