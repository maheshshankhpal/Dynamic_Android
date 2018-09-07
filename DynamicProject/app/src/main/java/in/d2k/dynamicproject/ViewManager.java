package in.d2k.dynamicproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DM365 on 01/12/2017.
 */

public class ViewManager {
    View getView(final Context context, final JSONObject jsonObject) throws JSONException {

        View v = null;

        String ControlType = jsonObject.getString("ControlType");

        switch (ControlType) {
            case "TextView": {
                TextView t = new TextView(context);
                t.setText(jsonObject.getString("text"));

                int width = -1, height = -1;

                try {
                    String layout_width = jsonObject.getString("layout_width");

                    if (layout_width.equalsIgnoreCase("match_parent"))
                        width = ViewGroup.LayoutParams.MATCH_PARENT;
                    else if (layout_width.equalsIgnoreCase("wrap_content"))
                        width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    else
                        width = Integer.parseInt(layout_width);
                } catch (Exception e) {
                }


                try {
                    String layout_height = jsonObject.getString("layout_height");

                    if (layout_height.equalsIgnoreCase("match_parent"))
                        height = ViewGroup.LayoutParams.MATCH_PARENT;
                    else if (layout_height.equalsIgnoreCase("wrap_content"))
                        height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    else
                        height = Integer.parseInt(layout_height);
                } catch (Exception e) {
                }

                t.setLayoutParams(new ViewGroup.LayoutParams(width, height));

                return t;
            }
            case "EditText": {
                EditText t = new EditText(context);
                //t.setText(jsonObject.getString("text"));

                int width = -1, height = -1;

                try {
                    String layout_width = jsonObject.getString("layout_width");

                    if (layout_width.equalsIgnoreCase("match_parent"))
                        width = ViewGroup.LayoutParams.MATCH_PARENT;
                    else if (layout_width.equalsIgnoreCase("wrap_content"))
                        width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    else
                        width = Integer.parseInt(layout_width);
                } catch (Exception e) {
                }


                try {
                    String layout_height = jsonObject.getString("layout_height");

                    if (layout_height.equalsIgnoreCase("match_parent"))
                        height = ViewGroup.LayoutParams.MATCH_PARENT;
                    else if (layout_height.equalsIgnoreCase("wrap_content"))
                        height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    else
                        height = Integer.parseInt(layout_height);
                } catch (Exception e) {
                }

                t.setLayoutParams(new ViewGroup.LayoutParams(width, height));

                return t;
            }
            case "Button": {
                Button b = new Button(context);
                b.setText(jsonObject.getString("text"));

                int width = -1, height = -1;

                try {
                    String layout_width = jsonObject.getString("layout_width");

                    if (layout_width.equalsIgnoreCase("match_parent"))
                        width = ViewGroup.LayoutParams.MATCH_PARENT;
                    else if (layout_width.equalsIgnoreCase("wrap_content"))
                        width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    else
                        width = Integer.parseInt(layout_width);
                } catch (Exception e) {
                }


                try {
                    String layout_height = jsonObject.getString("layout_height");

                    if (layout_height.equalsIgnoreCase("match_parent"))
                        height = ViewGroup.LayoutParams.MATCH_PARENT;
                    else if (layout_height.equalsIgnoreCase("wrap_content"))
                        height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    else
                        height = Integer.parseInt(layout_height);
                } catch (Exception e) {
                }


                b.setLayoutParams(new ViewGroup.LayoutParams(width, height));

                return b;
            }
        }

        return v;
    }
}
