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

        // create a couple arrays of y-values to plot:
        //final Number[] domainLabels = {10, 20, 30, 40};
        
        plot.setDomainStep(StepMode.INCREMENT_BY_VAL, 2);
        
        //Format de la déclaration des data series du helloPlot Quickstart, sous forme d'arrays, qui ensuite sont transformées en listes dans new SimpleXYSeries()
        //Number[] series1Numbers = {1, 4, 2, 8, 4, 16, 8, 32, 16, 64};
        
        //Déclarations sous forme de List<Integer> que je peux renvoyer depuis une méthode dans ma classe BaseDeDonnees
        //List<Integer> series1Numbers_as_list = Arrays.asList(new Integer[]{1, 4, 2, 8, 4, 16, 8, 32, 16, 64});
        //List<Integer> series2Numbers_as_list = Arrays.asList(new Integer[]{5, 2, 10, 5, 20, 10, 40, 20, 70, 40});
 

        // turn the above arrays into XYSeries':
        
        /*XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");*/
        //Je passe directement les series en List<Integer>
        //XYSeries series1 = new SimpleXYSeries(series1Numbers_as_list, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");
        //XYSeries series2 = new SimpleXYSeries(series2Numbers_as_list, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");         
                
        
        
        //Passer deux arrays: un pour les X un pour les Y
        List<Integer> xVals = Arrays.asList(new Integer[]{1, 2, 7, 8});
        List<Integer> yVals = Arrays.asList(new Integer[]{5, 2, 10, 5});
            
        
        //XYSeries my_series = new SimpleXYSeries(yVals, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "my series1"); // (Y_VALS_ONLY means use the element index as the x value)
        XYSeries my_series = new SimpleXYSeries(xVals, yVals, "my series");     
             
                
                

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels);

        /*LineAndPointFormatter series2Format =
                new LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels_2);*/

        // add an "dash" effect to the series2 line:
        /*series2Format.getLinePaint().setPathEffect(new DashPathEffect(new float[] {

                // always use DP when specifying pixel sizes, to keep things consistent across devices:
                PixelUtils.dpToPix(20),
                PixelUtils.dpToPix(15)}, 0));*/

        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        //series1Format.setInterpolationParams(
        //        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        /*series2Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));*/

        // add a new series' to the xyplot:
        plot.addSeries(my_series, series1Format);
        //plot.addSeries(series2, series2Format);
        
        
        //https://github.com/halfhp/androidplot/blob/master/demoapp/src/main/java/com/androidplot/demos/FXPlotExampleActivity.java
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.BOTTOM, new MyLineLabelRenderer());
        
        
        
		//Ce système de modification des labels ne marche que quand je déclare new SimpleXYSeries(yVals, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY... ce qui ne 
		//	me donne pas de "linéarité" dans les valeurs d'abcisse (chaque valeur de x n'est pas comptabilisée sur sa valeur mais juste sur son existence (un item, pas un temps par exemple)
        /*plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            // obj contains the raw Number value representing the position of the label being drawn. customize the labeling however you want here:
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                Log.d(TAG, "getLineLabelStyle override de format(), Number=" + ((Number) obj).floatValue());
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(xVals.toArray()[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });*/
    }
    
        class MyLineLabelRenderer extends XYGraphWidget.LineLabelRenderer {
        @Override
        protected void drawLabel(Canvas canvas, String text, Paint paint,
                float x, float y, boolean isOrigin) {
                Log.d(TAG, "On passe dans drawLabel avec text = " + text);
                //Exemple AndroidPlot: on colore en rouge
                final Paint newPaint = new Paint(paint);
                newPaint.setColor(Color.RED);
                super.drawLabel(canvas, text, newPaint, x, y , isOrigin);
        }
    }
}
