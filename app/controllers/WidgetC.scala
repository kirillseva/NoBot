package controllers

import play.api._
import play.api.mvc._
import play.api.data._

import models._
import views._

object WidgetC extends Controller {
  def test(path: String, email: String) = Action { implicit request =>
    Ok(Widget.getLayout(path, email))
  }
}
