import 'dart:async';
import 'dart:io';
import 'dart:convert';
import 'package:flutter/material.dart';

import 'SingleMachineView.dart';

class MachinesView extends StatefulWidget {
  final String userName;

  const MachinesView({super.key, required this.userName});

  @override
  State<StatefulWidget> createState() => MachinesViewState(userName);
}

class MachinesViewState extends State<MachinesView> {
  String userName;
  Socket? _socket;
  StreamSubscription<List<int>>? _subscription;


  final ScrollController _scrollController = ScrollController();

  MachinesViewState(this.userName);

  void _connectToServer() async {
    try {
      final InternetAddress serverAddress = InternetAddress('199.203.44.142');
      const port = 1999;
      _socket = await Socket.connect(serverAddress, port);
    } catch (e) {
      print("Unable to connect: $e");
    }
  }

  // String _getDataForMachine() {
  //   _socket!.write("Flutter here, send me data");
  //
  //   // Wait for the server's response
  //   String response = "test";
  //
  //   //listen only once , only for the current message
  //   _socket!.listen((data) {
  //
  //     // Convert byte data to string
  //     response = utf8.decode(data);
  //     //print('Server: $response');
  //
  //     // Complete the future with the server's response
  //
  //   });
  //
  //   // Return the response when it's ready
  //   return response;
  // }

  Future<String> _getDataForMachine() async {
    Completer<String> completer = Completer<String>();
    String response = "";
    _socket!.write("Flutter here, send me data");

    // Listen for the server's response
    _socket!.asBroadcastStream().listen((data) {
      response = utf8.decode(data);
      completer.complete(response);
      // Optionally, print the server's response
      // print('Server: $response');
    });
    // Complete the future with the server's response


    // Only listen once, so cancel the subscription after receiving the data
    //_socket!.close(); // Assuming you want to close the socket after receiving the response
    // Return the future from the completer
    return completer.future;
  }

  @override
  void initState() {
    super.initState();
    _connectToServer(); // Call the connect method when the widget is initialized
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Welcome $userName !!'),
        automaticallyImplyLeading: false,
      ),
      body:
      Scrollbar(
          controller: _scrollController,
          thumbVisibility: true, // Always show the scrollbar
          thickness: 8.0, // Customize the scrollbar thickness
          radius: const Radius.circular(10), // Customize the scrollbar radius
        child: SingleChildScrollView(
          controller: _scrollController,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Platform.isAndroid?
            Align(
                alignment: Alignment.center,
                child: TextButton(
                    onPressed: () {
                      // Call the async function and handle the result
                      _getDataForMachine().then((data) {
                        // Once data is available, navigate to the next screen
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SingleMachinesView(
                              userName: this.userName,
                              machineData: data, // Pass the fetched data as a parameter
                            ),
                          ),
                        );
                      });
                    }, child: Column (
              mainAxisSize: MainAxisSize.min,
              children: [
                const Text('8020'),
                const SizedBox(height: 1),
                Image.asset(
                  'assets/adt.png',
                  width: 200,
                  height: 200,
                ),
                const SizedBox(width: 20 , height: 30),
              ],
            ))) :
            // TextButton(onPressed: (){
            //   Navigator.push(context,
            //       MaterialPageRoute(builder: (context) => SingleMachinesView( userName: this.userName , machineData: _getDataForMachine(),)));
            // }, child: Column (
            //   mainAxisSize: MainAxisSize.min,
            //   children: [
            //     const Text('8020'),
            //     const SizedBox(height: 1),
            //     Image.asset(
            //       'assets/adt.png',
            //       width: 200,
            //       height: 200,
            //     ),
            //     const SizedBox(width: 20 , height: 30),
            //   ],
            // )),
            Align(
                alignment: Alignment.center,
                child: TextButton(
                    onPressed: () {
                      // Call the async function and handle the result
                      _getDataForMachine().then((data) {
                        // Once data is available, navigate to the next screen
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SingleMachinesView(
                              userName: this.userName,
                              machineData: data, // Pass the fetched data as a parameter
                            ),
                          ),
                        );
                      });
                    }, child: Column (
              mainAxisSize: MainAxisSize.min,
              children: [
                const Text('7122'),
                const SizedBox(height: 1),
                Image.asset(
                  'assets/adt.png',
                  width: 200,
                  height: 200,
                ),
                const SizedBox(width: 20 , height: 30),
              ],
            ))),
            Align(
                alignment: Alignment.center,
                child: TextButton(
                    onPressed: () {
                      // Call the async function and handle the result
                      _getDataForMachine().then((data) {
                        // Once data is available, navigate to the next screen
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SingleMachinesView(
                              userName: this.userName,
                              machineData: data, // Pass the fetched data as a parameter
                            ),
                          ),
                        );
                      });
                    }, child: Column (
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    const Text('7125'),
                    const SizedBox(height: 1),
                    Image.asset(
                      'assets/adt.png',
                      width: 200,
                      height: 200,
                    ),
                    const SizedBox(width: 20 , height: 30),
                  ],
                ))),
            Align(
                alignment: Alignment.center,
                child: TextButton(
                    onPressed: () {
                      // Call the async function and handle the result
                      _getDataForMachine().then((data) {
                        // Once data is available, navigate to the next screen
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SingleMachinesView(
                              userName: this.userName,
                              machineData: data, // Pass the fetched data as a parameter
                            ),
                          ),
                        );
                      });
                    }, child: Column (
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    const Text('7900'),
                    const SizedBox(height: 1),
                    Image.asset(
                      'assets/adt.png',
                      width: 200,
                      height: 200,
                    ),
                    const SizedBox(width: 20 , height: 30),
                  ],
                ))),
            Align(
                alignment: Alignment.center,
                child: TextButton(
                    onPressed: () {
                      // Call the async function and handle the result
                      _getDataForMachine().then((data) {
                        // Once data is available, navigate to the next screen
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SingleMachinesView(
                              userName: this.userName,
                              machineData: data, // Pass the fetched data as a parameter
                            ),
                          ),
                        );
                      });
                    }, child: Column (
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    const Text('80WT'),
                    const SizedBox(height: 1),
                    Image.asset(
                      'assets/adt.png',
                      width: 200,
                      height: 200,
                    ),
                    const SizedBox(width: 20 , height: 30),
                  ],
                )))

            // const SizedBox(height: 20), // Add some space between the table and the image
            // Image.asset(
            //   'assets/8030.png',
            //   width: 200,
            //   height: 200,
            // ),
          ],
        ),
      )
      )
      //   <--- image
    );
  }

  @override
  void dispose() {
    _socket?.close(); // Close the socket connection when the widget is disposed
    super.dispose();
  }
}
