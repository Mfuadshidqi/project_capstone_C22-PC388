//file ini memuat navigasi
"use strict";

//ekport dg memanggil file controller
module.exports = function (app) {
  const myjson = require("./controller");

  app.route("/").get(myjson.index);
};
