package models
import play.api.mvc._
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class Context(widgets: Seq[Widget], request: Request[AnyContent]) extends WrappedRequest(request)
