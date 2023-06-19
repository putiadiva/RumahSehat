import 'package:flutter/material.dart';
import 'package:rumahsehat/widgets/navbar/bottom_navbar.dart';
import 'package:rumahsehat/widgets/home/home_widget.dart';
import 'package:rumahsehat/widgets/navbar/side_bar.dart';

import '../../constants.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Home"),
        backgroundColor: kPrimaryColor,
      ),
      drawer: SideBar(),
      // body: HomeWidget(),
      body: const HomeWidget(),
      bottomNavigationBar: const BottomNavbar(index: 0)
    );
  }
}