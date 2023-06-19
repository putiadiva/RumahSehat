class AppointmentModel {
  final String kode;
  final String namaDokter;
  final String namaPasien;
  final String waktuAwal;
  final bool is_done;
  final String? tagihan;

  const AppointmentModel({
    required this.kode,
    required this.namaDokter,
    required this.namaPasien,
    required this.waktuAwal,
    required this.is_done,
    required this.tagihan,
});

  factory AppointmentModel.fromJson(Map<String, dynamic> json) {
    return AppointmentModel(kode: json['kode'], namaDokter: json['dokter']['nama'], namaPasien: json['pasien']['nama'],
        waktuAwal: json['waktuAwal'], is_done: json['isDone'], tagihan: json['tagihan'] != null ? json['tagihan']['kode'] : null);
  }
}