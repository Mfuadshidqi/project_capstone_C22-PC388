const jwt = require("jsonwebtoken");
const config = require("../config/secret");

function verif(req, res, next) {
  const token = req.headers.authorization?.split(" ")[1];
  if (!token) {
    return res.status(401).send("Access denied");
  }
  try {
    const verifikasi = jwt.verify(token, config.secret);
    req.user = verifikasi;
    next();
  } catch (error) {
    res.status(400).send("invalid Token");
  }
}

module.exports = verif;
