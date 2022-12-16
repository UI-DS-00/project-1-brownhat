import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/Screens/BNavigationBar.dart';
import 'package:get/get.dart';
import 'package:flutter/material.dart';

class SplashScreenController extends GetxController {
  final RxString _connectionStatus = '-1'.obs;
  Rx<ConnectivityResult> result = ConnectivityResult.none.obs;

  final Rx<Connectivity> _connectivity = Connectivity().obs;

  Future<void> _updateConnectionStatus(ConnectivityResult result) async {
    switch (result) {
      case ConnectivityResult.wifi:
        _connectionStatus.value = 'wifi net';
        break;
      case ConnectivityResult.mobile:
        _connectionStatus.value = "mobile net";
        break;
      case ConnectivityResult.none:
        _connectionStatus.value = '-1';
        Get.snackbar(
          'No internet connection.',
          'Please turn pn mobile data or wifi',
          backgroundGradient:
              LinearGradient(colors: [Colors.orange, Colors.purple]),
        );
        break;
      default:
        _connectionStatus.value = 'Failed to get connectivity';
        break;

    }
    if(_connectionStatus.value != '-1')
      {
        Future.delayed(const Duration(seconds: 3) , (){
          Get.to(const BNavigationBar());
        });
      }
  }
  Future<void> initConnectivity() async {

    try
    {
      result.value = await _connectivity.value.checkConnectivity();
    } on PlatformException catch (e)
    {
      print(e.toString());
    }

    return _updateConnectionStatus(result.value);
  }

  @override
  void onInit() {
    // TODO: implement onInit
    super.onInit();
    initConnectivity();
    ever(result, (_)
    {
      _connectivity.value.onConnectivityChanged.listen((_updateConnectionStatus));
    });
  }




}
