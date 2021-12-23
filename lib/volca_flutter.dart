
import 'dart:async';
import 'dart:ffi';

import 'package:flutter/services.dart';

class VolcaFlutter {
  static const MethodChannel _channel = MethodChannel('volca_flutter');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> init() async {
    await _channel.invokeMethod('init');
  }

  static Future<void> hookAll() async {
    await _channel.invokeMethod('hookAll');
  }

  static Future<Bool?> isAvailable() async {
    final Bool? version = await _channel.invokeMethod('isAvailable');
    return version;
  }

  static Future<void> block() async {
    await _channel.invokeMethod('block');
  }

  static Future<void> controlFrequency() async {
    await _channel.invokeMethod('controlFrequency');
  }

  static Future<void> disable() async {
    await _channel.invokeMethod('disable');
  }
}
