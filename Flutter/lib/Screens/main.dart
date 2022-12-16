import 'package:flutter/material.dart';
import 'package:flutter/material.dart';
import 'package:easy_splash_screen/easy_splash_screen.dart';
import 'package:get/get.dart';
import 'BNavigationBar.dart';
import 'package:flutter_app/Controllers/splash_screen_controller.dart';
void main() {
  runApp(GetMaterialApp(
    debugShowCheckedModeBanner: false,
    home: SplashScreen(),
  ));
}

class SplashScreen extends StatefulWidget {
  SplashScreen({Key? key}) : super(key: key);
  SplashScreenController controller = Get.put<SplashScreenController>(SplashScreenController());
  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  static const s = TextStyle(fontFamily: "fredoka" , fontSize: 25);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Image.asset('popcorn.png'),
            const SizedBox(height: 25,),
            Text('IMDb' ,style: s,),



          ],
        ),
      ),

    );
  }
}
