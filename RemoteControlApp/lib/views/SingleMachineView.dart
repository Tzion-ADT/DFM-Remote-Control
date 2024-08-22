import 'dart:io';
import 'package:dfm_remote_control/DataModels/StatusMachineVariables.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class SingleMachinesView extends StatefulWidget {
  final String userName;
  final String machineData;

  const SingleMachinesView({super.key, required this.userName , required this.machineData});

  @override
  State<StatefulWidget> createState() => SingleMachinesViewState(userName , machineData);
}

class SingleMachinesViewState extends State<SingleMachinesView> {
  final String userName;
  final String machineData;
  late StatusMachineVariable machineVariabletest;
  Socket? _socket;

  final ScrollController _scrollController = ScrollController();

  SingleMachinesViewState(this.userName, this.machineData);

  @override
  void initState() {
    super.initState();
    //read from Json
    // print('From single machine:');
    // print(this.machineData);
    machineVariabletest = StatusMachineVariable.fromJson(this.machineData);
    //_connectToServer(); // Call the connect method when the widget is initialized
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        iconTheme: const IconThemeData(color: Colors.white),
        title: Text(
          'Welcome ${this.userName}!!',
          style: TextStyle(color: Colors.grey),
        ),
        centerTitle: false,
        backgroundColor: Color.fromARGB(255,112,119,249),
        systemOverlayStyle: SystemUiOverlayStyle.dark,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // Add a title above the table
            Text(
              '7100_1', // Title text
              style: TextStyle(
                fontSize: 24, // Adjust the font size as needed
                fontWeight: FontWeight.bold, // Make the title bold
              ),
            ),
            SizedBox(height: 20), // Add some spacing between the title and the table
            Table(
              border: TableBorder.all(),
              children: [
                TableRow(children: [
                  TableCell(child: Center(child: Text('Status'))),
                  TableCell(child: Center(child: Text(machineVariabletest.Status))),
                ]),
                TableRow(children: [
                  TableCell(child: Center(child: Text('Recipe Name'))),
                  TableCell(child: Center(child: Text(machineVariabletest.RecipeName))),
                ]),
                TableRow(children: [
                  TableCell(child: Center(child: Text('Saw Id'))),
                  TableCell(child: Center(child: Text(machineVariabletest.SawId.toString()))),
                ]),
                TableRow(children: [
                  TableCell(child: Center(child: Text('Blade Name'))),
                  TableCell(child: Center(child: Text(machineVariabletest.BladeName))),
                ]),
                TableRow(children: [
                  TableCell(child: Center(child: Text('Login User Name'))),
                  TableCell(child: Center(child: Text(machineVariabletest.LoginName))),
                ]),
                TableRow(children: [
                  TableCell(child: Center(child: Text('Air Pressure'))),
                  TableCell(child: Center(child: Text(machineVariabletest.AirPressure.toString()))),
                ]),
                TableRow(children: [
                  TableCell(child: Center(child: Text('Spindle Speed'))),
                  TableCell(child: Center(child: Text(machineVariabletest.SpindleSpeed.toString()))),
                ]),
              ],
            ),
          ],
        ),
      ),
      //   <--- image
    );
  }

  @override
  void dispose() {
    _socket?.close(); // Close the socket connection when the widget is disposed
    super.dispose();
  }
}
