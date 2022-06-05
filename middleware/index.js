//daftarkan controler register
//ROUTER
var express = require("express");
var auth = require("./auth");
var router = express.Router();
const verifikasi = require("./verifikasi");

//daftarkan menu regist ke ROUTER
router.post("/api/v1/register", auth.registrasi);
router.post("/api/v1/login", auth.login); //ini pake verifikasi token JWT

//page yg perlu AUTHORIZATION
//bisa buat laman profile
router.get("/api/v1/getUser", verifikasi, auth.getuserlogin);

module.exports = router;
