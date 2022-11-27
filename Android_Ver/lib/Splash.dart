import 'package:flutter/material.dart';
import 'package:easy_splash_screen/easy_splash_screen.dart';
import 'Home.dart';
void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: true,
    home: SplashPage(),
  ));
}
//########################################################
class SplashPage extends StatefulWidget {
  SplashPage({Key? key}) : super(key: key);

  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {

  static const s = TextStyle(fontFamily: "fredoka" , fontSize: 25);
  @override
  Widget build(BuildContext context) {
    return EasySplashScreen(
      logo: Image.asset('popcorn.png'),
      title: Text("IMDb",
        style: s
      ),
      backgroundColor: Colors.grey.shade400,
      showLoader: true,
      loadingText: const Text("Loading..."),
      navigator: const Home(),
      durationInSeconds: 5,
    );
  }
}
