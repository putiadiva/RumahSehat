import 'package:flutter/material.dart';
import 'package:rumahsehat/main.dart';
import 'package:rumahsehat/screens/home/home.dart';
import 'package:rumahsehat/screens/sign_up/sign_up_screen.dart';
import 'package:rumahsehat/di/get_it.dart';
import 'package:rumahsehat/service/auth_service.dart';
import 'package:rumahsehat/widgets/custom_text_button.dart';
import 'package:rumahsehat/model/user_model.dart';

/*
UI: https://youtu.be/aJdIkRipgSk
Navigation: https://youtu.be/yuGaEsLRB38
 */
class Login extends StatefulWidget {
  const Login({Key? key}) : super(key: key);

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {
  String? username;
  String? password;

  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  GlobalKey<FormState> formKey = GlobalKey<FormState>();

  final authService = getIt<IAuthService>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[300],
      body: Form(
          key: formKey,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Text(
                  "Hello!",
                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 24),
                ),
                const SizedBox(
                  height: 10,
                ),
                const Text(
                  "Welcome Back.",
                  style: TextStyle(fontSize: 20),
                ),
                const TextField(
                  decoration: InputDecoration(border: InputBorder.none),
                ),
                const SizedBox(
                  height: 20,
                ),

                // username
                Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 25.0),
                    child: Container(
                      decoration: BoxDecoration(
                        color: Colors.grey[200],
                        border: Border.all(color: Colors.white),
                        borderRadius: BorderRadius.circular(12),
                      ),
                      child: Padding(
                        padding: const EdgeInsets.only(left: 20.0),
                        child: TextFormField(
                          decoration: const InputDecoration(
                              border: InputBorder.none, hintText: "Username"),
                          controller: usernameController,
                          onChanged: (value) {
                            setState(() {
                              username = value;
                            });
                          },
                          onSaved: (value) {
                            setState(() {
                              username = value;
                            });
                          },
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter your username';
                            }
                            return null;
                          },
                        ),
                      ),
                    )),
                const SizedBox(
                  height: 10,
                ),

                // password
                Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 25.0),
                    child: Container(
                      decoration: BoxDecoration(
                        color: Colors.grey[200],
                        border: Border.all(color: Colors.white),
                        borderRadius: BorderRadius.circular(12),
                      ),
                      child: Padding(
                        padding: const EdgeInsets.only(left: 20.0),
                        child: TextFormField(
                          obscureText: true,
                          decoration: const InputDecoration(
                              border: InputBorder.none, hintText: "Password"),
                          controller: passwordController,
                          onChanged: (value) {
                            setState(() {
                              password = value;
                            });
                          },
                          onSaved: (value) {
                            setState(() {
                              password = value;
                            });
                          },
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter your password';
                            }
                            return null;
                          },
                        ),
                      ),
                    )),
                const SizedBox(
                  height: 10,
                ),

                // log in button

                CustomTextButton(
                    text: "Sign In",
                    onTap: () async {
                      if (formKey.currentState!.validate()) {
                        var resp = await authService.signIn(
                          username: username!,
                          password: password!,
                        );
                        if (resp != null)
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => const Home()),
                          );
                      }
                    }),

                // register
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text(
                      "Have no account? ",
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    TextButton(
                      style: TextButton.styleFrom(
                        textStyle: const TextStyle(fontSize: 20),
                      ),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => const SignUpScreen()),
                        );
                      },
                      child: const Text('Sign Up'),
                    ),
                  ],
                )
              ],
            ),
          )),
    );
  }
}
