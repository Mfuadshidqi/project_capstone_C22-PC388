//file ini memuat navigasi
"use strict";

//ekport dg memanggil file controller
module.exports = function (app) {
  const myjson = require("./controller");

  app.route("/").get(myjson.index);
  app.route("/showuser").get(myjson.showAllUser);
  app.route("/showuserbyid/:id").get(myjson.showAllUserbyID);
  app.route("/addUser").post(myjson.addUser);
  app.route("/showwastebyid/:id").get(myjson.showWastebyID);
  app.route("/addWaste").post(myjson.addWaste);
};
