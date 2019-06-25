package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;


class Server3 implements Runnable{
    ServerSocket lis;
    Server3() throws Exception{
        lis = new ServerSocket(8080);
    }

    @Override
    public void run(){
        while(true) {
            try{
                System.out.println("waiting...");
                Socket socket = lis.accept();
                System.out.println(socket.getInetAddress());

                BufferedReader inp = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                //System.out.println(inp.readLine());
                try {
                    // out.println("hello");
                    out.println("welcome!");
                    String clientInput;
                    while (true) {
//                        System.out.println("Waiting for input");
                        clientInput = inp.readLine();
                        //   System.err.println("client input: "+clientInput);
                        if (clientInput==null || clientInput.equals("quit")) {
                            break;
                        }
                        // out.print(">");
                        int ans = calc(clientInput);
                        out.println("> "+ clientInput+" = " + ans);
                        out.println("finish");
                    }


                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    try {
                        socket.close();
                        //out.println("finish");
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }

            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Server3 s = new Server3();
        // new Thread(s).start();
        s.run();
    }

    public static int calc(String s){
        int ans= 0;//avali

        try {
            String[] arr = s.split("(?=([+-]))");
            if(s.isEmpty()) return 0;
            if(arr[0].charAt(0)=='-') {
                int d = Integer.parseInt(arr[0].substring(1));
                ans-=d;
            }

            else{
                int d = Integer.parseInt(arr[0].substring(0));
                ans+=d;
            }
            for (int i = 1; i <arr.length ; i++) {
                //     if(!isNumber(arr[i].substring(1))) return 0;
                int d = Integer.parseInt(arr[i].substring(1));
                if(arr[i].charAt(0)=='+') ans+=d;
                else ans-=d;

            }

        }catch (Exception e){
            System.out.println("Wrong input format");
        }
        return ans;
    }

}
