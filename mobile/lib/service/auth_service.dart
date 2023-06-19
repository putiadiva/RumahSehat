import 'package:rumahsehat/model/user_model.dart';

abstract class IAuthService {
  Future<String> signIn({
    required String username,
    required String password,
  });
  Future<String> signUp(
      {required String nama,
        required String username,
        required String email,
        required String password,
        required int umur});
}
