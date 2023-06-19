import 'package:flutter/material.dart';
import 'package:google_nav_bar/google_nav_bar.dart';
import 'package:rumahsehat/appointment/viewAllAppointment.dart';
import 'package:rumahsehat/screens/profile/profile.dart';
import 'package:rumahsehat/screens/tagihan/viewall_tagihan.dart';

import '../../constants.dart';
import '../../screens/home/home.dart';

class BottomNavbar extends StatefulWidget {
  final int index;

  const BottomNavbar({Key? key, required this.index}) : super(key: key);

  @override
  _BottomNavbarState createState() => _BottomNavbarState();
}

class _BottomNavbarState extends State<BottomNavbar> {
  int _selectedIndex = 0;

  void _navigateBottomBar(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  void initState() {
    super.initState();
    _navigateBottomBar(widget.index);
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 20),
      child: GNav(
        selectedIndex: _selectedIndex,
        color: Colors.black54,
        activeColor: Colors.white,
        tabBackgroundColor: kPrimaryColor,
        gap: 8,
        onTabChange: _navigateBottomBar,
        padding: const EdgeInsets.all(16),
        tabs: [
          GButton(
            icon: Icons.home,
            text: "Home",
            onPressed: () => {
              Navigator.push(
                  context, MaterialPageRoute(builder: (context) => Home())),
            },
          ),
          GButton(
              icon: Icons.calendar_today,
              text: "Appointment",
              onPressed: () => {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => ViewAllAppointment())),
                  }),
          GButton(
            icon: Icons.payment,
            text: "Tagihan",
            onPressed: () => {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => DaftarTagihan())),
            },
          ),
          GButton(
            icon: Icons.person,
            text: "Profil",
            onPressed: () => {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => ProfilePage())),
            },
          ),
        ],
      ),
    );
  }
}
