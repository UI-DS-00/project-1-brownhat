import 'package:flutter/material.dart';

class CustomeButton extends StatelessWidget {
  const CustomeButton({Key? key, required this.text, required this.onTapped , required this.color})
      : super(key: key);
  final String text;
  final VoidCallback onTapped;
  final Color color;
  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(15),
      child: InkWell(
          borderRadius: BorderRadius.circular(15),
          onTap: onTapped,
          child: Container(
            width: double.infinity,
            padding: EdgeInsets.symmetric(vertical: 15),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(15),
              color: Color.fromRGBO(80, 40, 0, 1),
            ),
            child: Center(child: Text(
              text, style: TextStyle(color: color,fontWeight: FontWeight.bold ,fontSize: 18),),),
          ),
      ),
    );
  }
}
