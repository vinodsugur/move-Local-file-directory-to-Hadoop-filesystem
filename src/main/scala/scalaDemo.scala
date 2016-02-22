/**
  * Created by vinod_sugur on 2/9/2016.
  */

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.commons.io.IOUtils

object scalaDemo {
  def main(args: Array[String]) {
     DataReader.writeFile("D:/software/New Text Document.txt")
  }
}
