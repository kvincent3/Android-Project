package com.example.arnaud.englishproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.StrictMath.abs;

public class ViewMap {
	private GoogleMap map;
	private MarkerInstance spot = new MarkerInstance("touched");
	private Boolean toTouch = false;
	private LatLng touched = new LatLng(0, 0);
	private Activity activity;
    private Boolean markerRefreshed = false;

	// instanciate a specific view on a specific map
	public ViewMap(Activity a, Boolean toTouch) {
		this.activity = a;
		this.map = ((MapFragment) a.getFragmentManager().findFragmentById(
				R.id.map)).getMap();

		this.toTouch = toTouch;

		this.spot.setTitre("touched");

		if (this.toTouch) {
			this.map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
				@Override
				public void onMapClick(LatLng arg0) {
					// TODO Auto-generated method stub
					Log.d("Touched!", arg0.latitude + "-" + arg0.longitude);
					touched = new LatLng(formatConversion(arg0.latitude),
							formatConversion(arg0.longitude));

					spot.setCoor(arg0);
					//map.clear();
					//map.addMarker(new MarkerOptions().position(spot.getCoor()));

				}
			});

		}
	}
	
	public ViewMap(Activity a)
	{
		this.activity = a;
		this.map = ((MapFragment) a.getFragmentManager().findFragmentById(
				R.id.map2)).getMap();
		CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(51.5085300,-0.1257400));
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
		this.map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.moveCamera(center);
		map.animateCamera(zoom);
	}
	
	public void LoadHistoryMap(final ArrayList<DataHistory> datahistory)
	{
		Log.d("test","c'est ok");
		this.map = ((MapFragment) this.activity.getFragmentManager().findFragmentById(R.id.map2)).getMap();

		if (this.map !=null && datahistory!=null)
		{
			Log.d("DataHist","Loading map");
			for (int j=0; j < datahistory.size();j++) {


                Log.d("dataHistory", "init of markers");
				final String texte = datahistory.get(j).getStory();
                MarkerOptions tmp = new MarkerOptions()
                        .position(datahistory.get(j).getMarker().getCoor())
                        .title(datahistory.get(j).getMarker().getTitre())
                        .snippet(datahistory.get(j).getMarker().getTexte())
                        .icon(BitmapDescriptorFactory.fromAsset(datahistory.get(j).getMarker().getImage()));

                map.addMarker(tmp);
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                    @SuppressLint("NewApi")
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        TextView history = (TextView) activity
                                .findViewById(R.id.textView1);

                        String story = getStoryFromMarker(datahistory, marker.getTitle());
                        Log.d("marker", "history is : "+story+" from " + marker.getTitle());

                        if (story!=null) {
                            history.setText(story);
                        }
                        Log.d("dataHist", "history loaded");
                        return false; // Conserver le comportement normal
                    }


                });


            }
		}
	
	}

	public void InitialiseMap(Location l) {

		// modiofication de getLocationFromPlace, de manière a retourner null
		// dans les cas embarassants
		if (map != null && l != null) {
			// Ajout de gardes à faire sur l.getGPS et l.getZoom
			// Ajout d'une valeur par défaut du zoom (vue de l'angleterre ?)
			CameraUpdate center = CameraUpdateFactory.newLatLng(l.getGps());
			CameraUpdate zoom = CameraUpdateFactory.zoomTo(l.getZoom());

			if (l.getZoom() < 10) {
				this.map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			} else {
				this.map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			}

			map.moveCamera(center);
			map.animateCamera(zoom);

            this.initMarkerWithImage(l);

		}

	}

	/*
	 * Amelioration pour rafraichir au cas par cas, que si la location existe
	 * reellement dans notre fichier mapData.txt
	 */
	public void refreshMap(Location l) {
		if (map != null) {
			CameraUpdate center;
			CameraUpdate zoom;
			if (l != null) {
				map.clear();
				center = CameraUpdateFactory.newLatLng(l.getGps());

				if (l.getZoom() != -1) {
					zoom = CameraUpdateFactory.zoomTo(l.getZoom());

					if (l.getZoom() < 10) {
						this.map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
					} else {
						this.map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
					}

					map.moveCamera(center);
					map.animateCamera(zoom);

					this.initMarkerWithImage(l);

				}
			}
		}
	}

	public double formatConversion(double d) {
		DecimalFormat dFormat = new DecimalFormat("#.######");
		return Double.valueOf(dFormat.format(d));
	}

	public boolean checkAccurancy(String answer) {

		boolean found = false;

		/*
		 * Convert the given String to a new LAtLng variable
		 */
		String[] toConvert = answer.split(", ");
		if (toConvert.length != 2) {
			toConvert = answer.split(",");
			if (toConvert.length != 2) {
				Log.d("convert", "cannot split into 2 pieces the given answer");
				return found;
			}
			Log.d("convert",
					"avoid a segfault on the split of the given answer");

		}

		LatLng l = new LatLng(
				formatConversion(Double.parseDouble(toConvert[0])),
				formatConversion(Double.parseDouble(toConvert[1])));

		// Compare the two locations, and identify if they are close enough
		/*
		 * Conversion explanation
		 * http://www.sunearthtools.com/dp/tools/conversion.php?lang=fr 50m <=>
		 * 0.0001*5 --> too restrictive 0.01 <=> 1,11 km
		 */
		/*
		 * Fuck la theorie, ça marche que trop moyennement trafalgar :
		 * 51.507861, -0.128166 ltg : marge de 0.0017 sud : 51.506886, -0.128209
		 * nord : 51.508676, -0.128316
		 * 
		 * lng : marge de 0.00350 est : 51.507714, -0.126342 ouest : 51.507581,
		 * -0.129389 ==> Hop la, on fait les bourrins
		 */
		Log.d("input : ", "" + touched.latitude + "-" + touched.longitude);
		Log.d("expect: ", "" + l.latitude + "-" + l.longitude);

		double ltg = formatConversion(abs(touched.latitude - l.latitude));
		double lng = formatConversion(abs(touched.longitude - l.longitude));
		if (ltg < 0.0017000 && lng < 0.003000) {

			Log.d("Accurancy", "touched match !!!");
			found = true;
		} else {
			Log.d("Accurancy", "touched FAILED !!!");

		}
		Log.d("ACC", "" + ltg + " - " + lng);
		this.touched = new LatLng(0, 0);

		return found;
	}

	public Boolean getToTouch() {
		return this.toTouch;
	}

    public void initMarkerWithImage(final Location l){
        for (int j = 0; j < l.getMarkers().size(); j++) {
            final String image = l.getMarkers().get(j).getImage();

            MarkerOptions tmp = new MarkerOptions()
                    .position(l.getMarkers().get(j).getCoor())
                    .title(l.getMarkers().get(j).getTitre())
                    .snippet(l.getMarkers().get(j).getTexte())
                    .icon(BitmapDescriptorFactory.fromAsset(image));
            map.addMarker(tmp);
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                ImageView imageview1;

                @SuppressLint("NewApi")
                @Override
                public boolean onMarkerClick(Marker marker) {
                    touched = marker.getPosition();

                    Log.d("marker", "touched is : "+marker.getTitle());
                    imageview1 = (ImageView) activity
                            .findViewById(R.id.imageView1);

                    try
                    {
                        String image = getImageFromMarker(l, marker.getTitle());
                        if (l!=null) {
                            InputStream bitmap = activity.getAssets().open(image);
                            Bitmap bit = getResizedBitmap(BitmapFactory.decodeStream(bitmap), 200, 200);
                            imageview1.setImageBitmap(bit);
                            Log.d("MArker", "refresh marker on click");

                        }
                    }
                    catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    Log.d("MARKER", "internal variable has been intialized");
                    return false; // Conserver le comportement normal
                }

                public Bitmap getResizedBitmap(Bitmap bm, int newHeight,int newWidth)
                {
                    int width = bm.getWidth();
                    int height = bm.getHeight();
                    float scaleWidth = ((float) newWidth) / width;
                    float scaleHeight = ((float) newHeight) / height;
                    // create a matrix for the manipulation
                    Matrix matrix = new Matrix();
                    // resize the bit map
                    matrix.postScale(scaleWidth, scaleHeight);
                    // recreate the new Bitmap
                    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0,
                            width, height, matrix, false);
                    return resizedBitmap;

                }

            });

        }


    }

    public String getImageFromMarker(Location l, String markerTitle){
        ArrayList<MarkerInstance> markers = l.getMarkers();
        for (int i=0; i < markers.size(); i++){
            if(markers.get(i).getTitre().equals(markerTitle)){
                return markers.get(i).getImage();
            }
        }
        return null;
    }

    public String getStoryFromMarker(ArrayList<DataHistory> d, String markerTitle){
        for (int i=0; i < d.size(); i++){
            if(d.get(i).getMarker().getTitre().equals(markerTitle)){
                return d.get(i).getStory();
            }
        }
        return null;
    }


}
