# How to develop a widget for CoBot website?

The CoBot framework is built on top of Play Framework. Play is a web-development framework
written in Scala that allows to quickly and easily build fast and responsive
web application using Java or Scala. For more information please refer to the [Play Framework's documentation](http://www.playframework.com/documentation/2.3.x/Home).

The CoBot framework follows the [MVC architectural pattern](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) that guides developers
and makes creating new widgets a simple and manageable task.
Developing a widget consists of the following steps:

1. Create a model, that would be responsible for storing/serving the data.
The model is typically responsible for dealing with the database.
For example, for the map widget we created a model that is used to store the robot's
location in the database and retrieving it.

2. Create a view. The view is what the user will see on the screen.
From a developer's perspective a view is an html template. Play framework
allows to generate html based on the request and the data that's served to the template.
Please see the [official documentation](http://www.playframework.com/documentation/2.3.x/ScalaTemplates) for more information about how templates work.

3. Register your view with the system by modifying the **widgetry.scala.html** view and the **Widget.scala** model.

4. Some functionality of your widget may require dynamic input or interaction with the user.
In order to create that you might need to create a controller that would process your request
and return the data in your preferred format (usually JSON), and javascript code that
would run on the client and based on the user's interaction with the system would send requests to the server.
For example, in the map widget, when the user clicks on the map we get the location of the click,
transform it to the map coordinates and send these coordinates to the server to update robot's location.

This might sound more complicated than it actually is. So, let's develop a
simple widget and see how easy it is in fact!

Let's create a widget that will show current time on the screen.
Since time changes very often (every second),
it makes no sense to show any static content for this widget.
So, this widget doesn't require any model, or even a special controller, because time widget doesn't imply any interaction!
We'll start with the view.
Let's create a new file in the */views/* folder called **time.scala.html**.

Our view would look very simple.

```html
<div id="clock">00:00:00</div>
```

Yes, that's it for the view! We put *00:00:00* just as a placeholder text for testing.
Now, let's add this widget to our system. In order to do that, you need to add the following code to
**widgetry.scala.html** inside the switch statement.

```ruby
case "time" => {
  @views.html.time()
}
```
And we also need to register this widget with our system. Let's modify **Widget.scala** model and add **time** to the list of all widgets.

```ruby
val allWidgets = Seq(
  "weather",
  "map",
  "calendarEventQuery",
  "calendarEventNameQuery",
  "ShowPersonData",
  "ShowPersonLocation",
  "time"
)
```

Let's compile, launch the website and see our changes!

```bash
cd /path/to/Nobot
./activator run
```

And there it is! Add the time widget to your screen and save this layout.

<a href="http://tinypic.com?ref=ibbfo8" target="_blank"><img src="http://i59.tinypic.com/ibbfo8.png" border="0" alt="Image and video hosting by TinyPic" width="140" height="140"></a>

But this is not the time widget yet. Let's add some javascript code that would get current time of the client and would show it on the screen.
Let's add a new file to the directory */public/javascript/app/* called **time.js**.

```javascript
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    // add a zero in front of numbers<10
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('clock').innerHTML = h + ":" + m + ":" + s;
    t = setTimeout(function () {
        startTime()
    }, 500);
}

startTime();
```

Now, we need to add this script to our main view. Add this line at the end of the **main.scala.html**.

```html
<script type="text/javascript" src="@routes.Assets.at("javascripts/app/time.js")"></script>
```

That's it! Now, let's reload the page and see the result.
Hey, there is time right on the screen!

<a href="http://tinypic.com?ref=29dvgpf" target="_blank"><img src="http://i62.tinypic.com/29dvgpf.png" border="0" alt="Image and video hosting by TinyPic" width="140" height="140"></a>

But for now, it doesn't look quite good enough. Let's modify our javascript code to show
nicer result.
Modify the line in the script that changes html contents.

```javascript
document.getElementById('clock').innerHTML = "<h1>" + h + ":" + m + ":" + s + "</h1>";
```

Now, let's reload the page.

<a href="http://tinypic.com?ref=igiyq9" target="_blank"><img src="http://i61.tinypic.com/igiyq9.png" border="0" alt="Image and video hosting by TinyPic" width="140" height="140"></a>

And that's it! We've just created a widget that shows dynamic content on the screen
in under 10 minutes! And we have successfully added this widget to the existing system.

Now, let's add some more complexity to the widget. Let's make it so that
if a user clicks the time it will get stored in a database. Let's also show the latest saved time under the real time.

Let's modify the view a little bit. Add the following line to **time.scala.htlm**

```html
<dd> Latest saved time: </dd>
<dt id="savedTime">---<dt>
```

Now let's work on the backend side. First, we would need a database to store the data.
Open **1.sql** that is located in */conf/evolutions/default/*

Add this line in the section of down evolutions

```sql
drop table if exists savedtime;
```

And the following - to the up evolutions section
```sql
create table savedtime (
  email                     varchar(255) not null primary key,
  time                      varchar(255) not null
);
```

Now, let's create a model that would take care of all the database operations for us.
Create a new file under the models folder called **Time.scala**

Add the following content to the newly created file.

```java
package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import scala.language.postfixOps

case class Time (email: String, time: String)

object Time {

  // default value for when no value is stored in the DB yet
  var default = "No value. Yet."

  //a helper method that converts result of the reading from
  //a database to a case class, a native scala container
  val simple = {
    get[String]("savedtime.email") ~
    get[String]("savedtime.time") map {
      case email~time => Time(email, time)
    }
  }

  def readTime(email: String): Option[Time] = {
    var result = DB.withConnection { implicit connection =>
      SQL("select * from savedtime where email = {email}").on(
        "email" -> email
      ).as(Time.simple.singleOpt)
    }
    //if nothing found in database - return refault value
    if (result.isEmpty){
      result = Some(Time(email, default))
    }
    result
  }

  def saveTime(t: Time) {
    DB.withConnection { implicit connection =>
      SQL(
        """
          replace into savedtime (email, time) values
          ( {email}, {time} )
        """
      ).on(
        "email" -> t.email,
        "time" -> t.time
      ).executeUpdate()
  }
}
```

This model provides us with convinient methods to store and retrieve data from
the database, and also takes care of the case where there is no value in the DB.

Now, let's create the corresponding controller.
