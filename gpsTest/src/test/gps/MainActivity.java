package test.gps;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;


public class MainActivity extends Activity implements LocationListener,OnClickListener{
	private LocationManager mLocationManager;
	private Button button1;
	private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //LocationManagerの取得
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        
		//main.xmlと変数のひも付け
		button1 = (Button)findViewById(R.id.button1);
		
		button1.setOnClickListener(this);
		text1 =(TextView)findViewById(R.id.textView1);
    }
    
    @Override
    protected void onResume() {
    	//LocationManagerにリスナーを登録
        if (mLocationManager != null) {
            mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
//                LocationManager.NETWORK_PROVIDER,
                0,
                0,
                this);
        }   
        super.onResume();
    }
    	
    @Override
    protected void onPause() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }
        super.onPause();
    }

    //位置情報の変化の時呼び出される	
    @Override
    public void onLocationChanged(Location location) {
        Log.v("----------", "----------");
        //緯度
        Log.v("Latitude", String.valueOf(location.getLatitude()));
        //軽度
        Log.v("Longitude", String.valueOf(location.getLongitude()));
        //精度
        Log.v("Accuracy", String.valueOf(location.getAccuracy()));
        //標高
        Log.v("Altitude", String.valueOf(location.getAltitude()));
        Log.v("Time", String.valueOf(location.getTime()));
        Log.v("Speed", String.valueOf(location.getSpeed()));
        Log.v("Bearing", String.valueOf(location.getBearing()));
        text1.setText("La:"+Double.toString(location.getLatitude())+" Lo:"+Double.toString(location.getLongitude()));	
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    //位置情報取得の状態変化
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
        case LocationProvider.AVAILABLE:
            Log.v("Status", "AVAILABLE");
            break;
        case LocationProvider.OUT_OF_SERVICE:
            Log.v("Status", "OUT_OF_SERVICE");
            break;
        case LocationProvider.TEMPORARILY_UNAVAILABLE:
            Log.v("Status", "TEMPORARILY_UNAVAILABLE");
            break;
        }
    }

	//クリック時処理
	@Override
	public void onClick(View view) {
		//onLocationChanged(this);	
		text1.setText("HelloWorld!");
	}

    
    
}
