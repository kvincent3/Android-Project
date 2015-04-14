package com.example.arnaud.englishproject;




import android.app.Activity;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class ViewMap
{
    private Middleman middleman;
    private GoogleMap map;
    private Boolean toTouch = false;
    private LatLng touched = new LatLng(0, 0);


    //instanciate a specific view on a specific map
    public ViewMap(Activity a, Middleman m)
    {
        this.middleman = m;
        this.map = ((MapFragment) a.getFragmentManager().findFragmentById(R.id.map) ).getMap();

        this.map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub
                Log.d("Touched!", arg0.latitude + "-" + arg0.longitude);

                touched = arg0;
            }
        });

        InitialiseMap();
    }


    public void InitialiseMap()
    {
        Location l = this.middleman.giveMeMap( this.middleman.giveMeQuestion(0).getPlace() );

        if (map!=null){
            CameraUpdate center = CameraUpdateFactory.newLatLng(l.getGps());
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(l.getZoom());
            map.moveCamera(center);
            map.animateCamera(zoom);

            for (int j=0 ; j < l.getMarkers().size(); j++)
            {
                map.addMarker(new MarkerOptions().position(l.getMarkers().get(j).getCoor())
                        .title(l.getMarkers().get(j).getTitre())
                        .snippet(l.getMarkers().get(j).getTexte())
                        .icon(BitmapDescriptorFactory.fromAsset(l.getMarkers().get(j).getImage())));
            }
        }

    }

    /*
    Amelioration pour rafraichir au cas par cas, que si la location existe reellement dans notre
    fichier mapData.txt
     */
    public void refreshMap()
    {
        if (map!=null && this.middleman.comparePlaces())
        {
            Location l = this.middleman.getAppropriateLocation();

            CameraUpdate center;
            CameraUpdate zoom;
            if (l.getGps() != null)
            {
                map.clear();
                center = CameraUpdateFactory.newLatLng(l.getGps());

                if(l.getZoom() != -1){
                    zoom = CameraUpdateFactory.zoomTo(l.getZoom());
                    map.moveCamera(center);
                    map.animateCamera(zoom);

                    for (int j=0 ; j < l.getMarkers().size(); j++)
                    {
                        map.addMarker(new MarkerOptions().position(l.getMarkers().get(j).getCoor())
                                .title(l.getMarkers().get(j).getTitre())
                                .snippet(l.getMarkers().get(j).getTexte())
                                .icon(BitmapDescriptorFactory.fromAsset(l.getMarkers().get(j).getImage())));
                    }
                    synchronized (this)
                    {
                        Log.d("notifie","on notfie");
                        this.notify();
                    }
                }
            }
        }
    }

    public void checkAccurancy(){
        String place = this.middleman.giveMeQuestion(0).getPlace();
        Log.d("ACC", touched.latitude + "-" + touched.longitude+" VS "+ this.middleman.getLocationFromPlace(place).getGps());
    }


    public Boolean getToTouch() {
        return toTouch;
    }

    public void setToTouch(Boolean toTouch) {
        this.toTouch = toTouch;
    }
}
