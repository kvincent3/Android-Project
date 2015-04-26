package com.example.arnaud.englishproject;




import android.app.Activity;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.text.DecimalFormat;
import static java.lang.StrictMath.abs;


public class ViewMap
{
    private GoogleMap map;

    private MarkerInstance spot = new MarkerInstance("touched");
    private Boolean toTouch = false;
    private LatLng touched = new LatLng(0,0);

    //instanciate a specific view on a specific map
    public ViewMap(Activity a, Boolean toTouch)
    {

        this.map = ((MapFragment) a.getFragmentManager().findFragmentById(R.id.map) ).getMap();


        this.toTouch = toTouch;

        this.spot.setTitre("touched");

        if (this.toTouch) {
            this.map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng arg0) {
                    // TODO Auto-generated method stub
                    Log.d("Touched!", arg0.latitude + "-" + arg0.longitude);
                    touched = new LatLng(formatConversion(arg0.latitude), formatConversion(arg0.longitude));

                    spot.setCoor(arg0);
                    map.clear();
                    map.addMarker(new MarkerOptions().position(spot.getCoor()));

                }
            });

        }
    }


    public void InitialiseMap(Location l)
    {

        //modiofication de getLocationFromPlace, de manière a retourner null dans les cas embarassants
        if (map!=null && l!=null){
            //Ajout de gardes à faire sur l.getGPS et l.getZoom
            //Ajout d'une valeur par défaut du zoom (vue de l'angleterre ?)
            CameraUpdate center = CameraUpdateFactory.newLatLng(l.getGps());
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(l.getZoom());

            if(l.getZoom() < 10){
                this.map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }else{
                this.map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }

            map.moveCamera(center);
            map.animateCamera(zoom);

            for (int j=0 ; j < l.getMarkers().size(); j++)
            {
                map.addMarker(new MarkerOptions().position(l.getMarkers().get(j).getCoor())
                        .title(l.getMarkers().get(j).getTitre())
                        .snippet(l.getMarkers().get(j).getTexte())
                        .icon(BitmapDescriptorFactory.fromAsset(l.getMarkers().get(j).getImage())));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        touched = marker.getPosition();
                        Log.d("MARKER", "internal variable has been intialized");
                        return true;
                    }
                });
            }
        }

    }

    /*
    Amelioration pour rafraichir au cas par cas, que si la location existe reellement dans notre
    fichier mapData.txt
     */
    public void refreshMap(Location l)
    {
        if (map!=null)
        {
            CameraUpdate center;
            CameraUpdate zoom;
            if (l != null)
            {
                map.clear();
                center = CameraUpdateFactory.newLatLng(l.getGps());

                if(l.getZoom() != -1){
                    zoom = CameraUpdateFactory.zoomTo(l.getZoom());

                    if(l.getZoom() < 10){
                        this.map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    }else{
                        this.map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    }

                    map.moveCamera(center);
                    map.animateCamera(zoom);

                    for (int j=0 ; j < l.getMarkers().size(); j++)
                    {
                        map.addMarker(new MarkerOptions().position(l.getMarkers().get(j).getCoor())
                                .title(l.getMarkers().get(j).getTitre())
                                .snippet(l.getMarkers().get(j).getTexte())
                                .icon(BitmapDescriptorFactory.fromAsset(l.getMarkers().get(j).getImage())));

                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                touched = marker.getPosition();
                                Log.d("MARKER", "internal variable has been intialized");
                                return true;
                            }
                        });
                    }



                }
            }
        }
    }

    public double formatConversion(double d){
        DecimalFormat dFormat = new DecimalFormat("#.######");
        return Double.valueOf(dFormat.format(d));
    }

    public boolean checkAccurancy(String answer) {


        boolean found = false;

        /*
        Convert the given String to a new LAtLng variable

         */
        String[] toConvert = answer.split(", ");
        if(toConvert.length != 2){
            toConvert = answer.split(",");
            if(toConvert.length != 2){
                Log.d("convert", "cannot split into 2 pieces the given answer");
                return found;
            }
            Log.d("convert", "avoid a segfault on the split of the given answer");

        }

        LatLng l = new LatLng( formatConversion(Double.parseDouble(toConvert[0])),
                formatConversion(Double.parseDouble(toConvert[1]))
        );

        //Compare the two locations, and identify if they are close enough
        /* Conversion explanation
        http://www.sunearthtools.com/dp/tools/conversion.php?lang=fr
        50m <=> 0.0001*5 --> too restrictive
        0.01 <=> 1,11 km
         */
        /*
        Fuck la theorie, ça marche que trop moyennement
        trafalgar : 51.507861, -0.128166
        ltg : marge de 0.0017
        sud :		51.506886, -0.128209
        nord :		51.508676, -0.128316

        lng : marge de 0.00350
        est : 		51.507714, -0.126342
        ouest :		51.507581, -0.129389
        ==> Hop la, on fait les bourrins
         */
        Log.d("input : ", "" + touched.latitude + "-" + touched.longitude);
        Log.d("expect: ", "" + l.latitude + "-" + l.longitude);


        double ltg = formatConversion(abs(touched.latitude - l.latitude));
        double lng = formatConversion(abs(touched.longitude - l.longitude));
        if (ltg < 0.0017000 && lng < 0.003000){

            Log.d("Accurancy", "touched match !!!");
            found = true;
        } else {
            Log.d("Accurancy", "touched FAILED !!!");

        }
        Log.d("ACC", "" + ltg + " - " + lng );
        this.touched = new LatLng(0,0);

        return found;
    }

    public Boolean getToTouch() {
        return this.toTouch;
    }

}
