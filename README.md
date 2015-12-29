# Build It Bigger (includes in Udacity Nanodergree)

In this project, I've created an app with multiple flavors that uses
multiple libraries and Google Could Endpoints. The app consists
of four modules. A Java library that provides jokes, a Google Could Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

##What Have I Done?

I have learnt the role of Gradle in building Android Apps and how to use Gradle to manage apps of increasing complexity. I've also learnt:

* Add free and paid flavors to an app, and set up a build to share code between them
* Factor reusable functionality into a Java library
* Factor reusable Android functionality into an Android library
* Configure a multi project build to compile my libraries and app
* Use the Gradle App Engine plugin to deploy a backend
* Configure an integration test suite that runs against the local App Engine development server
* The free app variant displays interstitial ads between the main activity and the joke-displaying activity.
* The app displays a loading indicator while the joke is being fetched from the server.
* The root build.gradle file contains a task that will start up the GCE development server, run all Android tests, then shutdown the development server.
