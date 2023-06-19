import 'package:flutter/material.dart';
import 'package:rumahsehat/main.dart';
import 'package:rumahsehat/model/pasien_model.dart';
import 'package:rumahsehat/provider/user_provider.dart';
import 'package:rumahsehat/screens/home/home.dart';
import 'package:rumahsehat/screens/top_up/top_up.dart';
import 'package:provider/provider.dart';
import 'package:rumahsehat/widgets/header_widget.dart';
import 'package:rumahsehat/widgets/navbar/bottom_navbar.dart';
import 'package:rumahsehat/widgets/navbar/side_bar.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({super.key});
  @override
  State<StatefulWidget> createState() {
    return _ProfilePageState();
  }
}

class _ProfilePageState extends State<ProfilePage> {
  final double _drawerIconSize = 24;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((timeStamp) {
      Provider.of<UserProvider>(context, listen: false).getUser();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          title: const Text(
            "Profile Page",
            style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
          ),
          elevation: 0.5,
          iconTheme: const IconThemeData(color: Colors.white),
          flexibleSpace: Container(
            decoration: BoxDecoration(
                gradient: LinearGradient(
                    begin: Alignment.topLeft,
                    end: Alignment.bottomRight,
                    colors: <Color>[
                  Theme.of(context).primaryColor,
                  Theme.of(context).colorScheme.secondary,
                ])),
          ),
        ),
        drawer: SideBar(),
        bottomNavigationBar: const BottomNavbar(index: 3),
        body: Consumer<UserProvider>(builder: (context, value, child) {
          if (value.isLoading) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }
          final user = value.user;
          return Padding(
            padding: const EdgeInsets.all(10.0),
            child: userBody(user),
          );
        }));
  }

  Widget userBody(Pasien? user) {
    return SingleChildScrollView(
      child: Stack(
        children: [
          const SizedBox(
            height: 100,
            child: HeaderWidget(100, false, Icons.house_rounded),
          ),
          Container(
            alignment: Alignment.center,
            margin: const EdgeInsets.fromLTRB(25, 10, 25, 10),
            padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
            child: Column(
              children: [
                Container(
                  padding: const EdgeInsets.all(10),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(100),
                    border: Border.all(width: 5, color: Colors.white),
                    color: Colors.white,
                    boxShadow: const [
                      BoxShadow(
                        color: Colors.black12,
                        blurRadius: 100,
                        offset: Offset(5, 5),
                      ),
                    ],
                  ),
                  child: Icon(
                    Icons.person,
                    size: 80,
                    color: Colors.grey.shade300,
                  ),
                ),
                const SizedBox(
                  height: 20,
                ),
                Text(
                  user!.nama,
                  style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
                ),
                const SizedBox(
                  height: 20,
                ),
                const SizedBox(
                  height: 10,
                ),
                Container(
                  padding: const EdgeInsets.all(10),
                  child: Column(
                    children: <Widget>[
                      Card(
                        child: Container(
                          alignment: Alignment.topLeft,
                          padding: const EdgeInsets.all(15),
                          child: Column(
                            children: <Widget>[
                              Column(
                                children: <Widget>[
                                  ...ListTile.divideTiles(
                                    color: Colors.grey,
                                    tiles: [
                                      ListTile(
                                        contentPadding:
                                            const EdgeInsets.symmetric(
                                                horizontal: 12, vertical: 4),
                                        leading: const Text("Username: "),
                                        title: Text(user.username),
                                      ),
                                      ListTile(
                                        leading: const Text("Email:        "),
                                        title: Text(user.email),
                                      ),
                                      ListTile(
                                        leading: const Text("Saldo:        "),
                                        title: Text(user.saldo.toString()),
                                      ),
                                    ],
                                  ),
                                  ElevatedButton(
                                      onPressed: () {
                                        Navigator.push(
                                          context,
                                          MaterialPageRoute(
                                              builder: (context) =>
                                                  const TopUpScreen()),
                                        );
                                      },
                                      style: ElevatedButton.styleFrom(
                                          fixedSize: const Size(120, 60)),
                                      child: Row(
                                        children: const <Widget>[
                                          Expanded(
                                            child: Icon(
                                                Icons.account_balance_wallet),
                                          ),
                                          Expanded(
                                            child: Text('Top Up',
                                                textAlign: TextAlign.center),
                                          ),
                                        ],
                                      )),
                                ],
                              )
                            ],
                          ),
                        ),
                      )
                    ],
                  ),
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
