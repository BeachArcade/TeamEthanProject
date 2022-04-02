package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Reader {

  BufferedReader bufferedReader;
  StringTokenizer stringTokenizer;

  public Reader(File file) throws FileNotFoundException {
    bufferedReader = new BufferedReader(new FileReader(file));
  }

  String next() {
    while (stringTokenizer == null || !stringTokenizer.hasMoreElements()) {
      try {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(),"\t");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return stringTokenizer.nextToken();
  }

  int nextInt() {
    return Integer.parseInt(next());
  }

  long nextLong() {
    return Long.parseLong(next());
  }

  double nextDouble() {
    return Double.parseDouble(next());
  }

  String nextString() {
    return next();
  }

  String nextLine() {
    String str = "";
    try {
      str = bufferedReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }
}

