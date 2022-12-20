import 'package:flutter/material.dart';
import 'package:flutter_app/Controllers/movie_controller.dart';
import 'package:flutter_app/widgets/base_widget.dart';
import 'package:get/get.dart';

class MovieScreen extends GetView<MovieController> {
  MovieScreen({Key? key}) : super(key: key);
  Rx<Text> t = Text('salam').obs;
  @override
  Widget build(BuildContext context) {
    return BaseWidget(
        appBar: AppBar(
          centerTitle: true,
          title: const Text(
            'Movies',
            style: TextStyle(color: Colors.black),
          ),
          elevation: 0,
          backgroundColor: Colors.white,
        ),
        child: Obx(
                () {
          return ListView.separated(
            itemCount: controller.movieList.length + 20,
            separatorBuilder: (context, index) => const Divider(),
            itemBuilder: (context, index) {
              return ListTile(
                leading: const Icon(Icons.question_mark),
                title: const Text('temp'),
                onTap: ()
                {
                  Get.snackbar('OK', 'DONE!');
                },
              );
            },
          );
          //controller.movieList.length
        }));
  }
}
