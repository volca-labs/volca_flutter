package com.volca.volca_flutter;

import androidx.annotation.NonNull;

import com.volca.volcasdk.VolcaSDK;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** VolcaFlutterPlugin */
public class VolcaFlutterPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private FlutterPluginBinding binding;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "volca_flutter");
    channel.setMethodCallHandler(this);
    binding = flutterPluginBinding;
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("init")) {
      VolcaSDK.init(binding.getApplicationContext());
      result.success(null);
    } else if (call.method.equals("hookAll")) {
      result.success(VolcaSDK.hookAll());
      result.success(null);
    } else if (call.method.equals("isAvailable")) {
      result.success(VolcaSDK.isAvailable());
      result.success(null);
    } else if (call.method.equals("block")) {
      VolcaSDK.block();
      result.success(null);
    } else if (call.method.equals("controlFrequency")) {
      VolcaSDK.controlFrequency();
      result.success(null);
    } else if (call.method.equals("disable")) {
      VolcaSDK.disable();
      result.success(null);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
