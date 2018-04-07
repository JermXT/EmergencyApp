import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * Created by Jeremy on 4/7/18.
 */

public class PinGroup {
    private ArrayList<Marker> markers = new ArrayList();
    private double[] center = new double[2];
    private double radius;

    public PinGroup(Marker m) {
        markers.add(m);

    }

    public void addMarker(Marker m){
        markers.add(m);
        resetCenter();
    }
    private void resetCenter(){
        int marks = 0;
        double lg = 0.0;
        double la = 0.0;

        for(Marker m : markers){
            lg += m.getPosition().longitude;
            la += m.getPosition().latitude;
            marks++;
        }
        center[0] = la/marks;
        center[1] = lg/marks;
        double maxdist = 0.0;
        for(Marker m :markers){
            double dist = Math.sqrt(Math.pow(m.getPosition().longitude,2)+Math.pow(m.getPosition().latitude,2));
            if(dist > maxdist){
                maxdist = dist;
            }
        }
        radius = maxdist;
    }
}
