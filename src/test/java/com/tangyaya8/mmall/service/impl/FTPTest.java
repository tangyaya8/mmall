package com.tangyaya8.mmall.service.impl;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * @author tangxuejun
 * @version 2019-05-14 16:25
 */
public class FTPTest {

    public static void main(String[] args) throws IOException {
        FTPClient client = new FTPClient();
        client.connect("localhost", 21);
        boolean login = client.login("tangbaobao", "tangbaobao");
        System.out.println(login);
    }
}
