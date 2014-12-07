package bangkokguy.development.android.othello;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


public class MainActivity extends ActionBarActivity {

    public GridView playGround;

    private final static int cPlayerOne = 1;
    private final static int cPlayerTwo = 2;
    private final static int cEmpty = 0;

    private static final String[] cPieces  = {" ","X","O"};
    private static Integer[][] wPlayGround;
    private static String[] sPlayGround;
    private static int[]tt;

    private ArrayAdapter<String> bb;

    private void initProgram() {

        wPlayGround = new Integer[10][10];
        sPlayGround = new String[100];

        for (int i=0;i<=9;i++) {
            for (int j=0;j<=9;j++) {
                wPlayGround[i][j] = cEmpty;
            }
        }
        wPlayGround [4][4]= cPlayerOne;
        wPlayGround [5][5]= cPlayerOne;
        wPlayGround [4][5]= cPlayerTwo;
        wPlayGround [5][4]= cPlayerTwo;

    }

    private void fillScreen() {
        for (int i=0;i<=9;i++) {
            for (int j=0;j<=9;j++) {
                sPlayGround[i*10+j]=cPieces[(wPlayGround[i][j])];
            }
        }
        bb.addAll(sPlayGround);
    }

    private void mainLogic() {
        initProgram();
        fillScreen();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView playGround = (GridView)findViewById(R.id.gridView);
        playGround.setNumColumns(10);

        playGround.setOnItemClickListener(mMessageClickedHandler);

        bb = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        playGround.setAdapter(bb);
        mainLogic();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* Create a message handling object as an anonymous class.*/
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            bb.notifyDataSetChanged();
            sPlayGround[position]="*";
            bb.clear();
            bb.addAll(sPlayGround);
        }
    };
}