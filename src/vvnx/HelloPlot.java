/*
 * adb uninstall vvnx.helloplot
 * adb install out/target/product/generic_arm64/system/app/HelloPlot/HelloPlot.apk
 * 
 * 
 * 
 */

package vvnx.helloplot;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.*;

import java.text.SimpleDateFormat;
import android.util.Log;




public class HelloPlot extends Activity {
	
	private static final String TAG = "HelloPlot";

    private XYPlot plot;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.plot);
  
        //La data en List<Integer>
        //soit deux List       
        //List<Integer> xVals = Arrays.asList(new Integer[]{1601280124, 1601280224, 1601280524, 1601280624});
        //List<Integer> yVals = Arrays.asList(new Integer[]{5, 2, 10, 5});
        //soit une list interleaved
        List<Integer> myVals = Arrays.asList(new Integer[]{1601280124, 2, 1601280224, 3, 1601280524, 4, 1601280624, 5});
            
        
        //Transformation de la data en XYseries https://github.com/halfhp/androidplot/blob/master/docs/xyplot.md#simplexyseries
        //XYSeries my_series = new SimpleXYSeries(yVals, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "my series"); // (Y_VALS_ONLY means use the element index as the x value)
		XYSeries my_series = new SimpleXYSeries(myVals, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, "my series"); //XY_VALS_INTERLEAVED: interleaved list of x/y value pairs (x[0] = 1, y[0] = 4, x[1] = 2, y[1] = 8, ...):
		//XYSeries my_series = new SimpleXYSeries(xVals, yVals, "my series");     
          
        
        //setDomainStep -> Le nombre de ticks en abcisse (en jargon AndroidPlot: le nombre de lines en domain)  
        //https://github.com/halfhp/androidplot/blob/master/docs/xyplot.md#domain--range-lines  
        //plot.setDomainStep(StepMode.INCREMENT_BY_VAL, 2); et non pas INCREMENT_BY_VALUE comme écrit dans la doc
        plot.setDomainStep(StepMode.SUBDIVIDE, 4); //the graph is subdivided into the specified number of sections.
                

        //formatters configuration en xml (androidplot/demoapp/src/main/res/xml/)
        
        //LineAndPointFormatter series1Format = new LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels); //line_point_formatter_with_labels.xml: celui du hello world, une ligne qui relie les points
        LineAndPointFormatter series1Format = new LineAndPointFormatter(this, R.xml.point_formatter); //scatterPlot (que les points)

        // add a new series' to the xyplot:
        plot.addSeries(my_series, series1Format);
       
        
        //https://github.com/halfhp/androidplot/blob/master/docs/xyplot.md#linelabelrenderer
        //https://github.com/halfhp/androidplot/blob/master/demoapp/src/main/java/com/androidplot/demos/FXPlotExampleActivity.java
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.BOTTOM, new MyLineLabelRenderer());
  
    }
    
        class MyLineLabelRenderer extends XYGraphWidget.LineLabelRenderer {
        @Override
        protected void drawLabel(Canvas canvas, String text, Paint paint, float x, float y, boolean isOrigin) {
               // Log.d(TAG, "On passe dans drawLabel avec text = " + text);
				
				//Transformation de la valeur dans String text (epoch -> date formatée)
                //pour une valeur 1601280124 text arrive sous la forme: 1601280124,0. Impossible d'avoir un long directement. Il faut "." et pas "," et
                //il faut passer par parseDouble puis caster en long
                long epoch = (long)Double.parseDouble(text.replaceAll(",",".")); 
				Date date = new Date( epoch * 1000);
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");                
                text = sdf.format(date);                

                super.drawLabel(canvas, text, paint, x, y , isOrigin);
        }
    }
}
