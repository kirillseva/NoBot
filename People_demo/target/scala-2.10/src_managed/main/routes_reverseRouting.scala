// @SOURCE:C:/Users/trofi_000/Downloads/play-2.2.3/play-2.2.3/People_2/conf/routes
// @HASH:284acf5f383f51ef81c4ea574bae666a93a5170e
// @DATE:Mon Jun 09 13:02:50 EDT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:9
// @LINE:6
package controllers {

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:6
class ReversePeopleControllerScala {
    

// @LINE:6
def showAllPeople(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "people")
}
                                                
    
}
                          
}
                  


// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:6
class ReversePeopleControllerScala {
    

// @LINE:6
def showAllPeople : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.PeopleControllerScala.showAllPeople",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "people"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:6
class ReversePeopleControllerScala {
    

// @LINE:6
def showAllPeople(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.PeopleControllerScala.showAllPeople(), HandlerDef(this, "controllers.PeopleControllerScala", "showAllPeople", Seq(), "GET", """ Home page""", _prefix + """people""")
)
                      
    
}
                          
}
        
    