
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,Html,Lang,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String)(content: Html)(implicit lang:Lang):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.52*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*9.59*/routes/*9.65*/.Assets.at("images/favicon.png"))),format.raw/*9.97*/("""">
        <script src=""""),_display_(Seq[Any](/*10.23*/routes/*10.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*10.74*/("""" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
			"""),_display_(Seq[Any](/*14.5*/content)),format.raw/*14.12*/("""
		</div>
    </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html,lang:Lang): play.api.templates.HtmlFormat.Appendable = apply(title)(content)(lang)
    
    def f:((String) => (Html) => (Lang) => play.api.templates.HtmlFormat.Appendable) = (title) => (content) => (lang) => apply(title)(content)(lang)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Jun 09 14:00:13 EDT 2014
                    SOURCE: C:/Users/trofi_000/Downloads/play-2.2.3/play-2.2.3/People_2/app/views/main.scala.html
                    HASH: e3ad45b5beaf65361eb21fa846b6a862ca7900cb
                    MATRIX: 783->1|927->51|1015->104|1041->109|1138->171|1152->177|1207->211|1303->272|1317->278|1370->310|1431->335|1446->341|1513->386|1642->480|1671->487
                    LINES: 26->1|29->1|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|42->14|42->14
                    -- GENERATED --
                */
            