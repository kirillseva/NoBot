Installation instructions for Mac
================================================================================================

Here are the steps you need to do if you want to start developing for CoBot.

1. First, check out the code from github

        git clone git@github.com:kirillseva/NoBot.git
2. Change your working directory and mark activator as an executable file
        cd Nobot
        chmod +x activator
3. Start the server
        ./activator run
That's it! The server has started and you can access the application by pointing
you web-browser to *localhost:9000*

We used heroku in order to deploy the application. However, you can
choose your favorite deployment environment. Please refer to [Play documentation](http://www.playframework.com/documentation/2.3.x/Production)
for alternatives.
Here are the steps to get you started with heroku deployment.

1. Download and install [Heroku toolbelt](https://devcenter.heroku.com/articles/quickstart)
2. Create a new heroku app
        heroku create
Heroku will generate a random funny name for your application.
It will look something like *http://limitless-refuge-3721.herokuapp.com/*
You will be able to change it later in heroku dashboard.
3. Install clearDB plugin and new relic plugin
        heroku addons:add cleardb:ignite
        heroku addons:add newrelic
4. Change the licence key and other parameters in */lib/newrelic.yml*
Get the necessary config from [here](http://newrelic.com/java#installation)
5. Get the database URI
        >heroku config
        === limitless-refuge-3721 Config Vars
        CLEARDB_DATABASE_URL:  mysql://bd5a22e486aefb:35be3ea6@us-cdbr-iron-east-01.cleardb.net/heroku_a69522653b1ad6e?reconnect=true
        NEW_RELIC_LICENSE_KEY: 401000482f63839c5e3b2c2196cfc83632a2607d
        NEW_RELIC_LOG:         stdout
Take a note of **CLEARDB_DATABASE_URL**. It is written in the form
        mysql://USERNAME:PASSWORD@hostname?reconnect=true
Write down your username, password and hostname. You will need them in the next step
6. Export those pieces of data to system variables.
        heroku config:set DB_URL=jdbc:mysql://hostname
        heroku config:set DB_PASS=PASSWORD
        heroku config:set DB_USER=USERNAME
Don't forget to replace the hostname, the password and the username with your credentials obtained in step 5.
8. Deploy!
        git push heroku master
