import 'package:rumahsehat/service/api_service.dart';
import 'package:rumahsehat/service/auth_service.dart';
import 'package:rumahsehat/service/auth_service_impl.dart';
import 'package:rumahsehat/service/user_service.dart';
import 'package:rumahsehat/service/user_service_impl.dart';
import 'package:get_it/get_it.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

final getIt = GetIt.instance;

Future<void> init() async {
  getIt.registerLazySingleton<Client>(() => Client());

  final sharedPref = await SharedPreferences.getInstance();
  getIt.registerLazySingleton(() => sharedPref);

  getIt.registerLazySingleton<IAuthService>(
      () => AuthServiceImpl(httpClient: getIt(), sharedPreferences: getIt()));

  getIt.registerLazySingleton<HttpClient>(
      () => HttpClient(client: getIt(), sharedPreferences: getIt()));

  getIt.registerLazySingleton<IUserService>(
      () => UserServiceImpl(httpClient: getIt(), sharedPreferences: getIt()));
}
