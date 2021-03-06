package com.mapzen.open.fragment;

import com.mapzen.open.MapzenApplication;
import com.mapzen.open.R;
import com.mapzen.open.activity.BaseActivity;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import retrofit.RetrofitError;

public abstract class BaseFragment extends Fragment {
    protected BaseActivity act;
    protected MapFragment mapFragment;
    protected MapzenApplication app;

    public void setAct(BaseActivity act) {
        this.act = act;
        this.app = (MapzenApplication) act.getApplication();
    }

    public void inject() {
        app.inject(this);
    }

    public void setMapFragment(MapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }

    public MapFragment getMapFragment() {
        return mapFragment;
    }

    protected void onServerError(int status) {
        if (status == 207) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notifyError(R.string.no_route_found);
                }

            });
        } else {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notifyError(R.string.generic_server_error);
                }
            });
        }
        act.hideLoadingIndicator();
        Log.e(MapzenApplication.LOG_TAG, "request: error: " + String.valueOf(status));
    }

    private void notifyError(int messageRes) {
        if (act == null) {
            return;
        }
        Toast.makeText(act, act.getString(messageRes), Toast.LENGTH_LONG).show();
    }

    protected void onServerError(RetrofitError error) {
        Toast.makeText(act, act.getString(R.string.generic_server_error), Toast.LENGTH_LONG).show();
        act.hideLoadingIndicator();
        Log.e(MapzenApplication.LOG_TAG, "request: error: " + error.toString());
    }

    public BaseActivity getBaseActivity() {
        return act;
    }
}
