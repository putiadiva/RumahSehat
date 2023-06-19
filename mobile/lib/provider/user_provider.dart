import 'package:flutter/material.dart';
import 'package:rumahsehat/model/pasien_model.dart';
import 'package:rumahsehat/service/user_service.dart';
import 'package:rumahsehat/di/get_it.dart';
import 'package:rumahsehat/service/user_service_impl.dart';

class UserProvider extends ChangeNotifier {
  final userService = getIt<IUserService>();
  bool isLoading = false;
  Pasien? _pasien;
  Pasien? get user => _pasien;

  Future<void> getUser() async {
    isLoading = true;
    notifyListeners();

    final response = await userService.getUser();

    _pasien = response;
    isLoading = false;
    notifyListeners();
  }
}
