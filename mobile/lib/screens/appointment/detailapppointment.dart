import 'package:flutter/material.dart';
import 'package:flutter/gestures.dart';
import 'dart:ui';
import 'package:google_fonts/google_fonts.dart';
// import 'package:myapp/utils.dart';

class Scene extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    double baseWidth = 360;
    double fem = MediaQuery.of(context).size.width / baseWidth;
    double ffem = fem * 0.97;
    return Container(
      width: double.infinity,
      child: Container(
        // detailapppointmentYgz (1:3)
        width: double.infinity,
        height: 640 * fem,
        decoration: BoxDecoration(
          color: Color(0xff4682b4),
        ),
        child: Stack(
          children: [
            Positioned(
              // frame2zav (1:4)
              left: 0 * fem,
              top: 172 * fem,
              child: Container(
                padding:
                    EdgeInsets.fromLTRB(27 * fem, 33 * fem, 35 * fem, 0 * fem),
                width: 360 * fem,
                height: 544 * fem,
                decoration: BoxDecoration(
                  color: Color(0xffffffff),
                  borderRadius: BorderRadius.circular(29 * fem),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      // informasiappointmentUPc (1:18)
                      margin: EdgeInsets.fromLTRB(
                          0 * fem, 0 * fem, 0 * fem, 11 * fem),
                      child: Text(
                        'Informasi Appointment',
                        // style: SafeGoogleFont (
                        //   'Poppins',
                        //   fontSize: 24.6575641632*ffem,
                        //   fontWeight: FontWeight.w700,
                        //   height: 1.5*ffem/fem,
                        //   color: Color(0xff074070),
                        // ),
                      ),
                    ),
                    Container(
                      // frame3Put (1:5)
                      margin: EdgeInsets.fromLTRB(
                          1 * fem, 0 * fem, 0 * fem, 10 * fem),
                      width: 184 * fem,
                      height: 37 * fem,
                      child: Stack(
                        children: [
                          Positioned(
                            // namadoktertbk (1:6)
                            left: 0 * fem,
                            top: 0 * fem,
                            child: Align(
                              child: SizedBox(
                                width: 62 * fem,
                                height: 15 * fem,
                                child: Text(
                                  'Nama Dokter',
                                  // style: SafeGoogleFont (
                                  //   'Poppins',
                                  //   fontSize: 9.3683023453*ffem,
                                  //   fontWeight: FontWeight.w400,
                                  //   height: 1.5*ffem/fem,
                                  //   color: Color(0xff000000),
                                  // ),
                                ),
                              ),
                            ),
                          ),
                          Positioned(
                            // inicontohnamadokterTgn (1:7)
                            left: 0 * fem,
                            top: 12 * fem,
                            child: Align(
                              child: SizedBox(
                                width: 184 * fem,
                                height: 25 * fem,
                                child: Text(
                                  'Inicontohnamadokter',
                                  // style: SafeGoogleFont (
                                  //   'Poppins',
                                  //   fontSize: 16.1272468567*ffem,
                                  //   fontWeight: FontWeight.w700,
                                  //   height: 1.5*ffem/fem,
                                  //   color: Color(0xff6088c4),
                                  // ),
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    Container(
                      // frame4Wux (1:8)
                      margin: EdgeInsets.fromLTRB(
                          1 * fem, 0 * fem, 0 * fem, 10 * fem),
                      width: 186 * fem,
                      height: 37 * fem,
                      child: Stack(
                        children: [
                          Positioned(
                            // namapasienR1L (1:9)
                            left: 0 * fem,
                            top: 0 * fem,
                            child: Align(
                              child: SizedBox(
                                width: 63 * fem,
                                height: 15 * fem,
                                child: Text(
                                  'Nama Pasien',
                                  // style: SafeGoogleFont (
                                  //   'Poppins',
                                  //   fontSize: 9.3683023453*ffem,
                                  //   fontWeight: FontWeight.w400,
                                  //   height: 1.5*ffem/fem,
                                  //   color: Color(0xff000000),
                                  // ),
                                ),
                              ),
                            ),
                          ),
                          Positioned(
                            // inicontohnamapasienEjU (1:10)
                            left: 0 * fem,
                            top: 12 * fem,
                            child: Align(
                              child: SizedBox(
                                width: 186 * fem,
                                height: 25 * fem,
                                child: Text(
                                  'Inicontohnamapasien',
                                  // style: SafeGoogleFont (
                                  //   'Poppins',
                                  //   fontSize: 16.1272468567*ffem,
                                  //   fontWeight: FontWeight.w700,
                                  //   height: 1.5*ffem/fem,
                                  //   color: Color(0xff6088c4),
                                  // ),
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    Container(
                      // frame5g5g (1:11)
                      margin: EdgeInsets.fromLTRB(
                          0 * fem, 0 * fem, 0 * fem, 10 * fem),
                      width: 152 * fem,
                      height: 37 * fem,
                      child: Stack(
                        children: [
                          Positioned(
                            // statusnPc (1:12)
                            left: 0 * fem,
                            top: 0 * fem,
                            child: Align(
                              child: SizedBox(
                                width: 30 * fem,
                                height: 15 * fem,
                                child: Text(
                                  'Status',
                                  // style: SafeGoogleFont (
                                  //   'Poppins',
                                  //   fontSize: 9.3683023453*ffem,
                                  //   fontWeight: FontWeight.w400,
                                  //   height: 1.5*ffem/fem,
                                  //   color: Color(0xff000000),
                                  // ),
                                ),
                              ),
                            ),
                          ),
                          Positioned(
                            // selesaiataubelumSUA (1:13)
                            left: 0 * fem,
                            top: 12 * fem,
                            child: Align(
                              child: SizedBox(
                                width: 152 * fem,
                                height: 25 * fem,
                                child: Text(
                                  'selesaiataubelum',
                                  // style: SafeGoogleFont (
                                  //   'Poppins',
                                  //   fontSize: 16.1272468567*ffem,
                                  //   fontWeight: FontWeight.w700,
                                  //   height: 1.5*ffem/fem,
                                  //   color: Color(0xff6088c4),
                                  // ),
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    Container(
                      // frame6uMk (1:14)
                      margin: EdgeInsets.fromLTRB(
                          0 * fem, 0 * fem, 0 * fem, 50 * fem),
                      width: 93 * fem,
                      height: 37 * fem,
                      child: Stack(
                        children: [
                          Positioned(
                            // waktuawalztz (1:15)
                            left: 0 * fem,
                            top: 0 * fem,
                            child: Align(
                              child: SizedBox(
                                width: 55 * fem,
                                height: 15 * fem,
                                child: Text(
                                  'Waktu Awal',
                                  // style: SafeGoogleFont (
                                  //   'Poppins',
                                  //   fontSize: 9.3683023453*ffem,
                                  //   fontWeight: FontWeight.w400,
                                  //   height: 1.5*ffem/fem,
                                  //   color: Color(0xff000000),
                                  // ),
                                ),
                              ),
                            ),
                          ),
                          Positioned(
                            // waktuawalrgJ (1:16)
                            left: 0 * fem,
                            top: 12 * fem,
                            child: Align(
                              child: SizedBox(
                                width: 93 * fem,
                                height: 25 * fem,
                                child: Text(
                                  'waktuawal',
                                  // style: SafeGoogleFont (
                                  //   'Poppins',
                                  //   fontSize: 16.1272468567*ffem,
                                  //   fontWeight: FontWeight.w700,
                                  //   height: 1.5*ffem/fem,
                                  //   color: Color(0xff6088c4),
                                  // ),
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    Container(
                      // frame8KJz (1:689)
                      margin: EdgeInsets.fromLTRB(
                          204.25 * fem, 0 * fem, 0 * fem, 20 * fem),
                      width: 93.75 * fem,
                      height: 20 * fem,
                      decoration: BoxDecoration(
                        color: Color(0xff074070),
                        borderRadius: BorderRadius.circular(9.5871562958 * fem),
                      ),
                      child: Center(
                        child: Text(
                          'Resep',
                          // style: SafeGoogleFont (
                          //   'Poppins',
                          //   fontSize: 16.2631111145*ffem,
                          //   fontWeight: FontWeight.w700,
                          //   height: 1.5*ffem/fem,
                          //   color: Color(0xffffffff),
                          // ),
                        ),
                      ),
                    ),
                    Container(
                      // frame78nE (1:19)
                      width: 296 * fem,
                      height: 455 * fem,
                    ),
                  ],
                ),
              ),
            ),
            Positioned(
              // materialsymbolsarrowbackrounde (1:20)
              left: 20.3333435059 * fem,
              top: 31.9848709106 * fem,
              child: Align(
                child: SizedBox(
                  width: 24.66 * fem,
                  height: 24.05 * fem,
                  child: Image.asset(
                    'assets/page-1/images/material-symbols-arrow-back-rounded.png',
                    width: 24.66 * fem,
                    height: 24.05 * fem,
                  ),
                ),
              ),
            ),
            Positioned(
              // detailhyk (1:22)
              left: 20 * fem,
              top: 63 * fem,
              child: Align(
                child: SizedBox(
                  width: 86 * fem,
                  height: 39 * fem,
                  child: Text(
                    'DETAIL',
                    // style: SafeGoogleFont (
                    //   'Poppins',
                    //   fontSize: 25.3333339691*ffem,
                    //   fontWeight: FontWeight.w700,
                    //   height: 1.5*ffem/fem,
                    //   color: Color(0xffffffff),
                    // ),
                  ),
                ),
              ),
            ),
            Positioned(
              // appointmentkBL (1:688)
              left: 20 * fem,
              top: 91 * fem,
              child: Align(
                child: SizedBox(
                  width: 183 * fem,
                  height: 39 * fem,
                  child: Text(
                    'APPOINTMENT',
                    // style: SafeGoogleFont (
                    //   'Poppins',
                    //   fontSize: 25.3333339691*ffem,
                    //   fontWeight: FontWeight.w700,
                    //   height: 1.5*ffem/fem,
                    //   color: Color(0xffffffff),
                    // ),
                  ),
                ),
              ),
            ),
            Positioned(
              // idaptCp2 (1:23)
              left: 20 * fem,
              top: 124 * fem,
              child: Align(
                child: SizedBox(
                  width: 28 * fem,
                  height: 16 * fem,
                  child: Text(
                    'idApt',
                    // style: SafeGoogleFont (
                    //   'Poppins',
                    //   fontSize: 10.4313726425*ffem,
                    //   fontWeight: FontWeight.w400,
                    //   height: 1.5*ffem/fem,
                    //   color: Color(0xffffffff),
                    // ),
                  ),
                ),
              ),
            ),
            Positioned(
              // group31HKg (1:687)
              left: 187.6860046387 * fem,
              top: 14.0000038147 * fem,
              child: Container(
                width: 182.31 * fem,
                height: 195.12 * fem,
                child: Stack(
                  children: [
                    Positioned(
                      // groupaJn (1:472)
                      left: 0 * fem,
                      top: 0 * fem,
                      child: Align(
                        child: SizedBox(
                          width: 182.31 * fem,
                          height: 141.05 * fem,
                          child: Image.asset(
                            'assets/page-1/images/group-i9Y.png',
                            width: 182.31 * fem,
                            height: 141.05 * fem,
                          ),
                        ),
                      ),
                    ),
                    Positioned(
                      // groupeJe (1:558)
                      left: 17.7414245605 * fem,
                      top: 3.7689437866 * fem,
                      child: Align(
                        child: SizedBox(
                          width: 143.4 * fem,
                          height: 191.35 * fem,
                          child: Image.asset(
                            'assets/page-1/images/group.png',
                            width: 143.4 * fem,
                            height: 191.35 * fem,
                          ),
                        ),
                      ),
                    ),
                    Positioned(
                      // appointment7bY (1:686)
                      left: 57.0909118652 * fem,
                      top: 23.0498008728 * fem,
                      child: Align(
                        child: SizedBox(
                          width: 46 * fem,
                          height: 9 * fem,
                          child: Text(
                            'Appointment',
                            // style: SafeGoogleFont (
                            //   'Inter',
                            //   fontSize: 7.126789093*ffem,
                            //   fontWeight: FontWeight.w700,
                            //   height: 1.2125*ffem/fem,
                            //   color: Color(0xffffffff),
                            // ),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
