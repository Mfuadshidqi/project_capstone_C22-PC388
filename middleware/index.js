//daftarkan controler register
//ROUTER
var express = require("express");
var auth = require("./auth");
var router = express.Router();

//daftarkan menu regist ke ROUTER
router.post("/api/v1/register", auth.registrasi);

module.exports = router;
