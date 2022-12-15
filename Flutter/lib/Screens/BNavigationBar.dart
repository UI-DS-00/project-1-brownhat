import 'package:flutter/material.dart';
import 'package:animated_bottom_navigation_bar/animated_bottom_navigation_bar.dart';
import 'HomePage.dart';
//import 'Home.dart';
//##########################################################
class BNavigationBar extends StatefulWidget {
  const BNavigationBar({Key ?key}) : super(key: key);

  @override
  State<BNavigationBar> createState() => _MainState();
}

class _MainState extends State<BNavigationBar> {

  List<IconData> iconList = [
    Icons.account_circle_outlined,
    Icons.movie_creation_outlined,
    Icons.favorite_border,
    Icons.star_border,

  ];
  int _bottomNavIndex = -1;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //backgroundColor: Colors.red,
      body: buildPages(),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.home_filled),
        onPressed: (){
          _bottomNavIndex = -1;
          buildPages();
          print(_bottomNavIndex);
        },
        backgroundColor: const Color.fromRGBO(80, 40, 0, 1),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar:
      AnimatedBottomNavigationBar(
        icons: iconList,
        activeIndex: _bottomNavIndex,
        gapLocation: GapLocation.center,
        notchSmoothness: NotchSmoothness.verySmoothEdge,
        leftCornerRadius: 32,
        rightCornerRadius: 32,
        //height: 50,
        onTap: (index) => setState(() => _bottomNavIndex = index),
        backgroundColor: Colors.orangeAccent,
        inactiveColor: Colors.white,


        activeColor: const Color.fromRGBO(80, 40, 0, 1),


        //other params
      ),
    );
  }

  Widget ?buildPages() // returns a class which returns scaffold
  {
    switch(_bottomNavIndex)
    {
      case 1 :
        return const Scaffold(body: Center(child: Text("1"),),backgroundColor: Colors.white);
        break;
      case 2 :
        return const HomePage();
        break;
      default:
        return const Scaffold(body: Center(child: Text("salam"),) , backgroundColor: Colors.white,);

    }
  }
}