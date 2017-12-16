/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.util.advance.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liguohua on 16/8/21.
 */
public class LocalShellExecutor {
    /**
     * 在本地执行命令行,带有返回值
     *
     * @param command 命令行
     * @return 执行命令后的显示结果
     */
    public static String exexcutorShellCommondsWithResponse(String command) {
        List<String> cmds = new ArrayList<>();
        cmds.add(command);
        return exexcutorShellCommondsWithResponse(cmds);
    }

    /**
     * 在本地执行命令集,带有返回值
     *
     * @param commands 命令集
     * @return 执行命令后的显示结果
     */
    public static String exexcutorShellCommondsWithResponse(List<String> commands) {
        StringBuffer sb = new StringBuffer();
        try {
            //1.拼接命令
            //2.执行命令
            ProcessBuilder builder = new ProcessBuilder();
            builder = builder.command(commands);
            System.out.println(builder.command());
            builder.redirectErrorStream(true);
            Process process = builder.start();
            System.out.println(process);
            int i = process.waitFor();
            System.out.println(i);
            // 3.获取输入
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (String line = ""; (line = input.readLine()) != null; ) {
                sb.append(line);
            }
            // 4.关闭输入
            if (input != null) {
                input.close();
            }
            if (process != null) {
                process.destroy();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
        return sb.toString();
    }

}
