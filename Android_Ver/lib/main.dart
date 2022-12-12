import 'package:flutter/material.dart';
import 'package:easy_splash_screen/easy_splash_screen.dart';
import 'BNavigationBar.dart';
void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    home: SplashPage(),
  ));
}
//########################################################
class SplashPage extends StatefulWidget {
  SplashPage({Key key}) : super(key: key);

  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {

  static const s = TextStyle(fontFamily: "fredoka" , fontSize: 25);


  @override
  Widget build(BuildContext context) {
    return SafeArea(child: EasySplashScreen(
      logo: Image.asset('popcorn.png'),
      title: const Text("IMDb",
        style: s
      ),
      backgroundColor: Colors.grey.shade400,
      showLoader: true,
      loadingText: const Text("Loading..."),
      navigator: const BNavigationBar(),
      durationInSeconds: 5,
    )
    );
  }
}
