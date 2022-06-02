//daftarkan controler register

var express = require("express");
var auth = require("./auth");
var router = express.Router();

//daftarkan menu reigst
router.post("/api/v1/register", auth.registrasi);

module.exports = router;
