import 'dart:html';

import 'package:flutter/cupertino.dart';
import 'package:flutter_app/models/movie.dart';
import 'package:get/get.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert' as convert;
import 'package:dio/dio.dart';

class UserController extends GetxController {
  TextEditingController userNameController = TextEditingController();
  TextEditingController passController = TextEditingController();
  String accessToken = '-1';
  String refreshToken = '-1';
  bool textEmptyU = true;
  bool flag = true;
  bool textEmptyP = true;

  bool checkUserNameEmpty() {
    if (userNameController.text.isNotEmpty) {
      this.textEmptyU = false;
    } else
      this.textEmptyU = true;

    return textEmptyU;
  }

  bool checkPassEmpty() {
    if (passController.text.isNotEmpty) {
      this.textEmptyP = false;
    } else
      this.textEmptyP = true;
    return textEmptyP;
  }

  signUp() async {
    String username = userNameController.text;
    String password = passController.text;
    if (username.isEmpty) {
      return;
    }
    if (password.isEmpty || password.length < 8) {
      return;
    }

    try {
      String username = userNameController.text;
      String password = passController.text;
      var dio = Dio();
      var response =
          await dio.post('http://192.168.137.1:8080/api/signup', data: {
        'username': username,
        'password': password,
      });
      print(response.statusCode);
      //flag = false;
     flag = response.data['response'];
    } catch (e, s) {
      Get.snackbar(s.toString(), e.toString(),
          backgroundGradient:
          LinearGradient(colors: [Colors.orangeAccent, Colors.purple]));
      print('jjhkj');
      print(s);
      print(e);
    }
  }

  logIn() async {
    String userName = userNameController.text;
    String password = passController.text;
    if (userName.isEmpty) {
      return;
    }
    if (password.isEmpty || password.length < 0) {
      return;
    }

    try {
      String username = userNameController.text;
      String password = passController.text;
      var dio = Dio();
      // var response  = await dio.post('http://192.168.137.1:8080/api/login' , data: {
      //   'username' : username,
      //   'password' : password,
      // }
      // );

      var response = await dio.get('http://192.168.137.1:8080/api/login',
          queryParameters: {'username': username, 'password': password});
      print(response.statusCode);
      accessToken = response.data['accesstoken'];
      refreshToken = response.data['refreshtoken'];
      //
      // print( 'login is : ' + response.data['response']);
      //flag = response.data['response'];
    } catch (e, s) {
      print(s);
      print(e);
      Get.snackbar(s.toString(), e.toString(),
          backgroundGradient:
              LinearGradient(colors: [Colors.orangeAccent, Colors.purple]));
    }
  }
}
