
# Development notes
Started with working out the ideas with Faiz.
Trying to work out bounding box, group complexity, 
location. 
* started with pseudo-code and then now I am just trying 
to translate that
* faiz has the uml diagram, was not present. meeting later
* having git issues(adding multiple remotes)
* github working, trying to fix bit-bucket
* Bitbucket problem solved - two remotes now 
* Added faiz on git and bitbucket 
* did a lot of the translating from pseudo-code to code. All patterns are good, 
there are two errors related to the polygon shape. Other than that, we should be good!
* update: Almost all tests are passing, been like a day of putting code from pseudo => as
* tried to merge and somehow deleted most files, fetching from bit bucket
* ok back to normal, should commit, havent in a while
* size and group were temp lost
* tried to fix a few basic things, the newer api has rendering issues
# Learning Objectives
* Familiarity with a simple graphical (output-only) Android project
* Basic design patterns and their purpose
    * Composite pattern
    * Decorator pattern
    * Visitor pattern
# Reading/Listening
* SE Radio episode 1
* APPP chapters 31 and 35

# Setting up the Environment

Check out the project using Android Studio. This creates the `local.properties` file
with the required line

    sdk.dir=<root folder of Android Studio's Android SDK installation>

# Running the Application

In Android Studio: `Run > Run app`

# Running the Tests

## Unit tests

In Android Studio:

* right-click on `app/java/edu...shapes (test)`, then choose `Run Tests in edu...`

You can also use Gradle in a Terminal window =>

    $ gradlew testDebug   # on a Windows PC
    $ ./gradlew testDebug # on a Mac or Linux PC

You can view the resulting test reports in HTML by opening this file in your browser:

    app/build/reports/tests/debug/index.html

(So far, this example does not include any Android instrumentation tests.)
