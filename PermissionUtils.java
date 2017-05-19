package cxzx.bdyx.com.mvpdemo.view;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import cxzx.bdyx.com.mvpdemo.MainActivity;

/**
 * Created by QunCheung on 2017/5/18.
 */

public class PermissionUtils extends Activity{
    private Activity activity;
    /**
     * 地理定位权限,此处可以自定义权限常量,以方便使用
     */
    public static String LocalPermission = Manifest.permission.ACCESS_COARSE_LOCATION;
    private  int permissionCode = 0x999;
    private  OnPermissionResultListener listener;

    
    /**
     * 构造方法
     * @param activity 当前界面
     * @param listener 权限是否拥有监听
     */
    public PermissionUtils(Activity activity, OnPermissionResultListener listener) {
        this.activity = activity;
        this.listener = listener;
    }
    
    /**
     * 获取权限
     * @param permission 该参数为权限,可以写成常量
     */
    public void getPermission(String permission){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity,permission)
                    != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{permission},permissionCode);
            }else{
                listener.onPermissionSuccess();
            }
        }
    }

    /**
     * 该方法参数,来自Activity中实现onRequestPermissionsResult后的参数,
     * 需要在Activity中调用
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == permissionCode){
            if (grantResults !=null&&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                listener.onPermissionSuccess();
            } else {
                listener.onPermissionFailed();
            }
        }
    }

    /**
     * 是否拥有权限监听接口
     */
    public interface OnPermissionResultListener{
        void onPermissionSuccess();
        void onPermissionFailed();
    }
}
