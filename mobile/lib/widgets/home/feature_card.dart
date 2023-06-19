import 'package:flutter/material.dart';

class FeatureCard extends StatelessWidget {
  final String svgSrc;
  final String title;
  final VoidCallback press;

  const FeatureCard({Key? key, required this.svgSrc, required this.title, required this.press}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(13),
      child: Container(
        // padding: EdgeInsets.all(20),
        decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(13),
            boxShadow: const [
              BoxShadow(
                offset: Offset(0,17),
                blurRadius: 17,
                spreadRadius: -23,
                color: Colors.black38,
              )
            ]
        ),
        child: Material(
          color: Colors.transparent,
          child: InkWell(
            onTap: press,
            child:  Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                children: [
                  const Spacer(),
                  Image.asset(svgSrc, height: 120, width: 120),
                  const Spacer(),
                  Text(
                    title,
                    textAlign: TextAlign.center,
                    style: Theme.of(context).textTheme.titleSmall?.copyWith(fontSize: 12),
                  )
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
