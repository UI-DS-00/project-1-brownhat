import 'package:flutter/foundation.dart';
import 'package:flutter_app/Controllers/user_controller.dart';
import 'package:get/get.dart';

class AppBinding implements Bindings
{
  void dependencies()
  {
    Get.put<UserController>(UserController());
  }
}