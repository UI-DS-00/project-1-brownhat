import 'package:flutter/material.dart';

class BaseWidget extends StatelessWidget {
  BaseWidget({Key? key , this.appBar , required this.child}) : super(key: key);
  final AppBar? appBar;
  final Widget? child;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: appBar,
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20.0),
          child: child,
        ),
    );
  }
}
