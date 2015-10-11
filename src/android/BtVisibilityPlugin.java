/*

*/
package com.joshy.BtVisibility.BtVisibilityPlugin;



import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.wifi.WifiManager;

import android.bluetooth.BluetoothAdapter;
import android.util.Log;
import android.content.Intent;
import android.app.Activity;


import android.view.View;
import android.view.Menu;
import android.os.Bundle;

import android.content.IntentFilter;
import android.content.BroadcastReceiver;


/**
 * The Class MacAddressPlugin.
 */
public class BtVisibilityPlugin extends CordovaPlugin {
    // Context context;
    // public BtVisibilityPlugin(Context c)
    // {
    //     this.context = c;
    // }

//  public boolean isSynch(String action) {
    //     if (action.equals("getMacAddress")) {
    //         return true;
    //     }
    //     return false;
    // }

    // /*
    //  * (non-Javadoc)
    //  * 
    //  * @see org.apache.cordova.api.Plugin#execute(java.lang.String,
    //  * org.json.JSONArray, java.lang.String)
    //  */
    // @Override
    // public boolean execute(String action, JSONArray args,
    //         CallbackContext callbackContext) {

    //     if (action.equals("getMacAddress")) {

    //         String macAddress = this.getMacAddress();

    //         if (macAddress != null) {
    //             JSONObject JSONresult = new JSONObject();
    //             try {
    //                 JSONresult.put("mac", macAddress);
    //                 PluginResult r = new PluginResult(PluginResult.Status.OK,
    //                         JSONresult);
    //                 callbackContext.success(macAddress);
    //                 r.setKeepCallback(true);
    //                 callbackContext.sendPluginResult(r);
    //                 return true;
    //             } catch (JSONException jsonEx) {
    //                 PluginResult r = new PluginResult(
    //                         PluginResult.Status.JSON_EXCEPTION);
    //                 callbackContext.error("error");
    //                 r.setKeepCallback(true);
    //                 callbackContext.sendPluginResult(r);
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }


    private boolean enableBtVisibility( ) {
        // Context oContext = mContext;

        // // BluetoothAdapter ba =  BluetoothAdapter.getDefaultAdapter();
        // // Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE); //request user to turn on
        // // discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 180); //extends it
        // // Context.startActivity(discoverableIntent); //does it
        
        // if(D) Log.d(TAG, "ensure discoverable");
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        //     if (ba.getScanMode() !=
        //             ba.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
        //         Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //         discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        //         startActivity(discoverableIntent);
        // }

        // return true;
        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);
        ba.startDiscovery();



    }
        private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                showToast("Found device " + device.getName());
            }
        }
    };
}