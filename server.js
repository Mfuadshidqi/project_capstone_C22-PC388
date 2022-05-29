//memanggil liblaries npm
const express = require("express");
const bodyParser = require("body-parser");
const app = express(); //untuk memanggil express js secara global

//parse application/json
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

//memanggil routes
var routes = require("./routes");
routes(app);

app.listen(3000, () => {
  console.log("Server started on port");
});
