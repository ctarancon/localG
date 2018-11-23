
var  MONGODB_DATABASE= `${process.env.MONGODB_DATABASE}`;   
var  IP= `${process.env.IP}`;
var  PORT= `${process.env.PORT}`;



var express  = require("express"),
    app      = express(),
    http     = require("http"),
    helmet = require('helmet'),
    server   = http.createServer(app),
    mongoose = require('mongoose'),
    bodyParser = require('body-parser'),
    methodOverride = require('method-override'),
    cors = require('cors'),
    fetch = require('node-fetch'),
    json2csv = require('express-json2csv');
    
var  IP= `${process.env.IP}`;
var  PORT= `${process.env.PORT}`;
var  MONGODB_DATABASE= `${process.env.MONGODB_DATABASE}`;
var  MONGODB_USERNAME= `${process.env.MONGODB_USERNAME}`;
var  MONGODB_PASSWORD= `${process.env.MONGODB_PASSWORD}`;
var  MONGODB_REP= `${process.env.MONGODB_REP}`;
var  REPLICA_SET= `${process.env.REPLICA_SET}`;


//app.configure(function () {
  //app.use(express.bodyParser());
  //app.use(express.methodOverride());
  app.use(methodOverride());

  app.use(json2csv);

  //app.use(app.router);
  app.use(cors());

  app.use(helmet());
//});

// parse application/json
app.use(bodyParser.json());                        

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/api/', function(req, res) {
  res.send("Hello world!");
});

//routes = require('./routes/usuarioRoute')(app);

var rutas = require('./routes/rutas');
rutas(app);

//var uri = 'mongodb://movistar.across.cl:27018/movistar';
//var uri = 'localhost:3000/';
//var uri = 'mongodb://192.168.100.8:27018/movistar';

//var uri = 'mongodb://localhost:27019/movistar';
//var uri = `mongodb://${IP}:${PORT}/${MONGODB_DATABASE}`;
var uri = `mongodb://${MONGODB_USERNAME}:${MONGODB_PASSWORD}@${MONGODB_REP}/${MONGODB_DATABASE}?replicaSet=${REPLICA_SET}`


//var uri = 'mongodb://mongodb:27018/movistar';
//var uri = 'mongodb://localhost:27018/movistar';

console.log('uri',uri)
//mongoose.connect(uri, opt).then(
mongoose.connect(uri).then(
  () => { console.log('Connected to Database');},
  err => { 	console.log('ERROR: connecting to Database. ' + err);}
);



server.listen(3000, function() {
  console.log("Node server running on http://localhost:3000");
});
