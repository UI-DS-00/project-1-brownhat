import 'package:flutter/material.dart';
import 'package:flutter_app/Controllers/movie_controller.dart';
import 'package:get/get.dart';

class MovieScreen extends GetView<MovieController> {
  const MovieScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text(
          'Movies',
          style: TextStyle(),
        ),
        elevation: 0,
        backgroundColor: Colors.white,
      ),
      body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20),
          child: Obx(
                  () {
                return ListView.separated(itemBuilder: (context, index) {
                  return Container();
                },
                    separatorBuilder: (context, index) => const Divider(),
                    itemCount: controller.movieList.length);
              }


          )
      ),
    );
  }
}



