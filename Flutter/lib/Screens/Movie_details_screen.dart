import 'package:flutter/material.dart';
import 'package:flutter_app/models/movie.dart';
import 'package:flutter_app/widgets/base_widget.dart';
import 'package:get/get.dart';
class MovieDetailsScreen extends StatelessWidget {
  MovieDetailsScreen({Key? key}) : super(key: key);
  Movie M = Get.arguments;
  @override
  Widget build(BuildContext context) {
    return BaseWidget(
      appBar: AppBar(
        centerTitle: true,
        title:  Text(
          '${M.titleType}',
          style: TextStyle(color: Colors.black),
        ),
        elevation: 0,
        backgroundColor: Colors.white,
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          //image in this line
          const SizedBox(
            height: 30,
          ),

        ],
      ),
    );
  }
}
