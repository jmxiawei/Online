package cn.samir.online;

import android.util.Log;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //  0-100
        DecimalFormat df = new DecimalFormat("#.##");
        String v = df.format(0);
        Log.e("Tag", v);
    }
}