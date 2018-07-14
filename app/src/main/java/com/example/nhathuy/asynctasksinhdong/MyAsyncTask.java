package com.example.nhathuy.asynctasksinhdong;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

// AsyncTask<Params, Progress, Params> -> đối số Progress ở bài này để kiểu Integer để dễ set Progress cho SeekBar
public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    TextView txtStatus;
    SeekBar seekBar;

    Activity Context;

    // Toast lên nó đòi truyền Context vào nên làm hàm này thôi. Nếu ko Toast thì khỏi làm cũng dc
    public MyAsyncTask(Activity Context) {
        this.Context = Context;
        txtStatus = (TextView) Context.findViewById(R.id.txtStatus);
        seekBar = (SeekBar) Context.findViewById(R.id.seekbar);
    }

    // Hàm này sẽ chạy đầu tiên khi AsyncTask này được gọi
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(Context, "Start", Toast.LENGTH_SHORT).show();
    }

    // Hàm được được hiện tiếp sau hàm onPreExecute() và nó thực hiện các tác vụ chạy ngầm
    // Tuyệt đối ko vẽ giao diện trong hàm này
    @Override
    protected Void doInBackground(Void... params) {
        for(int i = 1; i < 101; ++i) {
            try {
                Thread.sleep(100);
                publishProgress(i);     // truyền dữ liệu cho hàm onProgressUpdate() biết tiến độ bao nhiêu rồi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    // Hàm thực hiện update giao diện khi có dữ liệu từ hàm doInBackground gửi xuống
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        seekBar.setProgress(values[0]);
        txtStatus.setText(values[0] + " %");
    }

    // Hàm này được thực hiện khi tiến trình kết thúc
    // Có thể nhận dữ liệu từ hàm doInBackground() gửi xuống nữa
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(Context, "Finished", Toast.LENGTH_SHORT).show();
    }
}

/*------------------------------------------ truyền 3 tham số khác nhau coi sao ------------------------------------------*/
//// AsyncTask<String, Integer, Boolean>
//public class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {
//    TextView txtStatus;
//    SeekBar seekBar;
//
//    Activity Context;
//
//    // Toast lên nó đòi truyền Context vào nên làm hàm này thôi. Nếu ko Toast thì khỏi làm cũng dc
//    public MyAsyncTask(Activity Context) {
//        this.Context = Context;
//        txtStatus = (TextView) Context.findViewById(R.id.txtStatus);
//        seekBar = (SeekBar) Context.findViewById(R.id.seekbar);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        Toast.makeText(Context, "START", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected Boolean doInBackground(String... params) {
//        for(int i = 1; i < 101; ++i) {
//            try {
//                Thread.sleep(100);
//                publishProgress(i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
//        txtStatus.setText(values[0] + " %");
//        seekBar.setProgress(values[0]);
//    }
//
//    @Override
//    protected void onPostExecute(Boolean aBoolean) {
//        super.onPostExecute(aBoolean);
//        if(aBoolean == false) {
//            Toast.makeText(Context, "FALSE", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
