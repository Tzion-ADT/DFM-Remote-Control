import 'package:flutter/cupertino.dart';

enum changePage {
  loginView,
  settingsView,
  machineView,
  logOut,
  aboutView
}

class NavigationItem{
  final changePage item;
  final String title;
  final IconData icon;

  NavigationItem(
      this.item,
      this.title,
      this.icon
      );
}
