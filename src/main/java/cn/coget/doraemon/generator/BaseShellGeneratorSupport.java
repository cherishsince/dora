//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.generator;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public abstract class BaseShellGeneratorSupport implements ShellGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseShellGeneratorSupport.class);
    private static final String FORMAT_2 = "\\{%s\\}";

    public BaseShellGeneratorSupport() {
    }

    public static String doFormat(String messagePattern, Map<String, Object> params) {
        if (StringUtils.isEmpty(messagePattern)) {
            return messagePattern;
        } else {
            Entry entry;
            for(Iterator var2 = params.entrySet().iterator(); var2.hasNext(); messagePattern = messagePattern.replaceAll(String.format("\\{%s\\}", entry.getKey()), String.valueOf(entry.getValue()))) {
                entry = (Entry)var2.next();
            }

            return messagePattern;
        }
    }

    protected static String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
        int i = 0;

        for(int l = 0; l < params.length; ++l) {
            int j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                LOGGER.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})", new Object[]{code, messagePattern, params});
                if (i == 0) {
                    return messagePattern;
                }

                sbuf.append(messagePattern.substring(i, messagePattern.length()));
                return sbuf.toString();
            }

            sbuf.append(messagePattern.substring(i, j));
            sbuf.append(params[l]);
            i = j + 2;
        }

        if (messagePattern.indexOf("{}", i) != -1) {
            LOGGER.error("[doFormat][参数过少：错误码({})|错误内容({})|参数({})", new Object[]{code, messagePattern, params});
        }

        sbuf.append(messagePattern.substring(i, messagePattern.length()));
        return sbuf.toString();
    }
}
