import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter/material.dart';
class Movie {
  String ?_titleType;
  String ?_primaryTitle;
  String ?_originalTitle;
  bool ?_isAdult;
  int ?_startYear;
  int ?_endYear;
  int ?_runtime;


  Movie(
      this._endYear,
      this._isAdult,
      this._originalTitle,
      this._primaryTitle,
      this._runtime,
      this._startYear,
      this._titleType,
      );
  //Set<FavouriteList> favouriteLists;
}