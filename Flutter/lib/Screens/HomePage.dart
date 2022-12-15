import 'package:flutter/material.dart';
import 'package:get/get.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Center(
      child: ElevatedButton(
        onPressed: () {
          Get.snackbar(
              "Hello",
              "Whats Up?",
              backgroundGradient: LinearGradient(colors: [Colors.red, Colors.blue])
          );
        },
        child: Text("push"),
      ),
    ));
  }
}
