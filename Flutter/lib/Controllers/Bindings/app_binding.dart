import 'package:flutter/foundation.dart';
import 'package:flutter_app/Controllers/user_controller.dart';
import 'package:get/get.dart';
import 'package:flutter_app/Controllers/movie_controller.dart';
class AppBinding implements Bindings
{
  void dependencies()
  {
    Get.put<UserController>(UserController());
    Get.put<MovieController>(MovieController());
  }
}