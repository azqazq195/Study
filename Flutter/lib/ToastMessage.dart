import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

class MyToastMessagePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Snack Bar"),
        centerTitle: true,
      ),
      body: MyToastMessage(),
    );
  }
}

class MyToastMessage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: TextButton(
        onPressed: () {
          flutterToast();
        },
        child: Text('Toast'),
      ),
    );
  }
}

void flutterToast() {
  Fluttertoast.showToast(
      msg: "Toast Message22",
      gravity: ToastGravity.BOTTOM_RIGHT,
      backgroundColor: Colors.redAccent,
      fontSize: 20.0,
      textColor: Colors.white,
      toastLength: Toast.LENGTH_LONG,
      webBgColor: "#00b09b",
      webPosition: "right");
}
