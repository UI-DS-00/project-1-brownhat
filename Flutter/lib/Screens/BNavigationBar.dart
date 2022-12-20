import 'package:flutter/material.dart';
import 'package:animated_bottom_navigation_bar/animated_bottom_navigation_bar.dart';
import 'package:flutter_app/Screens/movie_screen.dart';
import 'package:get/get.dart';
import 'HomePage.dart';
import 'login_page.dart';
//import 'Home.dart';
//##########################################################
class BNavigationBar extends StatefulWidget {
  const BNavigationBar({Key ?key}) : super(key: key);
  @override
  State<BNavigationBar> createState() => _MainState();
}

class _MainState extends State<BNavigationBar> {
  final RxInt _bottomNavIndex = (-1).obs;

  List<IconData> iconList = [
    Icons.account_circle_outlined,
    Icons.movie_creation_outlined,
    Icons.favorite_border,
    Icons.star_border,

  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: buildPages(),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.home_filled),
        onPressed: (){
          _bottomNavIndex.value = -1;
          buildPages();
          print(_bottomNavIndex);
        },
        backgroundColor: const Color.fromRGBO(80, 40, 0, 1),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar:
      AnimatedBottomNavigationBar(
        icons: iconList,
        activeIndex: _bottomNavIndex.value,
        gapLocation: GapLocation.center,
        notchSmoothness: NotchSmoothness.verySmoothEdge,
        leftCornerRadius: 32,
        rightCornerRadius: 32,
        //height: 50,
        onTap: (index) => setState(() => _bottomNavIndex.value = index),
        backgroundColor: Colors.orangeAccent,
        inactiveColor: Colors.white,


        activeColor: const Color.fromRGBO(80, 40, 0, 1),


        //other params
      ),
    );
  }

  Widget ?buildPages() // returns a class which returns scaffold
  {
    switch(_bottomNavIndex.value)
    {
      case 0 :
        return LoginScreen();
        break;
      case 1 :
        return MovieScreen();
        break;

      case 2 :
        return const HomePage();
        break;
      default:
        return const Scaffold(body: Center(child: Text("salam"),) , backgroundColor: Colors.white,);

    }
  }
}