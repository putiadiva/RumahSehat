import 'dart:html';

import 'package:rumahsehat/model/pasien_model.dart';
import 'dart:convert';
import 'package:http/http.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:rumahsehat/service/api_service.dart';
import 'package:rumahsehat/service/user_service.dart';
import 'package:shared_preferences/shared_preferences.dart';

class UserServiceImpl implements IUserService {
  final SharedPreferences sharedPreferences;
  final HttpClient httpClient;

  UserServiceImpl({
    required this.httpClient,
    required this.sharedPreferences,
  });

  @override
  Future<Pasien?> getUser() async {
    Response response = await httpClient.get("/api/v1/pasien");
    if (response.statusCode == 200) {
      var jsonDecoded = jsonDecode(response.body);
      Pasien pasien = Pasien.fromJson(jsonDecoded);
      return pasien;
    } else {
      throw Exception("Error");
    }
  }

  @override
  Future<String> topUp({required String nominal}) async {
    String body = jsonEncode({
      "nominal": nominal,
    });
    Response response = await httpClient.post("/api/v1/pasien/topUp", body);
    if (response.statusCode == 200) {
      return "Top Up Berhasil";
    } else {
      throw Exception("Error");
    }
  }

  @override
  String logOut() {
    sharedPreferences.clear();
    return "Logout anda berhasil";
  }
}
