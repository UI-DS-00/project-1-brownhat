import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert' as convert;

class UserController extends GetxController {
  TextEditingController userNameController = TextEditingController();
  TextEditingController passController = TextEditingController();
  bool textEmptyU = true;

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
      http.Response response = await http.post(Uri.parse('http://192.168.137.1:8080/api/signup'),
          headers: <String,String>
          {
            'Content-Type' : 'application/json'
          },
          body: <String , String>
          {
            "username": username,
            "password": password,
          }
          // headers: {
          //   '': '',
          //   '': '',
          // }
          );
      // if (response.statusCode < 300) {
      //   Map responseMap = convert.jsonDecode(response.body);
      //   if (!responseMap['response']) {
      //     //username is repited!
      //     return;
      //   }
      //   String token = response.body;
      //
      //   SharedPreferences pref = await SharedPreferences.getInstance();
      //   await pref.setString('token', responseMap['token']);
      //   await pref.setString('username', username);
      //   await pref.setString('password', password);


      //}
    print(response.body);
    print(response.statusCode);
    } catch (e, s) {
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
    if (password.isEmpty || password.length < 8) {
      return;
    }

    try {
      http.Response response = await http.post(Uri.parse(''),
          body: convert.jsonEncode({
            'username': userName,
            'password': password,
          }),
          headers: {
            '': '',
            '': '',
          });
      String token = response.body;

      SharedPreferences pref = await SharedPreferences.getInstance();
      await pref.setString('token', token);
      await pref.setString('userName', userName);
      await pref.setString('password', password);
      print(response.body);
      print(response.statusCode);
    } catch (e, s) {
      print(s);
      print(e);
    }
  }
}
