/*
 *
 * This file: Calendar ControllerC.scala
 * Programmer: Sammy Mudede (smudede@andrew.cmu.edu)
 * Project: MSIT-SE CoBot Team
 * Description: Basic CoBot controller functionality - pulling event details from
 *  MSE google calendars.
 * Last Modified: 6/8/14
 * Compiler: javac & scala template engine ?
 *
 */

package controllers

import play.api.mvc.{Action, Controller}
import play.api._
import play.api.mvc._
import scala.concurrent._
import scala.concurrent.duration._
import play.api.libs.ws._
import scala.concurrent.Future
import ExecutionContext.Implicits.global
import play.api.libs.json._
import play.api.libs.json.JsResult

object CalendarC extends Controller {
  def show = Action.async { implicit request =>

    val timeMin = "2014-06-13T00%3A00%3A00-04%3A00"
    val timeMax = "2014-06-13T23%3A59%3A59-04%3A00"
    val URL = "https://www.googleapis.com/calendar/v3/calendars/asmgtes27669vbdibsiqd6kacs%40group.calendar.google.com/events?orderBy=startTime&singleEvents=true&timeMax=" + timeMax + "&timeMin=" + timeMin + "&fields=items(creator%2FdisplayName%2Cend%2Cstart%2Csummary)&key=AIzaSyDCxa7PIXSYbYv4ERy1yP_FtW9nu4aUp2A"

    WS.url(URL).get.map { response =>

      val Lcreator = (response.json \\ "displayName").map(_.as[String])
      val LeventName = (response.json \\ "summary").map(_.as[String])
      val LTimes = (response.json \\ "dateTime").map(_.as[String])

      Ok(views.html.app(Lcreator, LeventName, LTimes))
//      Ok(response.json)
    }
  }
}
