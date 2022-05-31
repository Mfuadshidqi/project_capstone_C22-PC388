"use strict";
//file ini untuk mengontrol kebutuhan
var response = require("./res"); //memanggil file res
var connection = require("./connect"); //memanggil file connection

//eksport INDEX dengan respon ok
exports.index = function (req, res) {
  response.ok("Applikasi REST API berjalan.", res);
};

//menampilkan semua data user: PAGE - PROFILE
exports.showAllUser = function (req, res) {
  connection.query("SELECT * FROM user", function (error, rows, fields) {
    if (error) {
      connection.log(error);
    } else {
      response.ok(rows, res);
    }
  });
};

//menampilkan data user berdasarkan id: PAGE - LOGIN
exports.showAllUserbyID = function (req, res) {
  let id = req.params.id;
  connection.query(
    "SELECT * FROM user WHERE id_user = ?",
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

//menambahkan data user --> PAGE - REGISTRASI
exports.addUser = function (req, res) {
  //body itu data yang akan dipost berdasarkan input dari user
  var nama_user = req.body.nama_user;
  var email_user = req.body.email_user;
  var pw_user = req.body.pw_user;
  var address_user = req.body.address_user;

  connection.query(
    "INSERT INTO user (nama_user, email_user, pw_user, address_user) VALUES(?, ?, ?, ?)",
    [nama_user, email_user, pw_user, address_user],
    function (error, rows, fields) {
      if (error) {
        console.log(error);
      } else {
        response.ok("Berhasil menambahkan data", res);
      }
    }
  );
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

// menambahkan data TRANSAKSI: PAGE - TRANSAKSI
exports.addTransaction = function (req, res) {
  //body itu data yang akan dipost berdasarkan input dari user
  let id = req.params.id;
  var nama_sampah = req.body.nama_sampah;
  var harga_sampah = req.body.harga_sampah;
  connection.query(
    "INSERT INTO jenis_sampah (nama_sampah, harga_sampah) VALUES(?, ?, ?)",
    [nama_sampah, gambar_sampah, harga_sampah],
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

//
