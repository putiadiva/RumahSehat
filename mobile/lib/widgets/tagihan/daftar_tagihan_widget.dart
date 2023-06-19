import 'package:flutter/material.dart';
import 'package:rumahsehat/constants.dart';
import 'package:rumahsehat/model/tagihan_model.dart';
import 'package:rumahsehat/screens/tagihan/view_tagihan.dart';
import 'package:intl/intl.dart';

class DaftarTagihanWidget extends StatefulWidget {
  final TagihanModel tagihan;

  DaftarTagihanWidget({Key? key, required this.tagihan})
      : super(key: key);

  @override
  State<DaftarTagihanWidget> createState() => _DaftarTagihanWidgetState();
}

class _DaftarTagihanWidgetState extends State<DaftarTagihanWidget> {


  final Map<String, AssetImage> images = {
    "paid": const AssetImage("lib/assets/images/paid.png"),
    "unpaid": const AssetImage("lib/assets/images/unpaid.png"),
  };

  @override
  Widget build(BuildContext context) {
      return Card(
        child: ListTile(
            onTap: () => {
              Navigator.push(context, MaterialPageRoute(
                  builder: (context) => DetailTagihan(tagihan: widget.tagihan))
              ),
            },
          leading: CircleAvatar(
            backgroundImage: widget.tagihan.isPaid ? images["paid"] : images["unpaid"],
          ),
            title: Text(widget.tagihan.kode),
        subtitle: Text('Terbuat: ${DateFormat.yMMMMd('en_US').format(widget.tagihan.tanggalTerbuat)}'),
            trailing: ElevatedButton(
              onPressed: () => {
                Navigator.push(context, MaterialPageRoute(
                builder: (context) => DetailTagihan(tagihan: widget.tagihan))),
              },
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(kPrimaryColor),
              ),
              child: const Text('Detail'),
            ),
        ),
      );
  }
}

