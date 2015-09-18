package se.frand.app.dice;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends Activity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    Random r;
    AudioManager mAudioManager;
    SoundPool dice_sound = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
    int sound_id;
    DiceAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        sound_id = dice_sound.load(this,R.raw.shake_dice,1);

        mAdapter  = new DiceAdapter(this);

        r = new Random();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(mAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ImageView view = (ImageView)v;
                mAdapter.mArray[position] = roll();
                mAdapter.notifyDataSetChanged();
                dice_sound.play(sound_id, 0.99f, 0.99f, 1, 0, 1.0f);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void rollAll(View v) {

        int count = mAdapter.getCount();
        for(int i=0;i<count;i++) {
            mAdapter.mArray[i]  = roll();
        }
        dice_sound.play(sound_id,0.99f,0.99f,1,0,1.0f);

        mAdapter.notifyDataSetChanged();
    }

    private int roll() {

        return r.nextInt(6);
    }
}
