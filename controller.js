"use strict";

var { response } = require("./res"); //memanggil file res
var connection = require("./connection"); //memanggil file connection

//eksport index dengan respon ok
exports.index = function (req, res) {
  response.ok("Applikasi REST API berjalan.");
};
