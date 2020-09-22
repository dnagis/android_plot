/*
 * adb uninstall vvnx.helloplot
 * adb install out/target/product/generic_arm64/system/app/HelloPlot/HelloPlot.apk
 * 
 * 
 * 
 */

package vvnx.helloplot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;



public class HelloPlot extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View view = getLayoutInflater().inflate(R.layout.hello_plot, null);
        setContentView(view);
    }
}

