package com.project.mylocation.helper

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.project.mylocation.R



class CheckPermission constructor(private var activity: Activity) : AppCompatActivity(){
    val PERMISSION_REQUEST_LOCATION = 0

    var LOCATION_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    fun checkPermission(): Boolean{
        if (ActivityCompat.checkSelfPermission(activity, LOCATION_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(activity, LOCATION_PERMISSIONS[1]) == PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            requestLocationPermission()
            return false
        }
    }

    fun requestLocationPermission(){

        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, LOCATION_PERMISSIONS[0])
            && ActivityCompat.shouldShowRequestPermissionRationale(activity, LOCATION_PERMISSIONS[1])) {
            // 권한 요구
            Log.d(activity.toString(), "requestLoationPermission: 권한 요청")
            ActivityCompat.requestPermissions(activity, LOCATION_PERMISSIONS, PERMISSION_REQUEST_LOCATION)
        } else {
            // 다시 묻지 않음을 누르고 거부하고 요청했을떄
            Log.d(activity.toString(), "requestLoationPermission: 다시 묻지 않음을 누르고 거부 후, 권한 요청")
            AlertDialog.Builder(activity)
                .setTitle("알림")
                .setMessage("GPS 권한이 거부되었다네. 사용을 원한다면 설정에서 해당 권한을 직접 허용하게.")
                .setNeutralButton("설정", object : DialogInterface.OnClickListener {
                    override fun onClick(dialogInterface: DialogInterface, i: Int) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.parse("package:" + activity.packageName)
                        activity.startActivity(intent)
                    }
                })
                .setPositiveButton("확인", object : DialogInterface.OnClickListener {
                    override fun onClick(dialogInterface: DialogInterface, i: Int) {
                        dialogInterface.cancel()
                    }
                })
                .setCancelable(false)
                .create()
                .show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_REQUEST_LOCATION){
            if(grantResults.size == 2
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                // 권한 요쳥 수락
                Log.d(activity.toString(), "onRequestPermissionsResult: 권한 요청 수락")
                // 코드 실행
            } else {
                Log.d(activity.toString(), "onRequestPermissionsResult: 권한 요청 거부")
                // 권한 요청 거부
            }
        }
    }
}