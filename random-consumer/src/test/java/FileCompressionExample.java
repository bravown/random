import java.io.*;
import java.util.zip.*;

public class FileCompressionExample {
   public static void main(String[] args) {
      // 要压缩的文件路径
      String filePath = "D:\\MyFile\\Projects\\MyProject\\自定义异常测试项目\\自定义异常测试项目.iml";

      // 压缩后的文件名
      String compressedFileName = "compressed.zip";

      try {
         FileInputStream fileInputStream = new FileInputStream(filePath);
         FileOutputStream fileOutputStream = new FileOutputStream(compressedFileName);
         ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
         ZipEntry zipEntry = new ZipEntry(new File(filePath).getName());
         zipOutputStream.putNextEntry(zipEntry);

         byte[] buffer = new byte[1024];
         int length;
         while ((length = fileInputStream.read(buffer)) > 0) {
            zipOutputStream.write(buffer, 0, length);
         }

         zipOutputStream.closeEntry();
         zipOutputStream.close();
         fileOutputStream.close();
         fileInputStream.close();

         System.out.println("File compression completed.");

      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}