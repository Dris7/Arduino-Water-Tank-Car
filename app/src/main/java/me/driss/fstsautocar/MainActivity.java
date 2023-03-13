package me.driss.fstsautocar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import android.widget.CompoundButton;

import com.example.fstsautocar.R;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private ImageButton mButtonTop, mButtonLeft, mButtonRight, mButtonBottom;
    private Slider mSlider;
    private Toolbar mToolbar;

    //pomp
    private SwitchMaterial switchButton;
    private boolean pumpState = false;
    //pour voir si carte Bluetooth est compatible avec la carte Bluetooth du mobile
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String deviceAddress="00:21:09:01:25:35";
    private BluetoothSocket btSocket = null;

    @SuppressLint("MissingPermissihhon")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_FstsAutoCar);
        setContentView(R.layout.activity_main);

        switchButton = findViewById(R.id.switch_button);
        mButtonTop = findViewById(R.id.button_top);
        mButtonLeft = findViewById(R.id.button_left);
        mButtonRight = findViewById(R.id.button_right);
        mButtonBottom = findViewById(R.id.button_bottom);
        mSlider = findViewById(R.id.slider);

        //getSupportActionBar().hide();
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        String baseUrl = "http://192.168.4.1";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);


        mButtonTop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(isConnectedToWifi()){
                        retrofitApi.setState("F").enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    retrofitApi.setState("S").enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

                return false;
            }


        });

        mButtonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(isConnectedToWifi()){
                        retrofitApi.setState("R").enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    retrofitApi.setState("S").enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

                return false;
            }


        });

        mButtonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(isConnectedToWifi()){
                        retrofitApi.setState("L").enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    retrofitApi.setState("S").enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

                return false;
            }


        });

        mButtonBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(isConnectedToWifi()){
                        retrofitApi.setState("B").enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    retrofitApi.setState("S").enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

                return false;
            }


        });


        //slider
        mSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                int a = (int) value;
                if(isConnectedToWifi()){
                retrofitApi.setState("" + a).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("tag", "onResponse: " + response);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("tag", "onResponse: " + t);
                    }
                });
                }
            }
        });



        /***********pomp**********/
        CreateConnectThread connectThread = new CreateConnectThread();
        connectThread.start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         try {
             if(btSocket!=null){
                 btSocket.close();
                 Log.d("tag","in destroy isConnect: "+btSocket.isConnected());
             }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


public class CreateConnectThread extends Thread{

    @SuppressLint("MissingPermission")
    @Override
    public void run() {
        if(isBluetoothEnabled()){
        //cet objet contain les information de BluetoothAdapter du mobile
            // (reprisante le Bluetooth de mobile qui utilise l'application)
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        //cet objet reprisante l'application avec l'aquelle on veut communiquer
        BluetoothDevice hc05 = btAdapter.getRemoteDevice(deviceAddress);

        int counter = 0;
        //pour assurer la fiabilité du communication on va répéter la connection 3 fois
        do {
            try {
                //la connaiction ce fait à l'aide d'une socket
                btSocket = hc05.createRfcommSocketToServiceRecord(MY_UUID);

                btSocket.connect();
                Log.d("tag","is connect: "+btSocket.isConnected());

            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;

            if(counter>3) break;
        } while (!btSocket.isConnected());

        //après la connection maintenant on peut envoyer est recevoire des données
        try {
            //on utilise outputStream pour l'échange des informations
            OutputStream outputStream = btSocket.getOutputStream();
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isBluetoothEnabled()){
                        pumpState = isChecked;
                        if (pumpState) {
                            try {
                                //envoyer 1 ==>(alumé la pomp)
                                outputStream.write("1".getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                //envoyer 0 ==>(éteint la pomp)
                                outputStream.write("0".getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }else{

        }
    }
}

    private boolean isBluetoothEnabled() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Le périphérique ne prend pas en charge Bluetooth
            Toast.makeText(this, "Le périphérique ne prend pas en charge Bluetooth", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            // Bluetooth n'est pas activé, afficher une alerte pour demander à l'utilisateur de l'activer
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Bluetooth non activé");
                    builder.setMessage("Veuillez activer Bluetooth pour continuer.");
                    builder.setPositiveButton("Activer", new DialogInterface.OnClickListener() {
                        @SuppressLint("MissingPermission")
                        public void onClick(DialogInterface dialog, int id) {
                            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivity(enableBtIntent);
                        }
                    });
                    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // L'utilisateur a annulé la boîte de dialogue
                            dialog.dismiss();
                            // Destroy the main activity
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    MainActivity.this.finish();
                                }
                            });
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            return false;
        } else {
            // Bluetooth est déjà activé
            return true;
        }
    }

    public boolean isConnectedToWifi() {
        // l'objet ConnectivityManager  permet de gérer les différents types de connectivités (Wi-Fi, GSM, etc.)
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //obtenir les informations sur le réseau actuellement actif sur l'appareil
        @SuppressLint("MissingPermission") NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        //vérifier si le mobile est conneté à un réseau
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            // Wi-Fi n'est pas connecté, afficher une alerte pour demander à l'utilisateur de se connecter
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Wi-Fi non connecté");
                    builder.setMessage("Veuillez vous connecter à un réseau Wi-Fi pour continuer.");
                    builder.setPositiveButton("Connecter", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    });
                    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // L'utilisateur a annulé la boîte de dialogue
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            return false;
        } else {
            // Wi-Fi est connecté
            return true;
        }
    }

}





