"use strict";
//file ini untuk mengontrol kebutuhan
var response = require("./res"); //memanggil file res
var connection = require("./connect"); //memanggil file connection
const { query } = require("./connect");

//eksport INDEX dengan respon ok
exports.index = function (req, res) {
  response.ok("Applikasi REST API berjalan.", res);
};

//menampilkan data jenis-jenis sampah BY ID: PAGE - KATEGORI SAMPAH dan PINDAI SAMPAH
exports.showWastebyID = function (req, res) {
  let id = req.params.id;
  connection.query(
    "SELECT * FROM jenis_sampah WHERE id_sampah = ?",
    [id],
    function (error, rows, fields) {
      if (error) {
        connection.log(error);
      } else {
        response.ok(rows, res);
      }
    }
  );
};

//menampilkan data sampah dan jasa di page transaksi
//get jasa
exports.getJasa = function (req, res) {
  const id = req.params.id;
  connection.query("SELECT * from jasa", function (error, rows, fields) {
    if (error) {
      console.log(error);
      return;
    }
    response.ok(rows, res);
  });
};

//get sampah
exports.getSampah = function (req, res) {
  const id = req.params.id;
  connection.query(
    "SELECT * from jenis_sampah",
    function (error, rows, fields) {
      if (error) {
        console.log(error);
        return;
      }
      response.ok(rows, res);
    }
  );
};

// menambahkan data di TRANSAKSI: PAGE - TRANSAKSI
exports.addTransaction = function (req, res) {
  //body itu data yang akan dipost berdasarkan input dari user
  let id = req.params.id;
  const date = new Date();
  var total_harga = req.body.total_harga;
  var berat_sampah = req.body.berat_sampah;
  var id_sampah = req.body.id_sampah;
  var id_jasa = req.body.id_jasa;
  var id_user = req.body.id_user;
  connection.query(
    "INSERT INTO transaksi (date_transaksi, total_harga, berat_sampah, id_sampah, id_jasa, id_user) VALUES(?,?,?,?,?,?)",
    [date, total_harga, berat_sampah, id_sampah, id_jasa, id_user],
    function (error, rows, fields) {
      if (error) {
        console.log(error);
      } else {
        response.ok("Berhasil menambahkan data", res);
      }
    }
  );
};

//menampilkan data history: PAGE - HISTORY
exports.showHistory = function (req, res) {
  //menampilkan data dari transaksi, jasa, sampah
  let id = req.params.id;
  connection.query(
    "SELECT jenis_sampah.nama_sampah, transaksi.berat_sampah, transaksi.total_harga FROM history JOIN transaksi JOIN jasa JOIN jenis_sampah WHERE history.id_transaksi = transaksi.id_transaksi AND history.id_jasa = jasa.id_jasa AND history.id_sampah = jenis_sampah.id_sampah ORDER BY transaksi.id_transaksi",
    function (error, rows, fields) {
      if (error) {
        console.log(error);
      } else {
        response.ok("Berhasil menambahkan data", res);
      }
    }
  );
};
