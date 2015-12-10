package maximyudin.lib;

public class JokeFactory {
    public static Joke getSomeJoke() {
        Joke joke = new Joke();
        joke.setJokeText("This is totally a funny joke");
        return joke;
    }
}
