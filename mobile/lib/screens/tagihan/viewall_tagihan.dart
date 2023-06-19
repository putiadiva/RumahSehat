import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:rumahsehat/constants.dart';
import 'package:rumahsehat/model/tagihan_model.dart';
import 'package:rumahsehat/widgets/navbar/bottom_navbar.dart';
import 'package:rumahsehat/widgets/navbar/side_bar.dart';
import 'package:rumahsehat/widgets/tagihan/daftar_tagihan_widget.dart';
import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:convert';

import 'package:shared_preferences/shared_preferences.dart';

class DaftarTagihan extends StatefulWidget {
  const DaftarTagihan({Key? key}) : super(key: key);

  @override
  State<DaftarTagihan> createState() => _DaftarTagihanState();
}

class _DaftarTagihanState extends State<DaftarTagihan> {
  late Future<List<TagihanModel>> tagihanList;

  Future<List<TagihanModel>> getListTagihanfromApi() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? jwtToken = prefs.getString('token')!;
    String? username = prefs.getString('username');
    String url = "https://apap-051.cs.ui.ac.id/api/v1/tagihan/list-tagihan/$username";
    var response = await http.get(
      Uri.parse(url),
      headers: {"Authorization": "Bearer $jwtToken"},
    );
    Iterable list = json.decode(response.body);
    return list.map((model) => TagihanModel.fromJson(model)).toList();
  }

  @override
  void initState() {
    super.initState();
    tagihanList = getListTagihanfromApi();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Tagihan"),
          backgroundColor: kPrimaryColor,
        ),
        drawer: SideBar(),
        bottomNavigationBar: const BottomNavbar(index: 2),
        body: FutureBuilder<List<TagihanModel>>(
            future: tagihanList,
            builder: (context, AsyncSnapshot<List<TagihanModel>> listTagihan) {
              switch (listTagihan.connectionState) {
                case ConnectionState.waiting:
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                default:
                  if (listTagihan.hasError) {
                    return Text('Error: ${listTagihan.error}');
                  } else {
                    if (listTagihan.data!.isEmpty) {
                      return Align(
                        alignment: Alignment.center,
                        child: Text(
                          "Anda tidak memiliki Tagihan",
                          style: GoogleFonts.inter(
                            fontSize: 17,
                            fontWeight: FontWeight.w500,
                            color: Colors.black54,
                          ),
                        ),
                      );
                    } else {
                      return Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: ListView.builder(
                            itemCount: listTagihan.data!.isEmpty
                                ? 0
                                : listTagihan.data!.length,
                            itemBuilder: (context, index) {
                              return DaftarTagihanWidget(
                                tagihan: listTagihan.data![index],
                              );
                            }),
                      );
                    }
                  }
              }
            }));
  }
}
