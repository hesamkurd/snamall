package ir.zhiran2021.snamall.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

object UtilSocial {
    fun isInstallPackgName(context: Context, packagename: String?): Boolean {
        val manager = context.packageManager
        return try {
            manager.getPackageInfo(packagename!!, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
//            e.printStackTrace();
            false
        }
    }

    fun instagram(context: Context, result: String) {
        val instagramPackageName = "com.instagram.android"
        val isAppInsatll = isInstallPackgName(context, instagramPackageName)
        if (isAppInsatll) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/$result"))
            context.startActivity(intent)
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/$result"))
            context.startActivity(intent)
        }
    }

    fun telegram(context: Context, result: String) {
        val telegramPackageName = "org.telegram.messenger"
        val isAppInsatll = isInstallPackgName(context, telegramPackageName)
        if (isAppInsatll) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/$result"))
            context.startActivity(intent)
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/$result"))
            context.startActivity(intent)
        }
    }

    fun whatsApp(context: Context, result: String) {
        val whatsappPackageName = "com.whatsapp"
        val isAppInsatll = isInstallPackgName(context, whatsappPackageName)
        if (isAppInsatll) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+98$result"))
            context.startActivity(intent)
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+98$result"))
            context.startActivity(intent)
        }
    }

    fun youtube(context: Context, result: String) {
        val whatsappPackageName = "com.google.android.youtube"
        val isAppInsatll = isInstallPackgName(context, whatsappPackageName)
        if (isAppInsatll) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://youtube.com/channel/$result"))
            context.startActivity(intent)
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://youtube.com/channel/$result"))
            context.startActivity(intent)
        }
    }
}