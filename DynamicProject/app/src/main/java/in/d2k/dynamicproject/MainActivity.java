package in.d2k.dynamicproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

    ViewGroup rootLayout;

    HashMap<String, View> viewStringHashMap;

    JSONArray child, root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewStringHashMap = new HashMap<>();

        readJSON();
    }

    public JSONObject getJSONObjectByKeyValue(String key, String value) throws JSONException {
        JSONObject jsonObject = null;

        if (value.equalsIgnoreCase("root"))
            return root.getJSONObject(0);
        else {
            for (int index = 0; index < child.length(); index++) {
                if (child.getJSONObject(index).getString(key).equalsIgnoreCase(value))
                    return child.getJSONObject(index);
            }
        }
        return jsonObject;
    }

    public String loadJSONFromAsset(InputStream inputStream) {
        String json = null;
        try {

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {

            return null;
        }
        return json;
    }

    void readJSON() {
        try {
            InputStream inputStream = getAssets().open("layout.json");
            JSONObject obj = new JSONObject(loadJSONFromAsset(inputStream));

            root = obj.getJSONArray("root");

            LayoutManager layoutManager = new LayoutManager();
            rootLayout = layoutManager.getLayout(this, root.getJSONObject(0));
            viewStringHashMap.put(root.getJSONObject(0).getString("ControlName"), rootLayout);

            setContentView(rootLayout);

            child = obj.getJSONArray("child");
            for (int index = 0; index < child.length(); index++) {
                JSONObject jsonObject = child.getJSONObject(index);
                ViewManager viewManager=new ViewManager();

                View v = null;
                if(jsonObject.getString("ControlType").contains("Layout"))
                {
                    v= layoutManager.getLayout(this,jsonObject);
                }
                else
                {
                    v= viewManager.getView(this,jsonObject);
                    v.setOnClickListener(new ClickListener(this,jsonObject));
                }
                viewStringHashMap.put(jsonObject.getString("ControlName"), v);
                addView(v, jsonObject);
                //  rootLayout.addView(v);
            }
        } catch (Exception e) {
        }
    }

    void addView(View view, JSONObject jsonObject) throws JSONException {
        String ParentControlName = jsonObject.getString("ParentControlName");
        String ParentControlType = jsonObject.getString("ParentControlType");

        if (ParentControlType.equalsIgnoreCase("LinearLayout")) {
            LinearLayout linearLayout = (LinearLayout) viewStringHashMap.get(ParentControlName);
            linearLayout.addView(view);
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
