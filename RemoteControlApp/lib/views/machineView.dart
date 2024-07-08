import 'package:flutter/material.dart';

class MachineView extends StatefulWidget {
  final String userName;
  const MachineView({super.key, required this.userName});

  @override
  State<StatefulWidget> createState() => MachineViewState(userName);

}

class MachineViewState extends State<MachineView> {
  final String userName;
  MachineViewState(this.userName);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Welcome ${userName} !!'),
          automaticallyImplyLeading: false,
        ),
        body: Center (
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Table(
                border: TableBorder.all(),
                children: const [
                  TableRow(children: [
                    TableCell(child: Center(child: Text('Header 1'))),
                    TableCell(child: Center(child: Text('Header 2'))),
                    TableCell(child: Center(child: Text('Header 3'))),
                  ]),
                  TableRow(children: [
                    TableCell(child: Center(child: Text('Row 1, Col 1'))),
                    TableCell(child: Center(child: Text('Row 1, Col 2'))),
                    TableCell(child: Center(child: Text('Row 1, Col 3'))),
                  ]),
                  TableRow(children: [
                    TableCell(child: Center(child: Text('Row 2, Col 1'))),
                    TableCell(child: Center(child: Text('Row 2, Col 2'))),
                    TableCell(child: Center(child: Text('Row 2, Col 3'))),
                  ]),
                ],
              ),
              const SizedBox(height: 20), // Add some space between the table and the image
              Image.asset(
                'assets/8030.png',
                width: 200,
                height: 200,
              ),
            ],
          ),
        )
      //   <--- image
      );
    }
  }
