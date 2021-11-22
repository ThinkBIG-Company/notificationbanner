import 'dart:async';

import 'package:flutter/services.dart';

class FlutterAlertDemo {
  static const MethodChannel _channel =
      const MethodChannel('notificationbanner');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static get showNotifiactionBanner async {
    await _channel.invokeMethod('getNotificationBanner');
  }
}
