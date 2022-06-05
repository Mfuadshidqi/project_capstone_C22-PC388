var connection = require("../connect");
var mysql = require("mysql");
var md5 = require("MD5");
var response = require("../res");
var jwt = require("jsonwebtoken");
var config = require("../config/secret");
var ip = require("ip");
const conn = require("../connect");

//controller untuk REGISTER
exports.registrasi = function (req, res) {
  //data yang akan dimasukan ditampung di array post
  var post = {
    nama_user: req.body.nama_user,
    email_user: req.body.email_user,
    pw_user: md5(req.body.pw_user),
    address_user: req.body.address_user,
  };

  //buat query untuk cek apakah email udah terdaftar apa belum
  var query = "SELECT email_user FROM ?? WHERE ?? = ?";
  var table = ["user", "email_user", post.email_user];

  query = mysql.format(query, table);

  connection.query(query, function (error, rows) {
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
        response.ok("Email sudah terdafatar!", res);
      }
    }
  });
};

//controller untuk LOGIN
exports.login = function (req, res) {
  var post = {
    email_user: req.body.email_user,
    pw_user: req.body.pw_user,
  };

  var query = "SELECT * FROM ?? WHERE ??=? AND ??=?";
  var table = [
    "user",
    "email_user",
    post.email_user,
    "pw_user",
    md5(post.pw_user),
  ];

  query = mysql.format(query, table);
  connection.query(query, function (error, rows) {
    if (error) {
      console.log(error);
    } else {
      if (rows.length == 1) {
        //apakah ketika menampilkan data dari query itu ada datanya
        var token = jwt.sign({ rows }, config.secret, {
          expiresIn: 1440, //25menit
        });
        id_usertoken = rows[0].id_user;

        var data = {
          id_usertoken: id_usertoken,
          access_token: token,
          ip_address: ip.address(),
        };

        /**
         * ! ini bisa diapus, nanti tokennya simpen di local storage bagian frontend
         * *SEMANGAT
         */
        var query = "INSERT INTO ?? SET ?";
        var table = ["token"];

        query = mysql.format(query, table);
        connection.query(query, data, function (error, rows) {
          if (error) {
            console.log(error);
          } else {
            res.json({
              success: true,
              message: "Token, JWT tergnerate!",
              token: token,
              currUser: data.id_usertoken,
            });
          }
        });
      } else {
        res.json({ error: true, Message: "Email atau password salah!" });
      }
    }
  });
};

//dari token jadi data user
exports.getuserlogin = function (req, res) {
  const token = req.headers.authorization?.split(" ")[1];
  const user = jwt.verify(token, config.secret);
  res.json(user);
};
