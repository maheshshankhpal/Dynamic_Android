package in.d2k.dynamicproject;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DM365 on 01/12/2017.
 */

public class ClickListener implements View.OnClickListener {

    JSONObject jsonObject;
    MainActivity mainActivity;

    public ClickListener(MainActivity mainActivity, JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        try {
            String on_click_code = jsonObject.getString("on_click_code");
            String on_click_value = jsonObject.getString("on_click_value");

            switch (on_click_code)
            {
                case "C01":{
                    JSONObject jsonObject1 = null;
                    String value = "";
                    try {

                        if (on_click_value.equalsIgnoreCase("root"))
                            jsonObject1 = mainActivity.root.getJSONObject(0);
                        else
                            jsonObject1 = JSONManager.getJSONObjectByKeyValue(mainActivity.child,"ControlName", on_click_value);


                        String controlType = jsonObject1.getString("ControlType");

                        if (controlType.equalsIgnoreCase("TextView")) {
                            TextView textView = (TextView) mainActivity.viewStringHashMap.get(on_click_value);
                            value = textView.getText().toString();
                        }
                        else
                        if (controlType.equalsIgnoreCase("EditText")) {
                            EditText editText = (EditText) mainActivity.viewStringHashMap.get(on_click_value);
                            value = editText.getText().toString();
                        }

                    } catch (Exception e) {

                    }

                    Toast.makeText(mainActivity, value, Toast.LENGTH_LONG).show();
                }
                break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
