var connection = require("../connect");
var mysql = require("mysql");
var md5 = require("MD5");
var response = require("../res");
var jwt = require("jsonwebtoken");
var config = require("../config/secret");
var ip = require("ip");

//controller untuk register
exports.registrasi = function (req, res) {
  //data yang akan dimasukan ditampung di array post
  var post = {
    nama_user: req.body.nama_user,
    email_user: req.body.email_user,
    pw_user: md5(req.body.pw_user),
    address_user: req.body.address_user,
  };

  //buat query untuk cek apakah email udah terdaftar apa belum
  var query = "SELECT email_user FROM ?? WHERE ??";
  var table = ["user", "email_user", post.email_user];

  query = mysql.format(query, table);

  connection.query(query, table, function (error, rows) {
    if (error) {
      console.log(error);
    } else {
      if (rows.length == 0) {
        var query = "INSERT INTO ?? SET ?";
        var table = ["user"];
        query = mysql.format(query, table);
        connection.query(query, post, function (error, rows) {
          if (error) {
            console.log(error);
          } else {
            response.ok("Berhasil menambahkan data user baru", res);
          }
        });
      } else {
        response.ok("Email sudah terdafatar!");
      }
    }
  });
};
