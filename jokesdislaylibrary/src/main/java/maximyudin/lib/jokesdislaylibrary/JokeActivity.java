package maximyudin.lib.jokesdislaylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public final static String JOKE = "JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(JOKE)) {
            ((TextView) findViewById(R.id.tvJoke)).setText(bundle.getString(JOKE));
        }
    }
}
