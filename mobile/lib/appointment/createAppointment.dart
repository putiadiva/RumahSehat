import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:rumahsehat/appointment/viewAllAppointment.dart';
import 'package:rumahsehat/widgets/home/home_widget.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:convert';
import 'package:sizer/sizer.dart';
import '../../constants.dart';

import '../screens/home/home.dart';
import '../widgets/navbar/bottom_navbar.dart';

class FormAppointment extends StatefulWidget {
  const FormAppointment({Key? key}) : super(key: key);
  static String routeName = 'HomeScreen';

  @override
  FormAppointmentState createState() {
    return FormAppointmentState();
  }
}
class FormAppointmentState extends State<FormAppointment> {
  GlobalKey<FormState> formKey = GlobalKey<FormState>();
  final String urlGetDokter = "https://apap-051.cs.ui.ac.id/api/v1/appointment/listDokter";
  var valDokter;
  List dataDokter = [];

  DateTime dateTime = DateTime.now();

  Future<String> getDokterData() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? _jwtToken = prefs.getString('token');
    var response = await http.get(Uri.parse(urlGetDokter),
    headers: <String, String>{'Content-Type' : 'application/json;charset=UTF-8',
    'Authorization' : 'Bearer $_jwtToken'}
    );
    var body = json.decode(response.body);
    setState(() {
      dataDokter = body;
    });
    return "Succes";
  }

  Future<bool> createAppointment(String valDokter, DateTime dateTime) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? _jwtToken = prefs.getString('token');
    String? _username = prefs.getString('username');
    final response =  await http.post(
      Uri.parse("https://apap-051.cs.ui.ac.id/api/v1/appointment/add"),
      headers: <String, String>{
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization' : 'Bearer $_jwtToken',
      },
      body: jsonEncode({
        'waktuAwal' : "${dateTime.year.toString()}-${dateTime.month.toString().padLeft(2,'0')}-${dateTime.day.toString().padLeft(2,'0')}T${dateTime.hour.toString().padLeft(2,'0')}:${dateTime.minute.toString().padLeft(2,'0')}",
        'isDone' : false,
        'pasien' : {
          'username' : '$_username'
        },
        'dokter' : {
          'uuid' : valDokter
        },
      }),
    );

    Map<String, dynamic> state = jsonDecode(response.body);
    if (state['hasil'] == 'gagal') {
      showDialog(
          context: context,
          builder: (BuildContext context) {
            return AlertDialog(
              title: Text("Gagal membuat appointment"),
              content: Text("Waktu appointment bertabrakan dengan appointment lainnya"),
            );
          });
      return Future<bool>.value(false);

    } else {
      Navigator.pop(context);
      return Future<bool>.value(true);

    }

    if (response.statusCode == 200) {
      return Future<bool>.value(true);
    }
    else {
      return Future<bool>.value(false);
    }
  }

  Future pilihTanggalWaktu() async {
    DateTime? date = await pilihTanggal();
    if (date == null) return;

    TimeOfDay? time = await pilihWaktu();
    if (time == null) return;

    final dateTime = DateTime(date.year,date.month,date.day,time.hour,
      time.minute);
    setState(() => this.dateTime = dateTime);
  }

  Future<DateTime?> pilihTanggal() => showDatePicker(
    context: context,
    initialDate: dateTime,
    firstDate: DateTime(1922),
    lastDate: DateTime(2023),
  );

  Future<TimeOfDay?> pilihWaktu() => showTimePicker(
      context: context,
      initialTime: TimeOfDay(hour: dateTime.hour, minute: dateTime.minute));

  @override
  void initState() {
    super.initState();
    getDokterData();
  }

  @override
  Widget build(BuildContext context) {
    final hours = dateTime.hour.toString().padLeft(2, '0');
    final minutes = dateTime.minute.toString().padLeft(2, '0');
    var size = MediaQuery.of(context).size; //total height and width of device

    return Scaffold(
      appBar: AppBar(
        title: const Text("Create Appointment"),
        backgroundColor: kPrimaryColor,

        leading: IconButton(icon: const Icon(Icons.arrow_back_ios), onPressed: () {
          Navigator.push(context, MaterialPageRoute(
               builder: (context) => const Home()
            )
          );

        },),
      ),
      body: Container(
        height: size.height*0.50,
        decoration: BoxDecoration(
          color: Colors.white,
        ),
        padding: const EdgeInsets.all(20.0),
        child: Form(
          key: formKey,
          child: Scrollbar(
            isAlwaysShown: true,
            child: ListView(
              padding: const EdgeInsets.only(right: kFloatingActionButtonMargin),
              children: [
                SizedBox( height: 20.0),
                Text(
                  "Tanggal dan Waktu Appointment",
                  style: Theme.of(context).textTheme.subtitle1!.copyWith(
                    color: Colors.black,

                  ),
                ),
                SizedBox( height: 20.0),
                ElevatedButton(
                  onPressed: pilihTanggalWaktu,
                  child: Text('${dateTime.year}/${dateTime.month}/${dateTime.day} $hours:$minutes'),
                ),
                SizedBox( height: 20.0),
                Text(
                  "Nama Dokter",
                  style: Theme.of(context).textTheme.subtitle1!.copyWith(
                    color: Colors.black,
                  ),
                ),
                SizedBox( height: 20.0),
                DropdownButtonFormField<String>(
                  decoration: const InputDecoration(
                    prefixIcon: Icon(Icons.person),
                    labelText: "Nama - Tarif",
                  ),
                  isExpanded: true,
                  value: valDokter,
                  icon: const Icon(
                    Icons.arrow_drop_down,
                    color: Colors.blue, // <-- SEE HERE
                  ),
                  items: dataDokter.map((item) {
                    return DropdownMenuItem(
                      child: Text(item['nama'] + " - Rp." + item['tarif'].toString()),
                      value: item['uuid'].toString(),

                    );
                  }).toList(),
                  onChanged: (val) => setState(() {
                    valDokter = val.toString();
                  }),
                ),
                SizedBox( height: 20.0),
                OutlinedButton(
                  style: OutlinedButton.styleFrom(
                    side: BorderSide(color: Colors.green, width: 1), //<-- SEE HERE
                  ),
                  child: Text(
                    "Submit",
                    style: TextStyle(
                      color: Colors.green,
                    ),
                  ),
                  onPressed: () async {
                    final validated = formKey.currentState!.validate();
                    if (validated){
                      formKey.currentState!.save();
                      bool response = await createAppointment(valDokter, dateTime);
                      if (response == true) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('Pembuatan Appointment Berhasil')),
                        );
                        Navigator.push(context, MaterialPageRoute(
                            builder: (context) => const ViewAllAppointment()
                        )
                        );
                      }
                      else {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('Pembuatan Appointment Gagal')),
                        );
                      }
                      print(response.toString());
                    }
                    else {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text('Pembuatan Appointment Gagal')),
                      );
                    }
                  },
                ),
              ],
            ),
          ),
        ),
      ),
        bottomNavigationBar: const BottomNavbar(index: 0)

    );



}

}
