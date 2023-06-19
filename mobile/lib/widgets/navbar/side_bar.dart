import 'package:flutter/material.dart';
import 'package:rumahsehat/appointment/viewAllAppointment.dart';
import 'package:rumahsehat/constants.dart';
import 'package:rumahsehat/screens/home/home.dart';
import 'package:rumahsehat/screens/profile/profile.dart';
import 'package:rumahsehat/screens/tagihan/viewall_tagihan.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../di/get_it.dart';
import '../../screens/welcome/welcome.dart';
import '../../service/user_service.dart';

class SideBar extends StatefulWidget {
  @override
  State<SideBar> createState() => _SideBarState();
}

class _SideBarState extends State<SideBar> {
  late Future<String> username;

  //authservice
  final authService = getIt<IUserService>();

  Future<String> getUsername() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString('username')!;
  }

  @override
  void initState() {
    super.initState();
    username = getUsername();
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<String>(
        future: username,
        builder: (context, AsyncSnapshot<String> username) {
          switch (username.connectionState) {
            case ConnectionState.waiting:
              return const Center(
                child: CircularProgressIndicator(),
              );
            default:
              if (username.hasError) {
                return Text('Error: ${username.error}');
              } else {
                return Drawer(
                  child: ListView(
                    // Remove padding
                    padding: EdgeInsets.zero,
                    children: [
                      UserAccountsDrawerHeader(
                        accountName: Text(username.data!,
                            style: TextStyle(fontWeight: FontWeight.w800)),
                        accountEmail: Text('Pasien'),
                        currentAccountPicture: const CircleAvatar(
                          backgroundColor: Colors.white,
                          radius: 30,
                          child: Icon(
                            Icons.person,
                            color: Color(0xffCCCCCC),
                          ),
                        ),
                        decoration: const BoxDecoration(
                          color: kPrimaryColor,
                        ),
                      ),
                      ListTile(
                        leading: Icon(Icons.person),
                        title: Text('Profil'),
                        onTap: () => {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => const ProfilePage())),
                        },
                      ),
                      Divider(),
                      ListTile(
                        leading: Icon(Icons.home),
                        title: Text('Home'),
                        onTap: () => {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => const Home())),
                        },
                      ),
                      ListTile(
                        leading: Icon(Icons.calendar_today),
                        title: Text('Appointment'),
                        onTap: () => {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) =>
                                      const ViewAllAppointment())),
                        },
                      ),
                      ListTile(
                        leading: Icon(Icons.payment),
                        title: Text('Tagihan'),
                        onTap: () => {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => const DaftarTagihan())),
                        },
                      ),
                      Divider(),
                      ListTile(
                        title: Text('Log out'),
                        leading: Icon(Icons.exit_to_app),
                        onTap: () => {
                          authService.logOut(),
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => Welcome())),
                        },
                      ),
                    ],
                  ),
                );
              }
          }
        });
  }
}
