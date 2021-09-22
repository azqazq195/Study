import 'package:flutter/material.dart';

class MyNavigatorPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return FirstPage();
  }
}

class FirstPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("First Page")),
      body: Center(
        child: ElevatedButton(
          child: Text("Go to the Second Page."),
          onPressed: () => moveToSecondPage(context),
        ),
      ),
    );
  }
}

void moveToSecondPage(BuildContext context) {
  Navigator.push(context, MaterialPageRoute(builder: (_) => SecondPage()));
}

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Second Page"),
      ),
      body: Center(
        child: ElevatedButton(
          child: Text("Go to the First Page."),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
    );
  }
}
