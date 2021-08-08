//서버 실행법
// node server.js

//데이터베이스를 가져오기 위한 설정
const fs = require("fs");
// const data = fs.readFileSync("./database.json");
// const conf = JSON.parse(data);
const mysql = require("mysql");
//DB에 연결
const connection = mysql.createConnection({
  host: "projectmysql.cc86ypuql5hv.ap-northeast-2.rds.amazonaws.com",
  user: "master",
  password: "master0218",
  port: "3306",
  database: "project",
});

//node.js 익스플레스 설정
const express = require("express");
const { connect } = require("http2");
const app = express();

//서버 포트 설정
const server = app.listen(5000, () => {
  console.log("Start server");
});

//api test 요청
app.get("/api/test1:type", async (req, res) => {
  if (connection.state === "disconnected") connection.connect();
  console.log("connect");
  if (connection.state === "connected") connection.end();
});

app.get("/api/test2:type", (res) => {
  console.log("TEST");
});
