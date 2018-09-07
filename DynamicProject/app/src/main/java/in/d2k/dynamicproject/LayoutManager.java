package in.d2k.dynamicproject;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DM365 on 01/12/2017.
 */

public class LayoutManager {
    ViewGroup getLayout(Context context, JSONObject jsonObject) throws JSONException {
        ViewGroup viewGroup = null;

        String ControlType = jsonObject.getString("ControlType");

        switch (ControlType) {
            case "LinearLayout": {
                LinearLayout l = new LinearLayout(context);
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


                l.setLayoutParams(new LinearLayout.LayoutParams(width, height));

                String orientation = jsonObject.getString("orientation");
                if (orientation.equalsIgnoreCase("vertical"))
                    l.setOrientation(LinearLayout.VERTICAL);
                else if (orientation.equalsIgnoreCase("horizontal"))
                    l.setOrientation(LinearLayout.HORIZONTAL);

                return l;
            }
        }

        return viewGroup;
    }
}
