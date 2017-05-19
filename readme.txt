1.在Activity中初始化
permissionUtils = new PermissionUtils(this,this);
2.在需要调用的发放中,调用请求权限方法
permissionUtils.getPermission(PermissionUtils.LocalPermission);
3.重写Activity的onRequestPermissionsResult()方法,如下
@Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {     super.onRequestPermissionsResult(requestCode, permissions, grantResults);     permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults); }
4.权限是否允许在监听回调方法中
@Override public void onPermissionSuccess() {     Toast.makeText(this,"权限请求成功",Toast.LENGTH_SHORT).show(); }
@Override public void onPermissionFailed() {     Toast.makeText(this,"权限请求被拒绝",Toast.LENGTH_SHORT).show(); }



