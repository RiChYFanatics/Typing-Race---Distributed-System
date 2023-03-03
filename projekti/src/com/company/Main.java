//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Client {
    public Client() {
    }

    public static void main(String[] args) throws IOException {
        try {
            Socket connection = new Socket("127.0.0.1", 1234);
            System.out.println("Connected: " + connection);
            DataOutputStream output = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));
            DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean cont = true;
            int loop = 0;
            System.out.println(input.readUTF());

            while(cont) {
                String intro = input.readUTF();
                System.out.println(intro);
                int func = Integer.parseInt(br.readLine());
                output.writeInt(func);
                output.flush();
                switch(func) {
                    case 1:
                        System.out.println(input.readUTF());
                        String username = br.readLine();
                        output.writeUTF(username);
                        output.flush();
                        System.out.println(input.readUTF());
                        String password = br.readLine();
                        output.writeUTF(password);
                        output.flush();
                        System.out.println(input.readUTF());
                        break;
                    case 2:
                        if (input.readUTF().equals("y")) {
                            System.out.println("You are already logged in!");
                        } else {
                            System.out.println(input.readUTF());
                            String given_username = br.readLine();
                            output.writeUTF(given_username);
                            output.flush();
                            System.out.println(input.readUTF());
                            String given_password = br.readLine();
                            output.writeUTF(given_password);
                            output.flush();
                            System.out.println(input.readUTF());
                        }
                        break;
                    case 3:
                        if (!input.readUTF().equals("y")) {
                            System.out.println("Please log in first!");
                            break;
                        } else {
                            System.out.println(input.readUTF());
                            System.out.println(input.readUTF());
                            System.out.println("Press 1 when you are ready to start the game.");

                            while(loop == 0) {
                                int user_ready = Integer.parseInt(br.readLine());
                                if (user_ready == 1) {
                                    break;
                                }

                                System.out.println(loop);
                            }

                            System.out.println("out of the loop");
                            output.writeUTF("y");
                            output.flush();
                            System.out.println(input.readUTF());
                        }
                    case 4:
                        System.out.println(input.readUTF());
                        System.out.println(input.readUTF());
                        System.out.println(input.readUTF());
                        System.out.println(input.readUTF());
                        throw new IllegalStateException("Unexpected value: " + func);
                    default:
                        throw new IllegalStateException("Unexpected value: " + func);
                }
            }

            connection.close();
            input.close();
            output.close();
        } catch (IOException var16) {
            System.out.println(var16.getMessage());
        }
    }
}
