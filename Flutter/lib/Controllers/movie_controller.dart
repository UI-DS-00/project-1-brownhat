import 'package:get/get.dart';
import 'package:flutter_app/models/movie.dart';
import 'package:shared_preferences/shared_preferences.dart';

class MovieController extends GetxController {
  RxList<Movie> movieList = <Movie>[].obs;

  late SharedPreferences pref;
  String token = '-1';

  Future<void> getToken() async {
    pref = await SharedPreferences.getInstance();
    token = pref.getString('token') ?? '-1';
  }

  @override
  onInit() {
    super.onInit();
  }

  getMovieList() async {
    await getToken();

  }
}
