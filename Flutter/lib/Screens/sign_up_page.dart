import 'package:flutter/material.dart';
import 'package:flutter_app/Controllers/user_controller.dart';
import 'package:flutter_app/Screens/BNavigationBar.dart';
import 'package:flutter_app/widgets/custom_button.dart';
import 'package:get/get.dart';

class SignUpScreen extends GetView<UserController> {
  SignUpScreen({Key? key}) : super(key: key);
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
                  'SignUp.png',
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
                    text: 'SIGN UP',
                    onTapped: () {
                      if (!controller.textEmptyP && !controller.textEmptyU) {
                        controller.signUp();
                        print(controller.flag.toString());
                        if (controller.flag) {
                          Get.defaultDialog(
                              title: 'Done!',
                              titleStyle: TextStyle(
                                  fontWeight: FontWeight.bold, fontSize: 22),
                              middleText: 'Successfuly Registered',
                              middleTextStyle: TextStyle(fontSize: 18),
                              buttonColor: Colors.amberAccent,
                              textConfirm: 'OK',
                              backgroundColor: Colors.tealAccent,
                              confirmTextColor: Colors.black,
                              onConfirm: () {
                                Get.back();

                                // passController.text = '';
                                // userNameController.text = '';
                                controller.userNameController.clear();
                                controller.passController.clear();
                                BColor.value = Colors.white60;
                                controller.textEmptyU =
                                    true; // to avoid entering button again
                                controller.textEmptyP = true;

                                //Get.back();
                              });
                        }


                        else {
                          Get.defaultDialog(
                              title: 'ERROR!',
                              titleStyle: TextStyle(
                                  fontWeight: FontWeight.bold, fontSize: 22),
                              middleText: 'Username exits',
                              middleTextStyle: TextStyle(fontSize: 18),
                              buttonColor: Colors.amberAccent,
                              textConfirm: 'OK',
                              backgroundColor: Colors.tealAccent,
                              confirmTextColor: Colors.black,
                              onConfirm: () {
                                Get.back();

                                // passController.text = '';
                                // userNameController.text = '';
                                controller.userNameController.clear();
                                controller.passController.clear();
                                BColor.value = Colors.white60;
                                controller.textEmptyU =
                                true; // to avoid entering button again
                                controller.textEmptyP = true;

                                //Get.back();
                              });
                        }


                      }
                      else {
                        Get.defaultDialog(
                            title: 'ERROR!',
                            titleStyle: TextStyle(
                                fontWeight: FontWeight.bold, fontSize: 22),
                            middleText: 'Something wrong has happened',
                            middleTextStyle: TextStyle(fontSize: 18),
                            buttonColor: Colors.amberAccent,
                            textConfirm: 'OK',
                            backgroundColor: Colors.tealAccent,
                            confirmTextColor: Colors.black,
                            onConfirm: () {
                              Get.back();

                              // passController.text = '';
                              // userNameController.text = '';
                              controller.userNameController.clear();
                              controller.passController.clear();
                              BColor.value = Colors.white60;
                              controller.textEmptyU =
                              true; // to avoid entering button again
                              controller.textEmptyP = true;

                              //Get.back();
                            });
                       }

                     },
                    color: BColor.value),
                const SizedBox(
                  height: 160,
                ),
              ],
            ),
          ))),
    );
  }
}
