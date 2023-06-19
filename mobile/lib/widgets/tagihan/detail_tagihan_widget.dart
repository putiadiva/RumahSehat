import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:intl/intl.dart';
import 'package:rumahsehat/constants.dart';
import 'package:awesome_dialog/awesome_dialog.dart';
import 'package:rumahsehat/model/pasien_model.dart';
import 'package:rumahsehat/screens/tagihan/viewall_tagihan.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../../appointment/AppointmentModel.dart';
import '../../model/tagihan_model.dart';
import 'package:http/http.dart' as http;

class DetailTagihanWidget extends StatefulWidget {

  TagihanModel tagihan;
  AppointmentModel appointment;

  DetailTagihanWidget({Key? key, required this.tagihan, required this.appointment})
      : super(key: key);

  @override
  State<DetailTagihanWidget> createState() => _DetailTagihanWidgetState();
}

class _DetailTagihanWidgetState extends State<DetailTagihanWidget> {

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      body: Container(
        margin: EdgeInsets.only(top: 8),
        child: ListView(
          physics: ClampingScrollPhysics(),
          children: <Widget>[
            //Card section
            const SizedBox(
              height: 25,
            ),

            // kode tagihan & jumlah tagihan
            Container(
              margin: const EdgeInsets.only(right: 30, left: 30),
              height: 150,
              width: 200,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(28),
                color: kPrimaryColor,
                boxShadow: const [
                  BoxShadow(
                    color: Colors.black26,
                    blurRadius: 10,
                    spreadRadius: 3,
                    offset: Offset(8.0,8.0),
                  ),
                ],
              ),
              child: Stack(
                 children: [
                   Positioned(
                       left: 29,
                       top: 30,
                     child: Text('Kode Tagihan', style: GoogleFonts.inter(
                       fontSize: 12,
                       fontWeight: FontWeight.w400,
                       color: Colors.white,
                     )),
                   ),
                   Positioned(
                     left: 29,
                     top: 50,
                     child: Text(widget.tagihan.kode, style: GoogleFonts.inter(
                       fontSize: 14,
                       fontWeight: FontWeight.w600,
                       color: Colors.white,
                     )),
                   ),
                   Positioned(
                     right: 29,
                     bottom: 65,
                     child: Text('Jumlah Tagihan', style: GoogleFonts.inter(
                       fontSize: 14,
                       fontWeight: FontWeight.w400,
                       color: Colors.white,
                     )),
                   ),
                   Positioned(
                     right: 29,
                     bottom: 35,
                     child: Text("${widget.tagihan.jumlahTagihan}", style: GoogleFonts.inter(
                       fontSize: 20,
                       fontWeight: FontWeight.w600,
                       color: Colors.white,
                     )),
                   ),
                 ],
              ),
            ),

            const SizedBox(height: 20),

            // detail tagihan
            Container(
              margin: EdgeInsets.only(right: 30, left: 30),
              height: 150,
              width: 200,

              child: Stack(
                children: [
                  Positioned(
                    left: 29,
                    top: 22,
                    child: Text('Tanggal terbuat', style: GoogleFonts.inter(
                      fontSize: 12,
                      fontWeight: FontWeight.w600,
                      color: kPrimaryColor,
                    )),
                  ),
                  Positioned(
                    left: 29,
                    top: 42,
                    child: Text(
                        DateFormat.yMMMMd('en_US').format(widget.tagihan.tanggalTerbuat),
                        style: GoogleFonts.inter(
                          fontSize: 12,
                          fontWeight: FontWeight.w400,
                      )
                    ),
                  ),
                  Positioned(
                    right: 29,
                    top: 22,
                    child: Text('Tanggal dibayar', style: GoogleFonts.inter(
                      fontSize: 12,
                      fontWeight: FontWeight.w600,
                      color: kPrimaryColor,
                    )),
                  ),
                  Positioned(
                    right: 29,
                    top: 42,
                    child: Text(
                        widget.tagihan.isPaid
                            ? DateFormat.yMMMMd('en_US').format(widget.tagihan.tanggalBayar as DateTime)
                            : '-',
                        style: GoogleFonts.inter(
                        fontSize: 12,
                        fontWeight: FontWeight.w400,
                    )),
                  ),
                  Positioned(
                    left: 29,
                    bottom: 42,
                    child: Text('Kode Appointment', style: GoogleFonts.inter(
                      fontSize: 12,
                      fontWeight: FontWeight.w600,
                      color: kPrimaryColor,
                    )),
                  ),
                  Positioned(
                    left: 29,
                    bottom: 22,
                    child: Text(widget.appointment.kode, style: GoogleFonts.inter(
                      fontSize: 12,
                      fontWeight: FontWeight.w400,
                      // color: Colors.white,
                    )),
                  ),
                  Positioned(
                    right: 29,
                    bottom: 42,
                    child: Text('Status Tagihan', style: GoogleFonts.inter(
                      fontSize: 12,
                      fontWeight: FontWeight.w600,
                      color: kPrimaryColor,
                    )),
                  ),
                  Positioned(
                    right: 29,
                    bottom: 22,
                    child: Text(
                        widget.tagihan.isPaid? "Sudah dibayar" : "Belum dibayar",
                        style: GoogleFonts.inter(
                          fontSize: 13,
                          fontWeight: FontWeight.w700,
                          color: widget.tagihan.isPaid? Colors.green : Colors.deepOrange,
                        )
                    ),
                  ),
                ],
              ),
            ),

            // dialog konfirmasi
            Builder(
                builder: (context) {
                  if (!widget.tagihan.isPaid) {
                    return Container(
                    margin: const EdgeInsets.only(left: 40, right: 40, top: 20),
                    width: 50,
                    height: 45,
                    child: TextButton(
                      style: TextButton.styleFrom(
                        backgroundColor: kPrimaryColor,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20),
                        ),
                      ),
                      onPressed: () => {
                        AwesomeDialog(
                          context: context,
                          animType: AnimType.scale,
                          dialogType: DialogType.warning,
                          title: 'Konfirmasi Tagihan',
                          desc:   'Apakah kamu yakin ingin membayar tagihan?',
                          btnOkText: "Yes",
                          btnCancelColor: Colors.deepOrange,
                          btnOkColor: kPrimaryColor,
                          btnCancelOnPress: (){},
                          btnOkOnPress: () async {

                            SharedPreferences prefs = await SharedPreferences.getInstance();
                            String? jwtToken = prefs.getString('token')!;
                            String? username = prefs.getString('username');
                            String url = "https://apap-051.cs.ui.ac.id/api/v1/pasien/$username";
                            var response = await http.get(
                              Uri.parse(url),
                              headers: {"Authorization": "Bearer $jwtToken"},
                            );
                            Map<String,dynamic> pasienMap = json.decode(response.body);
                            var pasien = Pasien.fromJson(pasienMap);

                            if(widget.tagihan.jumlahTagihan <= pasien.saldo){

                              final response = await http.put(
                                Uri.parse('https://apap-051.cs.ui.ac.id/api/v1/tagihan/${widget.tagihan.kode}'),
                                headers: <String, String>{
                                  "Content-Type": "application/json",
                                  "Authorization": "Bearer $jwtToken",
                                },
                                body: jsonEncode(<String, String>{
                                  'kode': widget.tagihan.kode,
                                }),
                              );

                              if (response.statusCode == 200) {
                                Map<String,dynamic> tagihanMap = json.decode(response.body);
                                widget.tagihan = TagihanModel.fromJson(tagihanMap);
                                AwesomeDialog(
                                  context: context,
                                  animType: AnimType.scale,
                                  dialogType: DialogType.success,
                                  btnOkColor: kPrimaryColor,
                                  title: 'Berhasil!',
                                  desc:   'Tagihan Anda berhasil dibayarkan',
                                  btnOkOnPress: () {
                                    Navigator.push(context, MaterialPageRoute(
                                        builder: (context) => DaftarTagihan()));
                                  },
                                ).show();
                              } else {
                                throw Exception('Failed to update tagihan.');
                              }
                            } else {
                              AwesomeDialog(
                                context: context,
                                animType: AnimType.scale,
                                dialogType: DialogType.error,
                                btnOkColor: Colors.deepOrange,
                                title: 'Gagal!',
                                desc: 'Maaf, saldo Anda tidak cukup',
                                btnOkOnPress: () {},
                              ).show();
                            }
                          },
                        )..show()
                      },
                      child: const Text(
                        "Bayar Tagihan",
                        style: TextStyle(
                          fontWeight: FontWeight.w700,
                          color: Color(0xffffffff),
                        ),
                      ),
                    ),
                  );
                  } else {
                    return Container();
                  }
                }
            )
          ]
        )
      ),
    );
  }
}