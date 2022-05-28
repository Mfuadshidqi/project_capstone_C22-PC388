var mysql = require('mysql');

//membuat koneksi database
const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'waste-app'
});

conn.connect((err) => {
    if (err) {
        throw err;
    }else if{
        console.log('Mysql berhasil terkoneksi');
    }
});

module.exports = conn;