### AndroidPlot dans l'aosp

based on the wonderful work (thanks +++) of:
https://github.com/halfhp
https://github.com/halfhp/androidplot.git
https://github.com/halfhp/fig.git

### Importation du projet dans un Hello World des development/samples de l'AOSP
cd root; 
git clone https://github.com/halfhp/androidplot.git
git clone https://github.com/halfhp/fig.git #les fichiers d'androidplot l'import

cp ( ou symlink) /root/androidplot/androidplot-core/src/main/java/com/androidplot dans src/com/
cp ( ou symlink) /root/fig/figlib/src/main/java/com dans src/com/

même sans rien ajouter dans l'activité de départ, le simple symlink dans src/ cause des erreurs au make

cp des res/values/{attrs; color; style}.xml d'androidplot dans res/

#erreurs du type "package R does not exist":
src/com/androidplot/xy/XYGraphWidget.java:435: error: package R does not exist
                R.styleable.xy_XYPlot_gridBackgroundColor
                
le faire .java par .java: soit il y a un import com.androidplot.R; qui doit être remplacé par import vvnx.helloplot.R; 
	soit il n'y en a pas, tu l'ajoutes
je crois qu'il n'y a que 4 ou 5 fichiers à modifier

#erreurs du type "cannot find symbol" @NonNull ou @Nullable
	Android.mk (juste après LOCAL_SRC_FILES marche) --> LOCAL_STATIC_ANDROID_LIBRARIES := androidx.appcompat_appcompat
	et dans les .java (une bonne vingtaine...): import android.support.annotation.NonNull||Nullable; --> androidx.annotation.NonNull||Nullable;
	helpful one liner: find . -type f -iname '*.java' -exec sed -i 's/android.support/androidx/' "{}" +;
	
#erreurs du type "cannot find symbol" Fig.configure....
	git clone https://github.com/halfhp/fig.git + symlink halfhp dans src/com/ --> import com.halfhp.fig.Fig; doit marcher
