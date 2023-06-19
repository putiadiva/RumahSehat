import 'dart:convert';

import 'package:rumahsehat/model/user_model.dart';
import 'package:rumahsehat/service/api_service.dart';
import 'package:rumahsehat/service/auth_service.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AuthServiceImpl implements IAuthService {
  final SharedPreferences sharedPreferences;
  final HttpClient httpClient;

  AuthServiceImpl({
    required this.httpClient,
    required this.sharedPreferences,
  });

  @override
  Future<String> signIn({
    required String username,
    required String password,
  }) async {
    String body = jsonEncode({
      "username": username,
      "password": password,
    });
    Response response = await httpClient.post("/api/v1/authenticate", body);
    if (response.statusCode == 200) {
      var jsonDecoded = jsonDecode(response.body);
      sharedPreferences.setString("token", jsonDecoded['token']);
      sharedPreferences.setString("username", username);
      return "Login Berhasil";
    } else {
      throw Exception("Error");
    }
  }

  @override
  Future<String> signUp(
      {required String nama,
      required String username,
      required String email,
      required String password,
      required int umur}) async {
    String body = jsonEncode({
      "nama": nama,
      "username": username,
      "email": email,
      "password": password,
      "umur": umur,
      "isSso": false,
      "saldo": 0,
    });
    print(body);
    Response response = await httpClient.post("/api/v1/pasien/add", body);
    if (response.statusCode == 200) {
      return "Pendaftaran Berhasil";
    } else {
      return "Pendaftaran gagal";
    }
  }

  //logout
}
