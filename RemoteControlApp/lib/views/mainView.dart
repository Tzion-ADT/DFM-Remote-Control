import 'dart:io';

import 'package:dfm_remote_control/main.dart';
import 'package:dfm_remote_control/views/settingsView.dart';
import 'package:dfm_remote_control/widget/NavigationDrawerAcrossPagesWidget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:desktop_window/desktop_window.dart';

import '../Bloc/navigation_drawer_across_pages_bloc.dart';


void main() async {
  //TO DO : it is still not working --> need to check it..
  WidgetsFlutterBinding.ensureInitialized();

  if(Platform.isWindows || Platform.isMacOS || Platform.isLinux){
    const Size fixedSize = Size (700,700);
    await DesktopWindow.setWindowSize(fixedSize);
    await DesktopWindow.setMinWindowSize(fixedSize);
    await DesktopWindow.setMaxWindowSize(fixedSize);
  }
  
  runApp(const MyApp());
}

class MainView extends StatefulWidget {
  const MainView({super.key});

  @override
  MainPageState createState() => MainPageState();
  }

  class MainPageState extends State<MainView>{
  late NavigationDrawerAcrossPagesBloc _bloc;

  @override
  void initState(){
    _bloc = NavigationDrawerAcrossPagesBloc();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create:(context) => _bloc ,
        child:      Scaffold(
            appBar: AppBar (
                title: const Text('ADT Inc - Remote Control')
            ),

            drawer: const NavigationDrawerAcrossPagesWidget(),

            //settings button
            floatingActionButton: FloatingActionButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const SettingsView()),
                );
              },
              child: const Icon(Icons.settings),
            ),
          body: Center(
                child: Image.asset(
                     'assets/adt_logo.png',
                      height: 250.0,
                      width: 250.0,
                      fit: BoxFit.fill,
                ),
          )
        )
    );
  }
}