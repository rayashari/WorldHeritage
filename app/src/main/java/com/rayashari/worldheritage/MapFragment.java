package com.rayashari.worldheritage;

/**
 * Created by rayasha on 6/19/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/**
 * Created by rayasha on 6/19/2016.
 */
public class MapFragment extends SupportMapFragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnIndoorStateChangeListener,SeekBar.OnSeekBarChangeListener{
    StreetViewPanoramaView mStreetViewPanoramaView;
    StreetViewPanorama mPanorama;
    SeekBar mIndoorSelector;
    TextView mIndoorMinLevel, mIndoorMaxLevel;

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        showStreetView( latLng );
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCamera();
        initStreetView();
    }

    private void initCamera() {
        CameraPosition position = CameraPosition.builder()
                .target( new LatLng( -7.3595415, 108.5374913 ) )
                .zoom( 18f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();

        getMap().animateCamera(
                CameraUpdateFactory.newCameraPosition( position ), null );
        getMap().setMapType( GoogleMap.MAP_TYPE_HYBRID );
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup parent = (ViewGroup) super.onCreateView( inflater, container, savedInstanceState );
        View overlay = inflater.inflate( R.layout.view_map_overlay, parent, false );

        mStreetViewPanoramaView = (StreetViewPanoramaView) overlay.findViewById(R.id.steet_view_panorama);
        mStreetViewPanoramaView.onCreate(savedInstanceState);

        parent.addView( overlay );

        return parent;
    }

    private void initStreetView() {
        getMap().setOnMapLongClickListener( this );

        mStreetViewPanoramaView.getStreetViewPanoramaAsync(new OnStreetViewPanoramaReadyCallback() {
            @Override
            public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                mPanorama = panorama;
                showStreetView( new LatLng(  -7.3595415, 108.5374913) );
            }
        });
    }

    private void showStreetView( LatLng latLng ) {
        if( mPanorama == null )
            return;

        StreetViewPanoramaCamera.Builder builder = new StreetViewPanoramaCamera.Builder( mPanorama.getPanoramaCamera() );
        builder.tilt( 0.0f );
        builder.zoom( 0.0f );
        builder.bearing( 0.0f );
        mPanorama.animateTo( builder.build(), 0 );

        mPanorama.setPosition( latLng, 300 );
        mPanorama.setStreetNamesEnabled( true );
    }

    @Override
    public void onIndoorBuildingFocused() {

    }

    @Override
    public void onIndoorLevelActivated(IndoorBuilding indoorBuilding) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

