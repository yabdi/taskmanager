package com.nslearning.android.taskmanager;

import java.io.IOException;
import java.util.List;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.nslearning.android.taskmanager.views.AddressOverlay;

public class AddLocationMapActivity extends MapActivity {

	public static final String ADDRESS_RESULT = "address";
	private EditText addressText;
	private Button mapLocationButton;
	private Button useLocationButton;
	private MapView mapView;
	private Address address;

	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.add_location);
		setUpViews();
	}

	private void setUpViews() {
		addressText = (EditText)findViewById(R.id.task_address);
		mapLocationButton=(Button)findViewById(R.id.map_location_button);
		useLocationButton=(Button)findViewById(R.id.use_this_location_button);
		mapView = (MapView)findViewById(R.id.map);
		useLocationButton.setEnabled(false);
		mapView.setBuiltInZoomControls(true);
		
		mapLocationButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mapCurrentAddress();
				
			}
		});
		useLocationButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(null != address){
					Intent intent = new Intent();
					intent.putExtra(ADDRESS_RESULT, address);
					setResult(RESULT_OK, intent);
				}
				finish();
				
			}
		});
	}

	protected void mapCurrentAddress() {
		String addressString = 	addressText.getText().toString();
		Geocoder g = new Geocoder(this);
		List<Address> addresses;
		try {
			addresses = g.getFromLocationName(addressString, 1);
			if(null != addresses && addresses.size() > 0){
				address = addresses.get(0);
				List<Overlay> mapOverlays = mapView.getOverlays();
				AddressOverlay addressOverlay = new AddressOverlay(address);
				mapOverlays.add(addressOverlay);
				mapView.invalidate();
				final MapController mapController = mapView.getController();
				mapController.animateTo(addressOverlay.getGeopoint(), new Runnable() {
					public void run() {
						mapController.setZoom(12);
					}
				});
				useLocationButton.setEnabled(true);
			} else {
				//tell the user no results for that address
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	protected boolean isLocationDisplayed() {
		return false;
	}
}
