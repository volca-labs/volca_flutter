import 'dart:math';

import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:volca_flutter/volca_flutter.dart';

import 'package:network_info_plus/network_info_plus.dart';

void main() {
  const MethodChannel channel = MethodChannel('volca_flutter');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() async {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await VolcaFlutter.platformVersion, '42');
  });

  test('wifi', () async {

    await VolcaFlutter.init();
    await VolcaFlutter.hookAll();
    print("VolcaFlutter init");

    final info = NetworkInfo();
    var wifiBSSID = await info.getWifiBSSID();

    expect(wifiBSSID, "123456");
  });
}
