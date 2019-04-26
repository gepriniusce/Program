package pr.tongson.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

/**
 * <b>Create Date:</b> 2018/9/30<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class FilesUtil {

    /**
     * 获取指定目录内所有文件路径
     *
     * @param dirPath 需要查询的文件目录
     * @param _type   查询类型，比如mp3什么的
     */
    public static JSONArray getAllFiles(String dirPath, String _type) {
        File f = new File(dirPath);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }

        File[] files = f.listFiles();

        if (files == null) {//判断权限
            return null;
        }

        JSONArray fileList = new JSONArray();
        for (File _file : files) {//遍历目录
            if (_file.isFile() && _file.getName().endsWith(_type)) {
                String _name = _file.getName();
                String filePath = _file.getAbsolutePath();//获取文件路径
                String fileName = _file.getName().substring(0, _name.length() - 4);//获取文件名
                //                Log.d("LOGCAT","fileName:"+fileName);
                //                Log.d("LOGCAT","filePath:"+filePath);
                try {
                    JSONObject _fInfo = new JSONObject();
                    _fInfo.put("name", fileName);
                    _fInfo.put("path", filePath);
                    fileList.put(_fInfo);
                } catch (Exception e) {
                }
            } else if (_file.isDirectory()) {//查询子目录
                getAllFiles(_file.getAbsolutePath(), _type);
            } else {
            }
        }
        return fileList;
    } /**
     * 获取指定目录内所有文件路径
     *
     * @param dirPath 需要查询的文件目录
     */
    public static JSONArray getAllFiles(String dirPath) {
        File f = new File(dirPath);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }

        File[] files = f.listFiles();

        if (files == null) {//判断权限
            return null;
        }

        JSONArray fileList = new JSONArray();
        for (File _file : files) {//遍历目录
            if (_file.isFile()) {
                String _name = _file.getName();
                String filePath = _file.getAbsolutePath();//获取文件路径
                String fileName = _file.getName().substring(0, _name.length());//获取文件名
                //                Log.d("LOGCAT","fileName:"+fileName);
                //                Log.d("LOGCAT","filePath:"+filePath);
                try {
                    JSONObject _fInfo = new JSONObject();
                    _fInfo.put("name", fileName);
                    _fInfo.put("path", filePath);
                    fileList.put(_fInfo);
                } catch (Exception e) {
                }
            } else if (_file.isDirectory()) {//查询子目录
                getAllFiles(_file.getAbsolutePath());
            } else {
            }
        }
        return fileList;
    }

}
