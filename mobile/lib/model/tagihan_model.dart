class TagihanModel {
  final String kode;
  final DateTime tanggalTerbuat;
  final DateTime? tanggalBayar;
  final bool isPaid;
  final int jumlahTagihan;
  // final Appointment appointment;

  TagihanModel (
      {required this.kode,
        required this.tanggalTerbuat,
        required this.tanggalBayar,
        required this.isPaid,
        required this.jumlahTagihan,
        // required this.appointment
      });

  factory TagihanModel.fromJson(Map<String, dynamic> json) {
    return TagihanModel(
        kode: json['kode'],
        tanggalTerbuat: DateTime.parse(json['tanggalTerbuat']),
        tanggalBayar: json['tanggalBayar'] != null ? DateTime.parse(json['tanggalBayar']) : null,
        isPaid: json['isPaid'],
        jumlahTagihan: json['jumlahTagihan'],
        // appointment: json['appointment']
    );
  }

}

