package com.example.arnaud.englishproject;




import java.util.ArrayList;
import com.google.android.gms.maps.model.LatLng;

public class Location {
    private String name;
    private LatLng gps;
    private int zoom = -1;
    private ArrayList<MarkerInstance> markers = new ArrayList<MarkerInstance>();


    public Location(){
        super();
    }


    public void addMarkers(MarkerInstance markerInstance) {
        this.getMarkers().add(markerInstance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getGps() {
        return gps;
    }

    public void setGps(LatLng gps) {
        this.gps = gps;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public ArrayList<MarkerInstance> getMarkers() {
        return markers;
    }

    public void setMarkers(ArrayList<MarkerInstance> markers) {
        this.markers = markers;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", gps=" + gps +
                ", zoom=" + zoom +
                ", markers=" + markers +
                '}';
    }
}
