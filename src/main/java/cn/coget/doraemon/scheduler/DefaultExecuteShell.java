//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultExecuteShell implements ExecuteShell {
    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    public DefaultExecuteShell() {
    }

    public boolean execute(String shell) {
        try {
            String tmpName = getTmpName();
            File tmpFile = new File(TMP_DIR + tmpName + ".sh");
            boolean createSuccess = tmpFile.createNewFile();
            if (!createSuccess) {
                throw new RuntimeException("文件创建失败!");
            } else {
                String tmpFilePath = tmpFile.getPath();
                FileUtils.writeByteArrayToFile(tmpFile, shell.getBytes());
                String getX = "chmod a+x " + tmpFilePath;
                Process process = Runtime.getRuntime().exec(getX);
                process = Runtime.getRuntime().exec("bash " + tmpFilePath);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                    System.err.println(line);
                }

                process.waitFor();
                return true;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            return false;
        }
    }

    private static String getTmpName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
