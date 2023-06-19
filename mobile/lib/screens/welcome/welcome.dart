import 'package:flutter/material.dart';
import 'package:rumahsehat/login.dart';
import 'package:rumahsehat/screens/sign_up/sign_up_screen.dart';

class Welcome extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(
      body: SingleChildScrollView(
        child: Container(
          height: size.height,
          child: Stack(
            children: [
              Container(
                height: size.height * 0.7,
                decoration: const BoxDecoration(
                  image: DecorationImage(
                    alignment: Alignment(0, 1),
                    fit: BoxFit.cover,
                    image: AssetImage("lib/assets/images/hospital.jpg"),
                  ),
                ),
              ),
              Positioned(
                bottom: 80.0,
                width: size.width,
                child: Container(
                  padding: EdgeInsets.symmetric(horizontal: 24.0),
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      RichText(
                        text: TextSpan(
                          children: [
                            TextSpan(
                              text: "Welcome!",
                              style: TextStyle(
                                height: 1.4,
                                fontSize: 30.0,
                                color: Colors.black,
                              ),
                            ),
                            TextSpan(
                              text: "\nRumah Sakit",
                              style: TextStyle(
                                fontSize: 30.0,
                                fontWeight: FontWeight.w600,
                                color: Colors.black,
                              ),
                            ),
                          ],
                        ),
                      ),
                      SizedBox(
                        height: 20.0,
                      ),
                      Row(
                        children: [
                          ElevatedButton(
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => const SignUpScreen()),
                              );
                            },
                            child: const Text('Sign Up'),
                          ),
                          SizedBox(
                            width: 20.0,
                          ),
                          ElevatedButton(
                              onPressed: () {
                                Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => const Login()),
                                );
                              },
                              child: const Text("Login Page")),
                        ],
                      )
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
