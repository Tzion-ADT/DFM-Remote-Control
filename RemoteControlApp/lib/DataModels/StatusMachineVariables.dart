import 'dart:convert';

class StatusMachineVariable{
  final String UserStatus;
  final String Status;
  final String RecipeName;
  final int SawId;
  final String BladeName;
  final String LoginName;
  final int AirPressure;
  final int SpindleSpeed;

  StatusMachineVariable(
      {
        required this.UserStatus,
        required this.Status,
        required this.RecipeName,
        required this.SawId,
        required this.BladeName,
        required this.LoginName,
        required this.AirPressure,
        required this.SpindleSpeed,
      }
      );

  factory StatusMachineVariable.fromJson(String jsonString){
    Map<String, dynamic> json = jsonDecode(jsonString);
    return StatusMachineVariable(
      UserStatus: json['userStatus'],
      Status: json['status'],
      RecipeName: json['recipeName'],
      SawId: json['sawId'],
      BladeName: json['bladeName'],
      LoginName: json['loginName'],
      AirPressure: json['airPressure'],
      SpindleSpeed: json['spindleSpeed'],
    );
  }
}