package docent.example.com.smsforward;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class MainActivity extends AppCompatActivity {

    private TextView fromPstnEdit;
    private TextView toPstnEdit;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fromPstnEdit = (TextView) findViewById(R.id.edit_pstn_from);
        toPstnEdit = (TextView) findViewById(R.id.edit_pstn_to);
        prefs = getSharedPreferences("main", MODE_PRIVATE);
        Button saveConfigButton = (Button) findViewById(R.id.save_config_button);
        saveConfigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConfig();
            }
        });
    }

    private void saveConfig() {
        boolean hasErrors = false;
        if (isBlank(fromPstnEdit.getText())) {
            fromPstnEdit.setError("You should specify input pstn");
            hasErrors = true;
        }

        if (isBlank(toPstnEdit.getText())) {
            toPstnEdit.setError("You should specify output pstn");
            hasErrors = true;
        }
        if (hasErrors) {
            return;
        }
        prefs.edit()
                .putString("from", String.valueOf(fromPstnEdit.getText()))
                .putString("to", String.valueOf(toPstnEdit.getText()))
                .apply();
        Toast.makeText(getApplicationContext(), "Setting have been saved", Toast.LENGTH_SHORT).show();
    }
}
