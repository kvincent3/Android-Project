package com.example.englishproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.content.Context;


import com.google.android.gms.maps.model.LatLng;

public class ModelMap {
	String ville;
	LatLng coorVille;
	int zoom;
	ArrayList<markerInstance> markers;

	public ModelMap(Context c, String relativeMapFile) {
		this.markers = new ArrayList<markerInstance>();
		extractData(c, relativeMapFile);
	}

	public void extractData(Context c, String relativeMapFile) {
		InputStream in;
		BufferedReader br = null;
		try {
			in = c.getAssets().open(relativeMapFile);
			br = new BufferedReader(new InputStreamReader(in));

			// Read the file content and fill the map data
			String line;
			while ((line = br.readLine()) != null) {

				String[] parts = line.split(":");

				if (parts[0].equals("p")) {
					this.setVille(parts[1]);
				} else if (parts[0].equals("m")) {
					this.markers.add(new markerInstance(parts[1]));
				} else if (parts[0].equals("z")) {
					this.zoom = Integer.parseInt(parts[1]);
				} else if (parts[0].equals("c")) {
					String[] info = parts[1].split(",");
					this.coorVille = new LatLng(Double.parseDouble(info[0]),
							Double.parseDouble(info[1]));
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public LatLng getCoorVille() {
		return coorVille;
	}

	public void setCoorVille(LatLng coorVille) {
		this.coorVille = coorVille;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public ArrayList<markerInstance> getMarkers() {
		return markers;
	}

	public void setMarkers(ArrayList<markerInstance> markers) {
		this.markers = markers;
	}

}
