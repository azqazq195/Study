import 'dart:html';

import 'package:flutter/material.dart';

class ScreenA extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("ScreenA"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/B');
                },
                child: Text("Go to ScreenB")),
            ElevatedButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/C');
                },
                child: Text("Go to ScreenC"))
          ],
        ),
      ),
    );
  }
}
