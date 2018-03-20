
# read

Started with working out the ideas with Faiz.
Trying to work out bounding box, group complexity, 
location. 
* started with pseudo-code and then now I am just trying 
to translate that
* faiz has the uml diagram, was not present. meeting later
* having git issues(adding multiple remotes)
* github working, trying to fix bit-bucket
* Test BB two

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

You can also use Gradle in a Terminal window:

    $ gradlew testDebug   # on a Windows PC
    $ ./gradlew testDebug # on a Mac or Linux PC

You can view the resulting test reports in HTML by opening this file in your browser:

    app/build/reports/tests/debug/index.html

(So far, this example does not include any Android instrumentation tests.)
