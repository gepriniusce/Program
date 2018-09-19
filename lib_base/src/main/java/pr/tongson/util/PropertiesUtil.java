package pr.tongson.util;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <b>Create Date:</b> 2018/9/12<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class PropertiesUtil {

    String propertiesName = "setting.properties";
    String propertiesKey = "app";


    /**
     * 获取app.properties的配置的值
     *
     * @param context 上下文
     * @param key     关键值
     */
    public static String getAppProperties(Context context, String propertiesName, String key) {
        try {
            Properties prop = new Properties();
            InputStream in = context.getAssets().open(propertiesName);
            prop.load(in);
            in.close();
            String value = prop.getProperty(key);
            if (TextUtils.isEmpty(value)) {
                return null;
            } else {
                return value.trim();
            }
        } catch (IOException e) {
        }
        return null;
    }
//
//    public void setAppProperties(Context context, String key, String value) {
//        try {
//            Properties prop = new Properties();
//            InputStream in = context.getAssets().open(propertiesName);
//            prop.load(in);
//            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(propertiesName);
//            context.getClass().getResourceAsStream("/assets/app.properties");
//
//            URL url = context.getClass().getResource("/assets/app.properties");
//            url.getPath();//获取配置文件的路径
//
//
//            OutputStream fos = context.getAssets().openFd(propertiesName).createOutputStream();
//            prop.setProperty(key, value);
//            prop.store(fos, "Update '" + key + "' value");
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
