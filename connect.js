//memanggil lib mysql
var mysql = require("mysql");

//membuat koneksi database
const conn = mysql.createConnection({
  // host: "localhost",
  // user: "root",
  // password: "",
  // database: "waste",

  host: "104.154.30.56",
  user: "waste-app-user",
  password: "wasteapp101",
  database: "waste",
});

conn.connect((err) => {
  if (err) {
    throw err;
  } else {
    console.log("MYSQL berhasil terkoneksi");
  }
});

module.exports = conn;
