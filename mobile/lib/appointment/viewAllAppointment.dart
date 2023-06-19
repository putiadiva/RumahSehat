import 'dart:convert';
import 'dart:async';
import 'package:flutter/material.dart';
import 'package:rumahsehat/constants.dart';
import 'package:rumahsehat/widgets/navbar/side_bar.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;
import '../widgets/navbar/bottom_navbar.dart';
import 'AppointmentModel.dart';
import 'package:intl/intl.dart';


class ViewAllAppointment extends StatefulWidget {
  const ViewAllAppointment({
    Key? key,
  }) : super(key: key);
  static String routeName = "ViewAllAppointment";

  @override
  ViewAllAppointmentState createState() => ViewAllAppointmentState();
}

class ViewAllAppointmentState extends State<ViewAllAppointment> {
  final String url = "https://apap-051.cs.ui.ac.id/api/v1/appointment/list-appointment";
  late Future<List<AppointmentModel>> futureAppointment;

  Future<List<AppointmentModel>> getAllAppointmentData() async {

    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? _jwtToken = prefs.getString('token');
    var response = await http.get(Uri.parse(url),
        headers: <String, String>{
          'Content-Type' : 'application/json;charset=UTF-8',
          'Authorization' : 'Bearer $_jwtToken',
        });

    print(response.statusCode);
    if (response.statusCode == 200) {
      List jsonResponse = json.decode(response.body);
      return jsonResponse.map((data) => new AppointmentModel.fromJson(data)).toList();
    }
    else {
      throw Exception("Gagal menampilkan jadwal appointment");
    }
  }

  @override
  void initState() {
    super.initState();
    futureAppointment = getAllAppointmentData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Jadwal Appointment"),
        backgroundColor: kPrimaryColor,
      ),
      drawer: SideBar(),
        bottomNavigationBar: const BottomNavbar(index: 1),
        body: Padding(
        padding : const EdgeInsets.all(1.0),
        child: FutureBuilder<List<AppointmentModel>> (
          future: futureAppointment,
          builder: (context, snapshot) {
            if(snapshot.hasData) {
              List<AppointmentModel>? appointmentList = snapshot.data;
              return ListView.builder(
                  itemCount: appointmentList?.length,
                  itemBuilder: (context, index){
                    return Container(
                      margin: EdgeInsets.only(bottom: 5.0, top: 5.0),
                      child: Column(
                        children: [
                          Container(
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.vertical(
                                  top: Radius.circular(1.0),
                                ),
                                color: kPrimaryColor,
                                boxShadow: [
                                  BoxShadow(
                                    color: Colors.grey,
                                    blurRadius: 2.0,
                                  ),
                                ],
                              ),
                              child: Column(
                                children: [
                                  AppointmentFieldRow(field: "Nama Dokter", value: appointmentList![index].namaDokter),
                                  SizedBox(
                                    height: 16.0,
                                    child : Divider(
                                      thickness: 1.0,
                                    ),
                                  ),
                                  AppointmentFieldRow(field: "Waktu Appointment", value: getTanggal(appointmentList[index].waktuAwal)),
                                  SizedBox(
                                    height : 16.0,
                                    child : Divider(
                                    thickness: 1.0,
                                  ),
                                  ),
                                  AppointmentFieldRow(field: 'Status', value: getStatus(appointmentList[index].is_done)),
                                  SizedBox(
                                      height : 16.0
                                  )
                                ],
                              )
                          )
                        ]),
                    );
                  }
                  );
            } else if (snapshot.hasError) {
              return Text("${snapshot.error}");
            }
            return CircularProgressIndicator();
          },
        ),
      ),

    );
  }

}

String getStatus(bool isDone) {
  if (isDone) {
    return "Selesai";
  } else {
    return "Belum Selesai";
  }
}

String getTanggal(String waktuAwal) {
  DateTime tanggal = new DateFormat("yyyy-MM-ddThh:mm:ss").parse(waktuAwal);
  String convertedTanggal = "${tanggal.year.toString()}-${tanggal.month.toString().padLeft(2,'0')}-${tanggal.day.toString().padLeft(2,'0')} ${tanggal.hour.toString().padLeft(2,'0')}:${tanggal.minute.toString().padLeft(2,'0')}";
  return convertedTanggal;
}

class AppointmentFieldRow extends StatelessWidget {
  const AppointmentFieldRow({Key? key, required this.field, required this.value})
      : super(key: key);
  final String field;
  final String value;
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Text(
          field,
          style: Theme.of(context).textTheme.caption!.copyWith(
            color: Colors.black,
          ),
        ),
        Text(value, style: Theme.of(context).textTheme.subtitle2!.copyWith(
          color: Colors.black,
        )),
      ],
    );
  }
}