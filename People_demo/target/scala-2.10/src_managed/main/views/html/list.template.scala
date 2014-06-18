
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
object list extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[List[People],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(allPeople: List[People]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.27*/(""" 

"""),_display_(Seq[Any](/*3.2*/main(Messages("application.name"))/*3.36*/ {_display_(Seq[Any](format.raw/*3.38*/("""
	<dl class ="allPeople">
		"""),_display_(Seq[Any](/*5.4*/for(person <- allPeople) yield /*5.28*/ {_display_(Seq[Any](format.raw/*5.30*/("""
			<dt>"""),_display_(Seq[Any](/*6.9*/person/*6.15*/.first_name)),format.raw/*6.26*/("""</dt>
			<dt>"""),_display_(Seq[Any](/*7.9*/person/*7.15*/.last_name)),format.raw/*7.25*/("""</dt>
			<dt>"""),_display_(Seq[Any](/*8.9*/person/*8.15*/.status)),format.raw/*8.22*/("""</dt>
			<dt>"""),_display_(Seq[Any](/*9.9*/person/*9.15*/.office)),format.raw/*9.22*/("""</dt>
			<dt>"""),_display_(Seq[Any](/*10.9*/person/*10.15*/.phone)),format.raw/*10.21*/("""</dt>
			<dt>"""),_display_(Seq[Any](/*11.9*/person/*11.15*/.email)),format.raw/*11.21*/("""</dt>
			<dd>"""),_display_(Seq[Any](/*12.9*/person/*12.15*/.additional_info)),format.raw/*12.31*/("""</dd>
			<p>
			<hr>
		""")))})),format.raw/*15.4*/("""
	</dl>
""")))})))}
    }
    
    def render(allPeople:List[People]): play.api.templates.HtmlFormat.Appendable = apply(allPeople)
    
    def f:((List[People]) => play.api.templates.HtmlFormat.Appendable) = (allPeople) => apply(allPeople)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Jun 09 14:11:23 EDT 2014
                    SOURCE: C:/Users/trofi_000/Downloads/play-2.2.3/play-2.2.3/People_2/app/views/list.scala.html
                    HASH: 87762290fd27585b0d429aaa6f87195069db172d
                    MATRIX: 779->1|898->26|938->32|980->66|1019->68|1084->99|1123->123|1162->125|1206->135|1220->141|1252->152|1301->167|1315->173|1346->183|1395->198|1409->204|1437->211|1486->226|1500->232|1528->239|1578->254|1593->260|1621->266|1671->281|1686->287|1714->293|1764->308|1779->314|1817->330|1875->357
                    LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5|34->6|34->6|34->6|35->7|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|43->15
                    -- GENERATED --
                */
            