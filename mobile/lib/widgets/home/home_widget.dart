import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:rumahsehat/appointment/createAppointment.dart';
import 'package:rumahsehat/constants.dart';
import 'package:rumahsehat/widgets/navbar/side_bar.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../screens/tagihan/viewall_tagihan.dart';
import 'feature_card.dart';

class HomeWidget extends StatefulWidget {
  const HomeWidget({Key? key}) : super(key: key);

  @override
  State<HomeWidget> createState() => _HomeWidgetState();
}

class _HomeWidgetState extends State<HomeWidget> {

  late Future<String> username;

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
    var size = MediaQuery.of(context).size; //total height and width of device
    return Scaffold(
      drawer: SideBar(),
      body: FutureBuilder<String> (
          future: username,
          builder: (context, AsyncSnapshot<String> username) {
            switch (username.connectionState) {
              case ConnectionState.waiting:
                return const Center(
                  child: CircularProgressIndicator(),
                );
              default: if (username.hasError) {
                return Text('Error: ${username.error}');
              } else {
                return Stack(
                  children: [
                    Container(
                      //height of the container is 45% of our total height
                      height: size.height*0.30,
                      decoration: const BoxDecoration(
                        color: kPrimaryColor,
                      ),
                    ),
                    SafeArea(child:
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 20),
                      child: Column(
                        children: [
                          const SizedBox(height: 50),
                          Align(
                            alignment: Alignment.centerLeft,
                            child: Text(
                              "Halo, ${username.data!}!",
                              style: GoogleFonts.inter(
                                fontSize: 20,
                                fontWeight: FontWeight.w700,
                                color: Colors.white,
                              ),
                            ),
                          ),
                          Align(
                            alignment: Alignment.centerLeft,
                            child: Text(
                              "Selamat datang di Rumah Sehat",
                              style: GoogleFonts.inter(
                                fontSize: 18,
                                fontWeight: FontWeight.w500,
                                color: Colors.white,
                              ),
                            ),
                          ),
                          const SizedBox(height: 40),
                          Expanded(
                              child: GridView.count(
                                crossAxisCount: 2,
                                childAspectRatio: 0.85,
                                crossAxisSpacing: 20,
                                mainAxisSpacing: 20,
                                children: [
                                  FeatureCard(
                                    title: "Appointment",
                                    svgSrc: "lib/assets/images/appointment.png",
                                    press: () => {
                                      Navigator.push(context, MaterialPageRoute(
                                          builder: (context) => FormAppointment()))
                                    },
                                  ),
                                  FeatureCard(
                                    title: "Tagihan",
                                    svgSrc: "lib/assets/images/tagihan.png",
                                    press: () => {
                                      Navigator.push(context, MaterialPageRoute(
                                          builder: (context) => DaftarTagihan()))
                                    },
                                  ),
                                ],
                              )
                          )
                        ],
                      ),
                    )
                    )
                  ],
                );
              }
            }
          })
    );
  }
}