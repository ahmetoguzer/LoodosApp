import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public class AlgorithmExample {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] arg){

       String mString = "abbcccaaeeeeb bfffffca ccab";

//      n = 2 -->> result : "a***********b b*****ca **ab"
//      n = 3 -->> result : "abb***aa****b b*****ca ccab"
//      n = 5 -->> result : "abbcccaaeeeeb b*****ca ccab"
//      n = 9 -->> result : "abbcccaaeeeeb bfffffca ccab"

        changeString(mString,3);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void changeString(String mStr, int repeatCount) {
        HashMap<Integer, Integer> mCharMap = new HashMap<Integer, Integer>();
        char[] mCharArray = mStr.toCharArray();
        int count = 1;
        for(int i=1; i<mCharArray.length; i++){
            if(mCharArray[i] == mCharArray[i-1]){
                count++;
                if(count >= repeatCount)
                    mCharMap.put(i ,count);
            } else
                count = 1;
        }

        for ( Map.Entry<Integer, Integer> entry : mCharMap.entrySet()) {
            int mCharPos = entry.getKey();
            int mCharCount = entry.getValue();
            // Java 8 stream APIs using for n-times string
            String s = IntStream.range(0, mCharCount).mapToObj(i ->  "*").collect(Collectors.joining(""));
            mStr = mStr.substring(0, mCharPos - mCharCount + 1) + s + mStr.substring(mCharPos + 1, mStr.length());
        }
        System.out.println(mStr);
    }
}
