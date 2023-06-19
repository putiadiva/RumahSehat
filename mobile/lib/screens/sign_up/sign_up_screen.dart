import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:rumahsehat/constants.dart';
import 'package:rumahsehat/login.dart';
import 'package:rumahsehat/service/auth_service.dart';
import 'package:rumahsehat/di/get_it.dart';
import 'package:rumahsehat/service/pasien_service.dart';
import 'package:rumahsehat/service/pasien_service.dart';
// import 'package:rumahsehat/screens/sign_in/sign_in_screen.dart';
import 'package:rumahsehat/widgets/custom_text_button.dart';
import 'package:rumahsehat/widgets/form_title_and_field.dart';
import 'package:rumahsehat/widgets/form_title_and_field_int.dart';
import 'package:rumahsehat/widgets/header_widget.dart';

class SignUpScreen extends StatefulWidget {
  const SignUpScreen({Key? key}) : super(key: key);

  @override
  _SignUpScreenState createState() => _SignUpScreenState();
}

class _SignUpScreenState extends State<SignUpScreen> {
  String? username;
  String? email;
  String? password;
  String? passwordConfirmation;
  String? nama;
  int? umur;

  TextEditingController usernameController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController passwordConfirmationController =
      TextEditingController();
  TextEditingController namaController = TextEditingController();
  TextEditingController umurController = TextEditingController();
  GlobalKey<FormState> formKey = GlobalKey<FormState>();

  final authService = getIt<IAuthService>();

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;

    return Scaffold(
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
                    height: 100,
                    child: HeaderWidget(100, false, Icons.house_rounded),
                  ),
                  Container(
                    width: 600,
                    padding: const EdgeInsets.symmetric(
                        horizontal: 40, vertical: 50),
                    child: Column(
                      children: [
                        Text(
                          "Sign Up",
                          style: largestText,
                        ),
                        const SizedBox(height: 50),
                        FormTitleAndField(
                          title: "Nama",
                          textEditingController: namaController,
                          onChanged: (value) {
                            setState(() {
                              nama = value;
                            });
                          },
                          onSaved: (value) {
                            setState(() {
                              nama = value;
                            });
                          },
                          validate: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter your name';
                            }
                            return null;
                          },
                        ),
                        FormTitleAndField(
                          title: "Username",
                          textEditingController: usernameController,
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
                          validate: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter your username';
                            }
                            return null;
                          },
                        ),
                        FormTitleAndField(
                          title: "Email",
                          textEditingController: emailController,
                          onChanged: (value) {
                            setState(() {
                              email = value;
                            });
                          },
                          onSaved: (value) {
                            setState(() {
                              email = value;
                            });
                          },
                          validate: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter your email';
                            }
                            return null;
                          },
                        ),
                        FormTitleAndField(
                          title: "Password",
                          textEditingController: passwordController,
                          obscure: true,
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
                          validate: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter your password';
                            } else if (!isPasswordValid(value.toString())) {
                              return 'The password must contain numbers, uppercase, lowercase letters, symbols, \nand must be at least 8 characters long ';
                            }
                            return null;
                          },
                        ),
                        FormTitleAndField(
                          title: "Password Confirmation",
                          textEditingController: passwordConfirmationController,
                          obscure: true,
                          onChanged: (value) {
                            setState(() {
                              passwordConfirmation = value;
                            });
                          },
                          onSaved: (value) {
                            setState(() {
                              passwordConfirmation = value;
                            });
                          },
                          validate: (value) {
                            if (value == null ||
                                value.isEmpty ||
                                password != value) {
                              return 'Password doesn\'t match';
                            }
                            return null;
                          },
                        ),
                        FormTitleAndFieldInt(
                          title: "Umur",
                          textEditingController: umurController,
                          onChanged: (value) {
                            setState(() {
                              umur = int.parse(value);
                            });
                          },
                          onSaved: (value) {
                            setState(() {
                              umur = int.parse(value.toString());
                            });
                          },
                          validate: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter your age';
                            }
                            return null;
                          },
                        ),
                        const SizedBox(height: 30),
                        CustomTextButton(
                            text: "Sign Up",
                            onTap: () async {
                              if (formKey.currentState!.validate()) {
                                String status = await authService.signUp(
                                    username: username!,
                                    email: email!,
                                    password: password!,
                                    nama: nama!,
                                    umur: umur!);
                                if (status == "Pendaftaran Berhasil") {
                                  showDialog(
                                    context: context,
                                    builder: (ctx) => AlertDialog(
                                      title: const Text("Pendaftaran Berhasil"),
                                      content: const Text("Silahkan Login"),
                                      actions: <Widget>[
                                        TextButton(
                                          onPressed: () {
                                            Navigator.push(context,
                                                MaterialPageRoute(
                                              builder: (context) {
                                                return const Login();
                                              },
                                            ));
                                          },
                                          child: Container(
                                            padding: const EdgeInsets.all(14),
                                            child: const Text("Ok"),
                                          ),
                                        ),
                                      ],
                                    ),
                                  );
                                } else {
                                  showDialog(
                                    context: context,
                                    builder: (ctx) => AlertDialog(
                                      title:
                                          const Text("Username telah terpakai"),
                                      content: const Text(
                                          "Silahkan masukkan username lain"),
                                      actions: <Widget>[
                                        TextButton(
                                          onPressed: () {
                                            Navigator.push(context,
                                                MaterialPageRoute(
                                              builder: (context) {
                                                return const SignUpScreen();
                                              },
                                            ));
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
                              }
                            }),
                        const SizedBox(height: 30),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            const Text("Already have an account ? "),
                            InkWell(
                              onTap: () {
                                Navigator.push(context, MaterialPageRoute(
                                  builder: (context) {
                                    return Login();
                                  },
                                ));
                              },
                              child: Text("Sign In",
                                  style: TextStyle(
                                    color: Colors.blue,
                                  )),
                            ),
                          ],
                        ),
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
