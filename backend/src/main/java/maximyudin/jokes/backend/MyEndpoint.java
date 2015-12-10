/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package maximyudin.jokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import maximyudin.lib.Joke;
import maximyudin.lib.JokeFactory;

/** An endpoint class we are exposing */
@Api(
  name = "jokeApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.jokes.maximyudin",
    ownerName = "backend.jokes.maximyudin",
    packagePath=""
  )
)
public class MyEndpoint {
    /**
     * A simple endpoint method that return some joke
     */
    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        return JokeFactory.getSomeJoke();
    }
}
