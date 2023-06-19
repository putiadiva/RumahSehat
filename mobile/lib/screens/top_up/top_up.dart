import 'package:flutter/material.dart';
import 'package:rumahsehat/constants.dart';
import 'package:rumahsehat/main.dart';
import 'package:rumahsehat/screens/home/home.dart';
import 'package:rumahsehat/screens/profile/profile.dart';
import 'package:rumahsehat/service/auth_service.dart';
import 'package:rumahsehat/di/get_it.dart';
import 'package:rumahsehat/service/pasien_service.dart';
import 'package:rumahsehat/service/user_service.dart';
// import 'package:rumahsehat/screens/sign_in/sign_in_screen.dart';
import 'package:rumahsehat/widgets/custom_text_button.dart';
import 'package:rumahsehat/widgets/form_title_and_field.dart';
import 'package:rumahsehat/widgets/form_title_and_field_int.dart';
import 'package:rumahsehat/widgets/header_widget.dart';
import 'package:rumahsehat/widgets/navbar/bottom_navbar.dart';
import 'package:rumahsehat/widgets/navbar/side_bar.dart';

class TopUpScreen extends StatefulWidget {
  const TopUpScreen({Key? key}) : super(key: key);

  @override
  _TopUpScreenState createState() => _TopUpScreenState();
}

class _TopUpScreenState extends State<TopUpScreen> {
  int? topUp;

  TextEditingController topUpController = TextEditingController();
  GlobalKey<FormState> formKey = GlobalKey<FormState>();

  final userService = getIt<IUserService>();

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    final double _drawerIconSize = 24;
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: const Text(
          "Top Up",
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
      body: Form(
        key: formKey,
        child: GestureDetector(
          onTap: () {
            FocusScopeNode currentFocus = FocusScope.of(context);
            if (!currentFocus.hasPrimaryFocus) {
              currentFocus.unfocus();
            }
          },
          child: Container(
            color: Colors.white,
            width: size.width,
            height: size.height,
            alignment: Alignment.center,
            child: SingleChildScrollView(
              child: Column(
                children: [
                  const SizedBox(
                    child: HeaderWidget(100, false, Icons.house_rounded),
                  ),
                  Container(
                    width: 600,
                    child: Column(
                      children: [
                        Text(
                          "Top Up",
                          style: largestText,
                        ),
                        const SizedBox(height: 50),
                        FormTitleAndFieldInt(
                          title: "Jumlah",
                          textEditingController: topUpController,
                          onChanged: (value) {
                            setState(() {
                              topUp = int.parse(value);
                            });
                          },
                          onSaved: (value) {
                            setState(() {
                              topUp = int.parse(value.toString());
                            });
                          },
                          validate: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter the amount';
                            } else if (int.parse(value.toString()) <= 0) {
                              return 'Please enter valid amount';
                            }
                            return null;
                          },
                        ),
                        const SizedBox(height: 30),
                        CustomTextButton(
                            text: "TopUp",
                            onTap: () async {
                              if (formKey.currentState!.validate()) {
                                await userService.topUp(
                                    nominal: topUp!.toString());
                                showDialog(
                                  context: context,
                                  builder: (ctx) => AlertDialog(
                                    title: const Text("Top Up berhasil"),
                                    content: const Text(
                                        "Anda telah berhasil melakukan Top Up"),
                                    actions: <Widget>[
                                      TextButton(
                                        onPressed: () {
                                          Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                                builder: (context) =>
                                                    const ProfilePage()),
                                          );
                                        },
                                        child: Container(
                                          padding: const EdgeInsets.all(14),
                                          child: const Text("Ok"),
                                        ),
                                      ),
                                    ],
                                  ),
                                );
                              }
                            }),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
