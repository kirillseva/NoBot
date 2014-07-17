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

object CalendarC extends Controller {
  val form = Form(
    single(
      "roomNo" -> text
    )
  )

  def calendarEventQuery = Action {
    Ok(views.html.calendarEventQuery())
  }

  def selectRoom = Action { implicit request =>
    val (selectedRoomNo) = form.bindFromRequest.get
    val jsonString = Json.toJson(selectedRoomNo)

    Ok(jsonString)
  }


  def GoogleCalendar263 = Action.async { implicit request =>

      val timeMin = "2014-07-11T00%3A00%3A00-04%3A00"
      val timeMax = "2014-07-11T23%3A59%3A59-04%3A00"
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

}
