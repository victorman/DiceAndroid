package se.frand.app.dice;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by victorfrandsen on 9/16/15.
 */
public class DiceAdapter extends BaseAdapter implements Preference.OnPreferenceChangeListener{
    private static final String LOG_TAG = DiceAdapter.class.getSimpleName();
    private Context mContext;
    public int[] mArray;
    private int diecnt;
    private SharedPreferences prefs;

    public DiceAdapter(Context c) {
        mContext = c;
        prefs  = PreferenceManager.getDefaultSharedPreferences(mContext);
        diecnt = Integer.valueOf(prefs.getString(
                mContext.getString(R.string.pref_num_key),
                mContext.getString(R.string.pref_num_default)));
        mArray = new int[diecnt];
        //this shit defaults to 0; we want it to default to -1
        onPreferenceChange(null, diecnt);
    }

    @Override
    public int getCount() {
        return diecnt;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        setImage(mArray[position],imageView);

        return imageView;
    }

    public static void setImage(int i, ImageView v) {
        switch (i) {
            case 0:
                v.setImageResource(R.drawable.die1_300px);
                break;
            case 1:
                v.setImageResource(R.drawable.die2_300px);
                break;
            case 2:
                v.setImageResource(R.drawable.die3_300px);
                break;
            case 3:
                v.setImageResource(R.drawable.die4_300px);
                break;
            case 4:
                v.setImageResource(R.drawable.die5_300px);
                break;
            case 5:
                v.setImageResource(R.drawable.die6_300px);
                break;
            default:
                v.setImageResource(R.drawable.die0_300px);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        diecnt = Integer.valueOf(newValue.toString());
        mArray = new int[diecnt];
        for(int i=0;i<mArray.length;i++) {
            mArray[i] = -1;
        }

        return true;
    }
}
