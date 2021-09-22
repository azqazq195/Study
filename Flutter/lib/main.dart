import 'package:flutter/material.dart';
import 'package:flutter_app/navigator/ScreenA.dart';
import 'package:flutter_app/navigator/ScreenB.dart';
import 'package:flutter_app/navigator/ScreenC.dart';

// import 'SnackBar.dart';
// import 'Drawer.dart';
// import 'ToastMessage.dart';
// import 'Container.dart';
// import 'ColumnRow.dart';
// import 'Navigator.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      // title: "Appbar",
      theme: ThemeData(primarySwatch: Colors.red),
      // home: MyNavigatorWithOutAppBarPage(),
      initialRoute: '/A',
      routes: {
        '/A': (context) => ScreenA(),
        '/B': (context) => ScreenB(),
        '/C': (context) => ScreenC(),
      },
    );
  }
}
