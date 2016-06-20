package com.rayashari.worldheritage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/**
 * Created by rayasha on 6/19/2016.
 */
public class MemberDetail extends Activity implements GoogleMap.OnMapLongClickListener {
    StreetViewPanoramaView mStreetViewPanoramaView;
    StreetViewPanorama mPanorama;
    TextView description;
    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_PICTURE = "EXTRA_PICTURE";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_LOCATION = "EXTRA_LOCATION";
    public static final String EXTRA_DESC = "EXTRA_DESC";
    public static final String EXTRA_LAT = "EXTRA_LAT";
    public static final String EXTRA_LNG = "EXTRA_LNG";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_member);

        mStreetViewPanoramaView = (StreetViewPanoramaView) findViewById(R.id.steet_view_panorama);
        mStreetViewPanoramaView.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String place = intent.getStringExtra(EXTRA_NAME);
        String title1 = intent.getStringExtra(EXTRA_TITLE);
        int thumb = intent.getIntExtra(EXTRA_PICTURE, 0);
        final double lat1 = intent.getDoubleExtra(EXTRA_LAT,0);
        final double lng1 = intent.getDoubleExtra(EXTRA_LNG,0);
        String desc = intent.getStringExtra(EXTRA_DESC);
        ImageView thumbnail = (ImageView) findViewById(R.id.bahan);
        if(thumb != 0) {
            thumbnail.setImageResource(thumb);
        }

        Toast.makeText(MemberDetail.this, "Exploring " + place, Toast.LENGTH_SHORT).show();

        description = (TextView)findViewById(R.id.info_description);
        description.setText(desc);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title1);
        mStreetViewPanoramaView.getStreetViewPanoramaAsync(new OnStreetViewPanoramaReadyCallback() {
            @Override
            public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                mPanorama = panorama;
                double lat = lat1;
                double lng = lng1;
//                LatLng position = new LatLng(lat1, lng1);
                showStreetView( new LatLng(  lat, lng) );

            }
        });
    }

//    private void initStreetView() {
//        mStreetViewPanoramaView.getStreetViewPanoramaAsync(new OnStreetViewPanoramaReadyCallback() {
//            @Override
//            public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
//                mPanorama = panorama;
//                double lat = getIntent().getDoubleExtra("lat", 37.6329946);
//                double lng = getIntent().getDoubleExtra("lng", -122.4938344);
//                LatLng position = new LatLng(lat, lng);
//                showStreetView( new LatLng(  lat, lng) );
//
//            }
//        });
//    }

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


    public void showInformation(View view) {
        toggleInformationView(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void toggleInformationView(View view) {
        final View infoContainer = findViewById(R.id.information_container);

        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        float radius = Math.max(infoContainer.getWidth(), infoContainer.getHeight()) * 2.0f;

        Animator reveal;
        if (infoContainer.getVisibility() == View.INVISIBLE) {
            infoContainer.setVisibility(View.VISIBLE);
            reveal = ViewAnimationUtils.createCircularReveal(
                    infoContainer, cx, cy, 0, radius);
            reveal.setInterpolator(new AccelerateInterpolator(2.0f));
        } else {
            reveal = ViewAnimationUtils.createCircularReveal(
                    infoContainer, cx, cy, radius, 0);
            reveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    infoContainer.setVisibility(View.INVISIBLE);
                }
            });
            reveal.setInterpolator(new DecelerateInterpolator(2.0f));
        }
        reveal.setDuration(600);
        reveal.start();
    }
    @Override
    public void onMapLongClick(LatLng latLng) {

    }
}
