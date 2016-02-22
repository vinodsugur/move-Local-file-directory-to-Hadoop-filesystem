/**
  * Created by vinod_sugur on 2/19/2016.
  */

import java.io._

import org.apache.commons.io.IOUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs._

object DataReader {
  val hadoopconf = new Configuration()
  hadoopconf.set("fs.default.name","hdfs://192.168.56.101:8020")
  val hdfsDir =  "/user/hue/"
  val fs = FileSystem.get(hadoopconf);
  var basePath:String = null;


  def writeFile(fileName: Any): Unit = {
    var file: File = null
    if (fileName.isInstanceOf[File]) file = fileName.asInstanceOf[java.io.File]
    if (fileName.isInstanceOf[String]) {
      file = new File(fileName.asInstanceOf[String])
    }

      if (file.isDirectory()) {
      fs.mkdirs(new Path(file.getPath().replaceFirst(basePath,hdfsDir) + "/" + file.getName))
      for (fileIncr <- file.listFiles()) writeFile(fileIncr)
    }

    if (basePath == null) basePath = file.getParentFile().getPath()
    if (file.isFile) copyFile(file)
  }

  def copyFile(srcFile: File): Unit = {
    //srcFile.getName
    var hdfsPath:String = srcFile.getPath().replace(basePath + "\\",hdfsDir)
    fs.copyFromLocalFile(false,new Path(srcFile.getPath),new Path(hdfsPath))

/*    val outFileStream = fs.create(new Path(hdfsPath))
    var srcfileStream = new FileInputStream(srcFile)
    var bytes = Array.ofDim[Byte](100)
    var flag = true
    while(flag) {
      var fileRead:Int = srcfileStream.read(bytes)
      if (fileRead == -1) flag = false
      IOUtils.copy(srcfileStream, outFileStream)
    }
    //Close both files
    outFileStream.close();
    srcfileStream.close()
  */
     }
}