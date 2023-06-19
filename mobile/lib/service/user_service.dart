import 'package:rumahsehat/model/pasien_model.dart';

abstract class IUserService {
  Future<Pasien?> getUser();
  Future<String> topUp({required String nominal});
  String logOut();
}
