import 'package:flutter/material.dart';

class MyColumnRowPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.teal,
      body: SafeArea(
        child: Row(
          // mainAxisAlignment: MainAxisAlignment.center,
          // mainAxisSize: MainAxisSize.min,
          // verticalDirection: VerticalDirection.up,
          // mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Container(
              height: 100,
              color: Colors.white,
              child: Text("Container 1"),
            ),
            SizedBox(
              width: 30,
            ),
            Container(
              height: 100,
              color: Colors.blue,
              child: Text("Container 2"),
            ),
            Container(
              height: 100,
              color: Colors.red,
              child: Text("Container 3"),
            ),
          ],
        ),
      ),
    );
  }
}
