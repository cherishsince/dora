package cn.coget.dora.actuator.c2.execute;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * shell 执行器
 *
 * author: sin
 * time: 2020/5/2 12:16 下午
 */
public class ShellActuator implements Actuator {

    private static Logger LOGGER = LoggerFactory.getLogger(ShellActuator.class);

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    @Override
    public String exec(String shell) {
        String logs;
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
                return line;
            }
        } catch (Exception e) {
            LOGGER.error("执行失败! {}", e.getMessage());
            logs = e.getMessage();
            return logs;
        }
    }

    private static String getTmpName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
