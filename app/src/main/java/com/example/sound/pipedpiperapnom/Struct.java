package com.example.sound.pipedpiperapnom;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Struct {

    public static String[] unpack(char[] packet, byte[] raw){
        String[] result = new String[packet.length];

        int pos = 0;
        int Strindex = 0;

        for (int x = 0; x < packet.length; x++){

            char type = packet[x];
            if (type == 'x'){
                pos += 1;
                continue;
            }
            else if (type == 'c'){
                char c = (char) (raw[pos] & 0xFF);
                result[Strindex] = Character.toString(c);
                Strindex += 1;
                pos += 1;
            }
            else if (type == 'h'){
                ByteBuffer bb = ByteBuffer.allocate(2);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                bb.put(raw[pos]);
                bb.put(raw[pos+1]);
                short shortVal = bb.getShort(0);
                result[Strindex] = Short.toString(shortVal);
                pos += 2;
                Strindex += 1;
            }
            else if (type == 's'){
                String s = "";

                while (raw[pos] != (byte)0x00){
                    char c = (char) (raw[pos] & 0xFF);
                    s += Character.toString(c);
                    pos += 1;
                }
                result[Strindex] = s;
                Strindex += 1;
                pos += 1;
            }
            else if (type == 'b'){
                Byte p = raw[pos];
                result[Strindex] = Integer.toString(p.intValue());
                Strindex += 1;
                pos += 1;
            }
        }
        return result;
    }
}
