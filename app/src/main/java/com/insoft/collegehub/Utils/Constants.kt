package com.insoft.collegehub.Utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore

class Constants {
    companion object{
        const val CUSTOMER_ID = "customerId"
        const val DIALOG = "successDialog"
        const val REGISTERED_USER = "registered"
        const val LOGIN_USER = "login"
        const val LOCAL_STORAGE = "MyPrefs"
        const val CODE = "code"
        const val STATUS = "status"
        const val MESSAGE = "message"
        const val USER_PROFILE_STATUS = "userProfileStatus"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val MOBILE_NO = "mobileNumber"
        const val EMAIL = "email"
        const val ISRESPONDER = "isResponder"
        const val userStatusFlag = "flag"
        const val RATECLICKED = "false"
        const val LETSGETSTARTEDCLICKED = "false"
    }
}

object Utils {
    fun getRealPathFromUri(context: Context, uri: Uri): String {
        var realPath = ""
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            realPath = cursor.getString(idx)
            cursor.close()
        }
        return realPath
    }
}
