import 'package:get/get.dart';
import 'package:flutter_app/models/movie.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:dio/dio.dart';
import 'package:http/http.dart' as http;
import 'dart:io';
import 'package:flutter/material.dart';
class MovieController extends GetxController {
  RxList<Movie> movieList = <Movie>[].obs;


  @override
  onInit() {
    super.onInit();
  }

  getMovieList(String token) async {
    var dio = Dio();
    var response = await dio.get('http://192.168.137.1:8080/api/login',
    options: Options(
      method: "GET",
      headers:
        {
          HttpHeaders.authorizationHeader: 'Bearer $token',
        },
      responseType: ResponseType.json,
    )
    );

    if(response.statusCode !< 300)
      {
        for( var item in response.data['data'])
          {
            //movieList.add()
          }
      }

  }
}