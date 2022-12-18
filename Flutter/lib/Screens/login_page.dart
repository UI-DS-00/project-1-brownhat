import 'dart:math';
import 'package:flutter/material.dart';
import 'package:flutter_app/Screens/sign_up_page.dart';
import 'package:flutter_app/widgets/custom_button.dart';
import 'package:get/get.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  TextEditingController userNameController = TextEditingController();
  TextEditingController passController = TextEditingController();

  bool textEmptyU = true;
  bool textEmptyP = true;

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
                  controller: userNameController,
                  onChanged: (value) {
                    setState(() {
                      if (userNameController.text.isNotEmpty) {
                        textEmptyU = false;
                      } else
                        textEmptyU = true;
                    });
                  },
                  decoration: const InputDecoration(hintText: 'Username'),
                ),
                TextField(
                  obscureText: true,
                  controller: passController,
                  onChanged: (value) {
                    setState(() {
                      if (passController.text.isNotEmpty) {
                        textEmptyP = false;
                      } else
                        textEmptyP = true;
                    });
                  },
                  decoration: const InputDecoration(hintText: 'Password'),
                ),
                const SizedBox(
                  height: 40,
                ),
                CustomeButton(
                    text: 'LOG IN',
                    onTapped: () {},
                    color: textEmptyU || textEmptyP
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