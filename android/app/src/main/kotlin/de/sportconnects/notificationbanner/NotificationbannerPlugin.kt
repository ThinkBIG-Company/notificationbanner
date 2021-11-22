package de.sportconnects.notificationbanner

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.util.*
import com.shasin.notificationbanner.Banner;

/**
 * NotificationBannerPlugin
 */
class NotificationBannerPlugin : MethodCallHandler, FlutterPlugin {

    private var _context: Context? = null
    private var _channel: MethodChannel? = null
    private val TAG = "NotificationBannerPlugin"
    var channelName = "notificationbanner"

    @SuppressLint("LongLogTag")
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        val context = _context;
        val channel = _channel;

        if (context == null || channel == null) {
            Log.w(TAG, "Calling Notificationbanner plugin before initialization")
            return
        }

        when (call.method) {
            "getNotificationBanner" -> {
                getNotificationBanner(context)
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        init(binding.applicationContext, binding.binaryMessenger)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        _channel?.setMethodCallHandler(null)
        _context = null
        _channel = null
    }

    private fun init(context: Context, messenger: BinaryMessenger) {
        val channel = MethodChannel(messenger, channelName)
        channel.setMethodCallHandler(this)
        _context = context
        _channel = channel
    }

    private fun getNotificationBanner(context: Context) {
        Banner.make(findViewById(android.R.id.content), context, Banner.SUCCESS, "This is a successful message", Banner.TOP).show();
    }

    companion object {
        private const val TAG = "notificationbanner"

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val instance = NotificationBannerPlugin()
            instance.init(registrar.context(), registrar.messenger())
        }
    }
}