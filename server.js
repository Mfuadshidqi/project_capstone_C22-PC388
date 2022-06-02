//memanggil liblaries npm

const express = require("express");
const bodyParser = require("body-parser");
var morgan = require("morgan");
const app = express(); //untuk memanggil express js secara global

//parse application/json
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(morgan("dev"));

//memanggil routes
var routes = require("./routes");
routes(app);

//daftarkan menu routes dari index
app.use("/auth", require("./middleware"));

app.listen(3000, () => {
  console.log("Server started on port 3000");
});
