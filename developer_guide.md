# How to develop a widget for CoBot website?

The CoBot framework follows the MVC architectural pattern that guides developers
and makes creating new widgets a simple and manageable task.
Developing a widget consists of the following steps:

1. Create a model, that would be responsible for storing/serving the data.
The model is typically responsible for dealing with the database.
For example, for the map widget we created a model that is used to store the robot's
location in the database and retrieving it.

2. Create a view. The view is what the user will see on the screen.
From a developer's perspective a view is an html template. Play framework
allows to generate html based on the request and the data that's served to the template.
For more information please refer to the [Play Framework's documentation](http://www.playframework.com/documentation/2.3.x/Home).

3. Register your view with the system by modifying the **widgetry.scala.html** view and the **Widget.scala** model.

4. Some functionality of your widget may require dynamic input or interaction with the user.
In order to create that you might need to create a controller that would process your request
and return the data in your preferred format (usually JSON), and javascript code that
would run on the client and based on the user's interaction with the system would send requests to the server.
For example, in the map widget, when the user clicks on the map we get the location of the click,
transform it to the map coordinates and send these coordinates to the server to update robot's location.

This might sound more complicated than it actually is. So, let's develop a
simple widget and see how easy it is in fact!
