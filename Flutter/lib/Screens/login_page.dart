import 'dart:math';
import 'package:flutter/material.dart';
import 'package:flutter_app/Controllers/user_controller.dart';
import 'package:flutter_app/Screens/sign_up_page.dart';
import 'package:flutter_app/widgets/custom_button.dart';
import 'package:get/get.dart';

class LoginScreen extends GetView<UserController> {
  LoginScreen({Key? key}) : super(key: key);
  Rx<Color> BColor = Colors.white60.obs;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20),
          child: SingleChildScrollView(
              child: SizedBox(
            height: context.height,
            child: Column(
              children: [
                const Spacer(),
                Image.asset(
                  'User.png',
                ),
                const SizedBox(
                  height: 40,
                ),
                TextField(
                  controller: controller.userNameController,
                  onChanged: (value) {
                    if (!controller.checkUserNameEmpty() &&
                        !controller.checkPassEmpty()) {
                      BColor.value = Colors.white;
                    } else {
                      BColor.value = Colors.white60;
                    }
                    //print('U : ' + textEmptyU.toString());
                    // setState(() {
                    //   if (userNameController.text.isNotEmpty ||
                    //       passController.text.isNotEmpty) {
                    //     textEmptyU = false;
                    //   } else
                    //     textEmptyU = true;
                    // });
                  },
                  decoration: const InputDecoration(hintText: 'Username'),
                ),
                TextField(
                  obscureText: true,
                  controller: controller.passController,
                  onChanged: (value) {
                    if (!controller.checkUserNameEmpty() &&
                        !controller.checkPassEmpty()) {
                      BColor.value = Colors.white;
                    } else {
                      BColor.value = Colors.white60;
                    }
                    print(BColor.toString());

                    //print('P : ' + textEmptyP.toString());
                    // setState(() {
                    //   if (userNameController.text.isNotEmpty &&
                    //       passController.text.isNotEmpty) {
                    //     textEmptyP = false;
                    //   } else
                    //     textEmptyP = true;
                    // });
                  },
                  decoration: const InputDecoration(hintText: 'Password'),
                ),
                const SizedBox(
                  height: 40,
                ),
                CustomeButton(
                    text: 'LOG IN',
                    onTapped: () {
                      if (!controller.textEmptyP && !controller.textEmptyU) {
                        controller.logIn();
                      }
                    },
                    color: controller.textEmptyU || controller.textEmptyP
                        ? Colors.white60
                        : Colors.white),
                const SizedBox(
                  height: 10,
                ),
                CustomeButton(
                    text: 'CREATE YOUR ACCOUNT',
                    onTapped: () {
                      Get.to(SignUpScreen());
                    },
                    color: Colors.white),
                const SizedBox(
                  height: 110,
                ),
              ],
            ),
          ))),
    );
  }
}
