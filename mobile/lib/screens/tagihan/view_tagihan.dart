import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumahsehat/constants.dart';
import 'package:rumahsehat/model/appointment_model.dart';
import 'package:rumahsehat/model/tagihan_model.dart';
import 'package:rumahsehat/widgets/tagihan/detail_tagihan_widget.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;

import '../../appointment/AppointmentModel.dart';

class DetailTagihan extends StatefulWidget {

  late TagihanModel tagihan;
  DetailTagihan({Key? key, required this.tagihan}) : super(key: key);

  @override
  State<DetailTagihan> createState() => _DetailTagihanState();
}

class _DetailTagihanState extends State<DetailTagihan> {

  late Future<AppointmentModel> appointment;

  Future<AppointmentModel> getAppointmentModel() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? jwtToken = prefs.getString('token')!;
    String url = "https://apap-051.cs.ui.ac.id/api/v1/appointment/tagihan/${widget.tagihan.kode}";
    var response = await http.get(
      Uri.parse(url),
      headers: {"Authorization": "Bearer $jwtToken"},
    );
    Map<String,dynamic> appointmentMap = json.decode(response.body);
    return AppointmentModel.fromJson(appointmentMap);
  }

  @override
  void initState() {
    super.initState();
    appointment = getAppointmentModel();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Detail Tagihan"),
          backgroundColor: kPrimaryColor,
        ),
        body: FutureBuilder<AppointmentModel> (
            future: appointment,
            builder: (context, AsyncSnapshot<AppointmentModel> appointment) {
              switch (appointment.connectionState) {
                case ConnectionState.waiting:
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                default: if (appointment.hasError) {
                  return Text('Error: ${appointment.error}');
                } else {
                  return DetailTagihanWidget(
                      tagihan: widget.tagihan,
                      appointment: appointment.data!);
                }
              }
            })
    );
  }
}