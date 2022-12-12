import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({Key key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: Icon(
          Icons.menu,
          color: Colors.black45,
        ),
        title: Text(
          'Movies-db'.toUpperCase(),
          style: Theme.of(context).textTheme.caption?.copyWith(
            color: Colors.black45,
            fontSize: 20,
            fontWeight: FontWeight.bold,
            fontFamily: 'muli',
          ),
        ),
        actions: [
          Container(
            margin: EdgeInsets.only(right: 15),
            child: CircleAvatar(
              backgroundImage: AssetImage('assets/images/logo.jpg'),
            ),
          ),
        ],
      ),
    );

  }
}



//##################################################
// Scaffold(
// appBar: AppBar(
// backgroundColor: Colors.transparent,
// elevation: 0,
// leading: const Icon(
// Icons.menu,
// color: Colors.black45,
// ),
// title: Text(
// "Movies".toUpperCase(),
// style: Theme.of(context).textTheme.caption?.copyWith(
// color: Colors.black45,
// fontSize: 20,
// fontWeight: FontWeight.bold,
// ) ,
// ),
// actions: [
// Container(
// margin: const EdgeInsets.only(right: 15),
// child: const CircleAvatar(
// backgroundImage: const AssetImage("assets/images/logo.jpg"),
// ),
// )
// ],
// ),
// );
//##################################################



// Widget _Movies() {
//   return Container(
//     //color: Colors.green,
//
//     child: Container(
//       child: Text("Movies : " , style: TextStyle(color: Colors.black , fontSize: 70),) ,
//       margin: EdgeInsets.fromLTRB(16, 8, 16, 8),
//     height: 200,
//     //width: 100,
//     color: Colors.grey,
//   ),
//   );
// }

// Widget _secListView()
// {
//   return Container(
//     //color: Colors.red,
//
//     child: ListView.builder(
//
//         scrollDirection: Axis.vertical,
//         itemCount: 20,
//         shrinkWrap: true,
//
//         itemBuilder: (context , index)
//         {
//           return Card(
//             margin: EdgeInsets.fromLTRB(16, 8, 16, 8),
//             child: Container(
//               height: 200,
//               //width: 100,
//               color: Colors.red,
//             ),
//
//           );
//
//         }
//     ),
//   );
// }
