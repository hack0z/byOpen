package byopen.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import dyopen.lib.SystemLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "byOpen";
    private static final String SYSTEM_LIBRARY = "curl";
    private static final String SYMBOL_NAME = "curl_version";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button origin_loadLibraryBtn = (Button) findViewById(R.id.origin_loadLibrary);
        origin_loadLibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    System.loadLibrary(SYSTEM_LIBRARY);
                    if (NativeTest.validFromMaps(SYSTEM_LIBRARY)) {
                        origin_loadLibraryBtn.setText("load ok!");
                    } else {
                        origin_loadLibraryBtn.setText("load failed!");
                    }
                } catch (Throwable e) {
                    origin_loadLibraryBtn.setText("load failed!");
                }
            }
        });

        final Button byopen_loadLibraryBtn = (Button) findViewById(R.id.byopen_loadLibrary);
        byopen_loadLibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemLoader.loadLibrary(SYSTEM_LIBRARY) && NativeTest.validFromMaps(SYSTEM_LIBRARY)) {
                    byopen_loadLibraryBtn.setText("load ok!");
                } else {
                    byopen_loadLibraryBtn.setText("load failed!");
                }
            }
        });

        final Button dlopenBtn = (Button) findViewById(R.id.byopen_dlopen);
        dlopenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NativeTest.loadLibrary(SYSTEM_LIBRARY, SYMBOL_NAME) && NativeTest.validFromMaps(SYSTEM_LIBRARY)) {
                    dlopenBtn.setText("load ok!");
                } else {
                    dlopenBtn.setText("load failed!");
                }
            }
        });

        final Button validBtn = (Button) findViewById(R.id.valid_from_maps);
        validBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NativeTest.validFromMaps(SYSTEM_LIBRARY)) {
                    validBtn.setText("valid ok!");
                } else {
                    validBtn.setText("valid failed!");
                }
            }
        });
    }
}